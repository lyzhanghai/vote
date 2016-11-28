package yingjianhua.vote.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import yingjianhua.vote.actions.resource.AbstractCRUDAction;
import yingjianhua.vote.bean.WxAccount;
import yingjianhua.vote.pub.Log;
@Controller
@Scope("prototype")
public class WxAccountAction extends AbstractCRUDAction<WxAccount> {
	private static final long serialVersionUID = 1L;
	private static final Log LOG = new Log(WxAccountAction.class);
	
	private Integer accountId;
	private String url;

	public String jssdkConfig() {
		if(!url.startsWith(constant.getWebPath()))
			throw LOG.err("param url error", "url 参数错误");
		else {
			bean = wxAccountService.get(accountId);
			JSONObject config = wxAccountService.jssdkConfig(bean, url);
			object = config;
		}
		return OBJECT;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
