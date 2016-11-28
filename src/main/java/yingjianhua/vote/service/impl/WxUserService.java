package yingjianhua.vote.service.impl;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import yingjianhua.vote.bean.WxAccount;
import yingjianhua.vote.bean.WxUser;
import yingjianhua.vote.service.AbstractService;
import yingjianhua.vote.tools.WeixinUtil;

@Service
public class WxUserService extends AbstractService<WxUser> {
	
	/** 拉取用户信息(需scope为 snsapi_userinfo) 网页授权**/
	public static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//获取用户基本信息接口
	public final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//获取用户列表信息
	public final static String user_List_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	//批量获取用户信息列表
	public final static String user_info_batchget_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	//设置备注名
	public final static String user_remark_url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	//移动用户分组
	public final static String user_group_update_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	
	/**
	 * 根据网页授权获取的用户信息，新增用户
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public WxUser insByAuthorize(String accessToken, String openid, WxAccount account) {
		return ins(getInfoJsonByAuthorize(accessToken, openid), account.getPkey());
	}
	private WxUser ins(JSONObject json, Integer accountPkey) {
		WxUser user = buildWxUser(null, json, accountPkey);
		wxUserDao.save(user);
		return user;
	}
	/**
	 * 根据JSON中的内容新建一个wxUser对象或更新一个wxUser
	 * @param json
	 * @param account
	 * @return
	 */
	private WxUser buildWxUser(WxUser user, JSONObject json, Integer accountPkey) {
		try {
			if(user == null) {
				user = new WxUser();
				user.setOpenId(json.getString("openid"));
				user.setAccount(accountPkey);
				user.setSyncStatus(true);
			}
			user.setNickname(json.getString("nickname"));
			user.setSex((byte)json.getInt("sex"));
			user.setProvince(json.getString("province"));
			user.setCity(json.getString("city"));
			user.setCountry(json.getString("country"));
			user.setImageUrl(json.getString("headimgurl"));
			if(json.has("unionid")) {
				user.setUnionId(json.getString("unionid"));
			}
			if(json.has("groupid")) {
				user.setUserGroup(wxUserGroupDao.findByWxIdAccount(json.getInt("groupid"), accountPkey));
			} else {
				user.setUserGroup(wxUserGroupDao.findByWxIdAccount(0, accountPkey));
			}
			if(json.has("subscribe")) {//1 关注，2未关注
				user.setStatus((byte)(json.getInt("subscribe")==0?2:1));
				//user.stStatus(?Wx.OStatus.NOFOLLOW:Wx.OStatus.FOLLOW);
			} else {
				user.setStatus((byte)1);
			}
			if(json.has("subscribe_time")) {
				user.setSubscribeTime(new Date(Long.parseLong(json.get("subscribe_time")+"000")));
			} else {
				user.setSubscribeTime(new Date());
			}
			if(json.has("remark")) {
				user.setRem(json.getString("remark"));
			}
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	private static JSONObject getInfoJsonByAuthorize(String accessToken, String openid) {
		String requestUrl = USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replaceAll("OPENID", openid);
		return WeixinUtil.httpRequest(requestUrl, "GET", null);
	}
	
	public WxUser findByOpenidInAccount(Integer accountPkey, String openid) {
		return wxUserDao.findByOpenidInAccount(accountPkey, openid);
	}
	
	public WxUser findByUnionidInAccount(Integer accountPkey, String unionid) {
		return wxUserDao.findByUnionidInAccount(accountPkey, unionid);
	}
}
