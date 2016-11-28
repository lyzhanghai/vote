package yingjianhua.vote.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import yingjianhua.vote.actions.resource.AbstractCRUDAction;
import yingjianhua.vote.bean.WaActVoteEntry;
import yingjianhua.vote.bean.WaActVotePhoto;
import yingjianhua.vote.pub.Exp;
@Controller
@Scope("prototype")
public class WaActVoteEntryAction extends AbstractCRUDAction<WaActVoteEntry> {
	private static final long serialVersionUID = 1L;

	private Integer entryId;
	private Integer voteId;
	private String name;
	private String phone;
	private String des;
	private String[] picMediaIds;
	private String condition;
	private Byte orderBy;
	
	public String entryList() {
		pages = waActVoteEntryService.entryList(voteId, condition, start, limit, orderBy);
		
		JSONArray entryItems = new JSONArray();
		for(WaActVoteEntry line:pages.getItems()) {
			JSONObject entryItem = new JSONObject();
			entryItem.put("id", line.getPkey());
			entryItem.put("number", line.getNumber());
			entryItem.put("record", line.getVoteCount());
			entryItem.put("picUrl", waActVotePhotoService.getFirstPhoto(line.getPkey()).getPhotoUrl());
			entryItems.add(entryItem);
		}
		
		JSONObject entryList = new JSONObject();
		entryList.put("total", pages.getTotal());
		entryList.put("items", entryItems);
		
		object = entryList;
		return OBJECT;
	}
	
	public String detail() {
		bean = waActVoteEntryService.get(entryId);
		
		JSONArray photos = new JSONArray();
		for(WaActVotePhoto line:waActVotePhotoService.listByEntry(bean.getPkey())) {
			JSONObject photo = new JSONObject();
			photo.put("picUrl", line.getPhotoUrl());
			photo.put("id", line.getPkey());
			photo.put("sort", line.getSort());
			photos.add(photo);
		}
		
		JSONObject detail = new JSONObject();
		detail.put("id", bean.getPkey());
		detail.put("name", bean.getNamePerson());
		detail.put("number", bean.getNumber());
		detail.put("count", bean.getVoteCount());
		detail.put("rank", waActVoteEntryService.rankByVoteCount(bean.getPkey()));
		detail.put("picUrls", photos);

		object = detail;
		return OBJECT;
	}

	public String entry() {
		bean = waActVoteEntryService.entry(waActVoteService.get(voteId), chkWxUser().getPkey(), name, phone, des, picMediaIds);
		return OBJECT;
	}
	
	public String vote() {
		bean = waActVoteEntryService.get(entryId);
		try {
			if(waActVoteEntryService.chkVoteCondition(bean.getVote(), bean, chkWxUser(), getRequest().getRemoteAddr())) {
				waActVoteEntryService.vote(bean);
				waActVoteRecordService.vote(bean.getVote(), bean.getPkey(), chkWxUser().getPkey(), getRequest().getRemoteAddr(), bean.getAccount());
				JSONObject detail = new JSONObject();
				detail.put("id", bean.getPkey());
				detail.put("name", bean.getNamePerson());
				detail.put("number", bean.getNumber());
				detail.put("count", bean.getVoteCount());
				detail.put("rank", waActVoteEntryService.rankByVoteCount(bean.getPkey()));
				object = detail;
			}
		} catch (Exp e) {
			JSONObject msg = new JSONObject();
			msg.put("success", false);
			msg.put("errmsg", e.getExpMessage());
			object = msg;
		}
		
		return OBJECT;
	}
	
	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String[] getPicMediaIds() {
		return picMediaIds;
	}

	public void setPicMediaIds(String[] picMediaIds) {
		this.picMediaIds = picMediaIds;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Byte getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Byte orderBy) {
		this.orderBy = orderBy;
	}

}
