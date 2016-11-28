package yingjianhua.vote.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import yingjianhua.vote.bean.WxAccount;
import yingjianhua.vote.pub.Log;
import yingjianhua.vote.service.AbstractService;
import yingjianhua.vote.tools.Crypto;
import yingjianhua.vote.tools.WeixinUtil;

@Service
public class WxAccountService extends AbstractService<WxAccount> {
	private static final Log LOG = new Log(WxAccountService.class);
	
	public static String JS_SGIN_STR = "jsapi_ticket=JSAPI_TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
	
	public JSONObject jssdkConfig(WxAccount account, String url) {
		String appId = account.getAccountAppid();
		Long timestamp = tranDate(account.getAccessTime());
		String nonceStr = Crypto.encrypt(account.getJsapiTicket().substring(0, 16));
		String signature = crtSignature(getJsapiTicket(account), nonceStr, timestamp, url);
		
		JSONObject config = new JSONObject();
		config.put("appId", appId);
		config.put("timestamp", timestamp);
		config.put("nonceStr", nonceStr);
		config.put("signature", signature);
		
		return config;
	}
	/**
	 * 将java中的Date对象转换为微信的10位时间
	 * */
	private static long tranDate(Date date) {
		return Long.parseLong((date.getTime()+"").substring(0, 10));
	}
	/**
	 * 创建签名
	 * @return
	 */
	private static String crtSignature(String jsapiTicket, String nonceStr, Long timestamp, String url) {
		String str = JS_SGIN_STR.replace("JSAPI_TICKET", jsapiTicket)
				.replace("NONCESTR", nonceStr)
				.replace("TIMESTAMP", String.valueOf(timestamp))
				.replace("URL", url);
		return Crypto.getSha1(str);
	}
	public String getJsapiTicket(WxAccount account) {
		if (account.getJsapiTicket()==null || tokenTimeout(account))
			refreshAccessTokenAndJsapiTicket(account);
	 	return account.getJsapiTicket();
	}
	private boolean tokenTimeout(WxAccount account) {
		return (System.currentTimeMillis() - account.getAccessTime().getTime()) / 1000 / 3600 > 1.9;
	}
	 /**
	   * @param account 公众账号对象
	   * @return 得到当前公众号的APPID和APPSECRET并向微信服务器获取AccessToken
	   * @throws Exception
	   */
	  public synchronized void refreshAccessTokenAndJsapiTicket(WxAccount account) {
		  if (account.getAccessToken()!= null && !tokenTimeout(account))
			  return; // 有效期内直接返回
		  String token = null;
		  try {
			  String requestUrl = WeixinUtil.ACCESS_TOKEN_URL.replace("APPID", account.getAccountAppid()).replace("APPSECRET",account.getAccountAppsecret());
			  org.json.JSONObject json = WeixinUtil.httpRequest(requestUrl, "GET", null);
			  if (json.has(WeixinUtil.ERR_CODE))
				  throw LOG.err("","获取access_token失败");
			  token = json.getString("access_token");
			  account.setAccessToken(token);
			  
			  requestUrl = WeixinUtil.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", account.getAccessToken());
			  json = WeixinUtil.httpRequest(requestUrl, "GET", null);
			  if (json.getInt(WeixinUtil.ERR_CODE) != 0 || !json.getString(WeixinUtil.ERR_MSG).equals("ok"))
				  throw LOG.err("", "获取jsapi_ticket失败");
			  account.setJsapiTicket(json.getString("ticket"));
			  
			  account.setAccessTime(new Date());
			  wxAccountDao.save(account);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
}
