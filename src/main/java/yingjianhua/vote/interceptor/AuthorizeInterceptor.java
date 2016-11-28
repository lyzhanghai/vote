package yingjianhua.vote.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.ObjectNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import yingjianhua.vote.bean.OAccountType;
import yingjianhua.vote.bean.WaActVote;
import yingjianhua.vote.bean.WxAccount;
import yingjianhua.vote.pub.Log;
import yingjianhua.vote.pub.PubInfs.IMsg;
import yingjianhua.vote.service.impl.WaActVoteService;
import yingjianhua.vote.tools.WeixinUtil;
import yingjianhua.vote.tools.mch.MchUtil;

public class AuthorizeInterceptor extends MyAbstractInterceptor {
	
	public static void main(String[] args) {
		Integer voteId = Integer.parseInt("");
		System.out.println(voteId);
	}
	private static final long serialVersionUID = 1L;
	private static final Log LOG = new Log(AuthorizeInterceptor.class);
	
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		AccountNotExist("没有该公众号"),
		noAccountPkey("缺少公众号参数"),
		oauthErr("订阅号没有绑定三方授权代理号，不能进行网页授权"),
		oauthErr2("网页授权失败，未知错误"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	}
	
	/** 微信网页授权获取CODE **/
	public static final String OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	/** 微信网页授权获取网页accesstoken和OPENID **/
	public static final String OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/** 拉取用户信息(需scope为 snsapi_userinfo) **/
	public static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/** 网页授权重定向后带上的state参数 **/
	private static final String STATE = "oauth.state";
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		WaActVote vote = null;
		WxAccount account = null;
		String state = null;
		String code = null;
		String requestUrl = null;
		Integer voteId = null;
		Map<String, Object> session = null;
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		
		ActionContext actionContext = actionInvocation.getInvocationContext();
		session = actionContext.getSession();
		request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
		response = (HttpServletResponse)actionContext.get(ServletActionContext.HTTP_RESPONSE);

		String[] servletPath = request.getServletPath().split("/");
		if(servletPath.length>1) {
			try {
				voteId = Integer.parseInt(servletPath[1]);
				System.out.println("活动编号【"+voteId+"】");
				vote = getWaActVoteService().get(voteId);
				if(vote == null)
					throw LOG.err("vote no exsits", "编号{0}的活动不存在", voteId);
			} catch (NumberFormatException|ObjectNotFoundException e) {
				throw LOG.err("vote no exsits", "编号{0}的活动不存在", voteId);
			}
		}
		account = vote.getAccount();
		String query = request.getQueryString();
		requestUrl = request.getRequestURL() + (query == null ? "" : "?" + query);
		
		Map<String, Object> map = actionContext.getParameters();
		String[] states = (String[])map.get("state");
		if(states != null && states.length > 0) {
			state = states[0];
		}
		String[] codes = (String[])map.get("code");
		if(codes != null && codes.length > 0) {
			code = codes[0];
		}
		if(doAuthorize(account, state, code, requestUrl, session, request, response)) {
			return null;
		}
		System.out.println("AuthInterceptor start");
		String result = actionInvocation.invoke();
		System.out.println("AuthInterceptor end");
		return result;
	}
	/**
	 * 进行网页授权
	 * 若返回false 表示 已经完成网页授权，session里面已经放置了用户相关的信息
	 * 若返回true，表示 需要做网页授权，需要进行跳转
	 * @param account
	 * @return
	 * @throws JSONException
	 */
	public boolean doAuthorize(WxAccount account, String state, String code, String requestUrl, Map<String, Object> session, HttpServletRequest request, HttpServletResponse response) {
		WxAccount agent = null;
		if((agent=account).getAccountType()==OAccountType.SUBSCRIPTION.getLine().getKey() &&(agent=account.getAgentAccount())==null) {
			throw LOG.err(Msgs.oauthErr);
		}
		session.put("unionid", "oRf6tuLuAij7YQ2rSjvArZNHAtU0");
		session.put("accountPkey", 13);
		if((session.get("openid") != null ||session.get("unionid") != null)&& account.getPkey() == (int)session.get("accountPkey")) {
			//已经做过网页授权，不用再重复做了
			return false;
		} else if(state!=null && state.equals(session.get(STATE))) {
			if(code == null)
				throw LOG.err(Msgs.oauthErr2);
			//网页授权重定向回来 
			try {
				LOG.info("--------------doAuthorize():start--------------");
				requestUrl = OAUTH2_ACCESS_TOKEN_URL.replace("APPID", agent.getAccountAppid()).replace("SECRET", agent.getAccountAppsecret())
						.replaceAll("CODE", code);
				JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", null);
				if (result.has("errcode")) { 
					throw LOG.err("" + result.get("errcode"), result.getString("errmsg"));
				}
				
				String unionid = result.has("unionid")?result.getString("unionid"):null;
				String openid = result.has("openid")?result.getString("openid"):null;
				session.put("unionid", unionid);
				session.put("openid", openid);
				session.put("accountPkey", account.getPkey());
				LOG.info("--------------doAuthorize():end--------------");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			//进行网页授权的跳转
			try {
				state = MchUtil.createRandom(8);
				session.put(STATE, state);
				requestUrl = URLEncoder.encode(requestUrl.replace("+", "%2B").replace("*", "%2A").replace("~", "%7E").replace("#", "%23"), "UTF-8");
				String rtn_str = OAUTH2_AUTHORIZE_URL.replace("APPID", agent.getAccountAppid()).replace("REDIRECT_URI", requestUrl).replace("SCOPE", "snsapi_userinfo").replace("STATE", state);
				response.sendRedirect(rtn_str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	private WaActVoteService waActVoteService;
	
	private synchronized WaActVoteService getWaActVoteService() {
		if(waActVoteService == null) {
			waActVoteService = getFactory().getBean(WaActVoteService.class);
		}
		return waActVoteService;
	}

	private void setWaActVoteService(WaActVoteService waActVoteService) {
		this.waActVoteService = waActVoteService;
	}
}
