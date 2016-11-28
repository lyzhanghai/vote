package yingjianhua.vote.actions;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import yingjianhua.vote.bean.WxAccount;
import yingjianhua.vote.bean.WxUser;
import yingjianhua.vote.service.impl.WaActVoteBannerService;
import yingjianhua.vote.service.impl.WaActVoteConfigService;
import yingjianhua.vote.service.impl.WaActVoteEntryService;
import yingjianhua.vote.service.impl.WaActVotePhotoService;
import yingjianhua.vote.service.impl.WaActVoteRecordService;
import yingjianhua.vote.service.impl.WaActVoteService;
import yingjianhua.vote.service.impl.WaActVoteVstRcdService;
import yingjianhua.vote.service.impl.WxAccountService;
import yingjianhua.vote.service.impl.WxUserGroupService;
import yingjianhua.vote.service.impl.WxUserService;
import yingjianhua.vote.tools.Constant;

public abstract class AbstractWptAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	protected static String domain = null;
	protected String requestUrl = null;
	protected Map<String, Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected WxAccount account;
	protected String result;
	public static final String TRENDS = "trends";
	public static final String RTRENDS = "rtrends";
	
	@Resource
	protected Constant constant;
	
	@Resource
	protected WxAccountService wxAccountService;
	@Resource
	protected WxUserService wxUserService;
	@Resource
	protected WxUserGroupService wxUserGroupService;
	@Resource
	protected WaActVoteService waActVoteService;
	@Resource
	protected WaActVoteBannerService waActVoteBannerService;
	@Resource
	protected WaActVoteEntryService waActVoteEntryService;
	@Resource
	protected WaActVoteRecordService waActVoteRecordService;
	@Resource
	protected WaActVoteVstRcdService waActVoteVstRcdService;
	@Resource
	protected WaActVotePhotoService waActVotePhotoService;
	@Resource
	protected WaActVoteConfigService waActVoteConfigService;
	
	public Map<String, Object> getSession() {
		if(session == null)
			session = ServletActionContext.getContext().getSession();
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		if(request == null) 
			 request = ServletActionContext.getRequest();
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		if(response == null)
			 response = ServletActionContext.getResponse();
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public WxAccount getAccount() {
		return account;
	}
	public void setAccount(WxAccount account) {
		this.account = account;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDomain() {
		return constant.getWebPath();
	}
	public String getRequestUrl() {
		if(requestUrl == null) {
			String query = getRequest().getQueryString();
			requestUrl = getRequest().getRequestURL() + (query == null ? "" : "?" + query);
		}
		return requestUrl;
	}
	
	/**
	 * 网页授权后，session里保留了用户的基本信息，可以调用该方法，把微信用户对象从数据中提取出来
	 * @return
	 */
	public WxUser chkWxUser() {
		String openid = (String)getSession().get("openid");
		String unionid = (String)getSession().get("unionid");
		Integer accountPkey = (Integer)getSession().get("accountPkey");
		return accountPkey==null?null:openid!=null?wxUserService.findByOpenidInAccount(accountPkey, openid):unionid!=null?wxUserService.findByUnionidInAccount(accountPkey, unionid):null;
	}
}
