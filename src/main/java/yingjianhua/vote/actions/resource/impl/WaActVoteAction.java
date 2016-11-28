package yingjianhua.vote.actions.resource.impl;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import yingjianhua.vote.actions.resource.AbstractCRUDAction;
import yingjianhua.vote.bean.OStatus;
import yingjianhua.vote.bean.WaActVote;
import yingjianhua.vote.bean.WaActVoteBanner;
import yingjianhua.vote.bean.WxUser;
@Controller
@Scope("prototype")
public class WaActVoteAction extends AbstractCRUDAction<WaActVote> {
	private static final long serialVersionUID = 1L;
	
	private Integer voteId;
	public static void main(String[] args) {
		Date d1 = new Date();
		System.out.println("{\"success\":false,\"errmsg\":\""+"我错了"+"\"}");
		Date d2 = new Date();
		System.out.println(d2.getTime()-d1.getTime());
	}

	public String info() {
		bean = waActVoteService.get(voteId);
		WxUser wxUser = chkWxUser();
		
		JSONObject account = new JSONObject();
		account.put("id", bean.getAccount().getPkey());
		
		JSONObject user = new JSONObject();
		user.put("subscribe", wxUser.getStatus().equals(OStatus.FOLLOW.getLine().getKey())?true:false);
		
		JSONArray banners = new JSONArray();
		for(WaActVoteBanner line:waActVoteBannerService.listByVote(bean.getPkey())) {
			JSONObject banner = new JSONObject();
			banner.put("id", line.getPkey());
			banner.put("linkUrl", line.getUrl());
			banner.put("picUrl", line.getPicUrl());
			banner.put("title", line.getDescription());
			banner.put("sort", line.getSort());
			banners.add(banner);
		}
		JSONObject info = new JSONObject();
		info.put("id", bean.getPkey());
		info.put("actStartTime",bean.getActStartTime());
		info.put("actEndTime",bean.getActEndTime());
		info.put("entryStartTime",bean.getEntryStartTime());
		info.put("entryEndTime",bean.getEntryEndTime());
		info.put("status",bean.getStatus());
		info.put("account",account);
		info.put("user",user);
		info.put("banners", banners);
		
		object = info;
		return OBJECT;
	}
	
	public String voteInfo() {
		bean = waActVoteService.get(voteId);
		
		JSONObject voteInfo = new JSONObject();
		voteInfo.put("entrycount", waActVoteEntryService.countByVote(bean.getPkey()));
		voteInfo.put("votecount", waActVoteRecordService.countByVote(bean.getPkey()));
		voteInfo.put("visitcount", waActVoteVstRcdService.countByVote(bean.getPkey()));
		
		object = voteInfo;
		return OBJECT;
	}
	
	public String homeDes() {
		bean = waActVoteService.get(voteId);
		
		JSONObject homeDes = new JSONObject();
		homeDes.put("content", bean.getDes());
		
		object = homeDes;
		return OBJECT;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	
}
