package yingjianhua.vote.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import yingjianhua.vote.bean.OActEntryStatus;
import yingjianhua.vote.bean.OActUnit;
import yingjianhua.vote.bean.OStatus;
import yingjianhua.vote.bean.WaActConfig;
import yingjianhua.vote.bean.WaActVote;
import yingjianhua.vote.bean.WaActVoteEntry;
import yingjianhua.vote.bean.WaActVotePhoto;
import yingjianhua.vote.bean.WxAccount;
import yingjianhua.vote.bean.WxUser;
import yingjianhua.vote.pub.Log;
import yingjianhua.vote.service.AbstractService;
import yingjianhua.vote.tools.Page;
import yingjianhua.vote.tools.UploadUtil;
import yingjianhua.vote.tools.WeixinUtil;

@Service
public class WaActVoteEntryService extends AbstractService<WaActVoteEntry> {

	private static final Log LOG = new Log(WaActVoteEntryService.class);
	@Resource
	private UploadUtil uploadUtil;
	
	public Integer countByVote(Integer votePkey) {
		return waActVoteEntryDao.countByVote(votePkey).intValue();
	}
	
	public Integer rankByVoteCount(Integer entryPkey) {
		return waActVoteEntryDao.rankByVoteCount(entryPkey);
	}
	
	/**
	 * 
	 * @param voteId
	 * @param condition 搜索条件，名称或编号 
	 * @param start
	 * @param limit
	 * @param orderby 1表示根据报名顺序倒序，2表示根据排名顺序
	 * @return
	 */
	public Page<WaActVoteEntry> entryList(Integer voteId, String condition, Integer start, Integer limit, byte orderby) {
		return orderby==1?waActVoteEntryDao.pageOrderByEntryTime(voteId, condition, start, limit):orderby==2?waActVoteEntryDao.pageOrderByRank(voteId, condition, start, limit):null;
	}
	
	public boolean chkVoteCondition(WaActVote vote, WaActVoteEntry entry, WxUser wxUser, String ip) {
		Date now = new Date();
	    if (now.after(vote.getActEndTime()))
	    	throw LOG.err("woring time", "活动已结束");
	    if(now.before(vote.getActStartTime()))
	    	throw LOG.err("woring time", "活动未开始");
	    if(now.before(vote.getEntryStartTime()))
	    	throw LOG.err("need subscri", "投票时间未到达");
	    if(now.after(vote.getEntryEndTime()))
	    	throw LOG.err("need subscri", "投票时间已过");
	    if(wxUser.getStatus()==OStatus.NOFOLLOW.getLine().getKey())
	    	throw LOG.err("need subscri", "请先关注公众号");

	    WaActConfig config = vote.getActConfig();
	    int days;
	    if (config.getUnit().equals(OActUnit.WEEK.getLine().getKey()))
	        days = config.getCycle().intValue() * 7;
	    else
	    	days = config.getCycle().intValue();

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(calendar.get(1), calendar.get(2), calendar.get(5) - days + 1, 0, 0, 0);
	    Date stTime = calendar.getTime();

	    if ((waActVoteRecordDao.countByWxUser(vote.getPkey(), wxUser.getPkey(), stTime, now) >= config.getCycleLimit().intValue()) && (config.getCycleLimit().intValue() != -1))
	    	throw LOG.err("count limit", "{0}", config.getCycleLimitWords());
	    if ((config.getIpLimit().intValue() != -1)&&(waActVoteRecordDao.countByIp(vote.getPkey(), ip, stTime, now) >= config.getIpLimit().intValue()))
	    	throw LOG.err("ip limit", "{0}", config.getIpLimitWords());
	    
		return true;
	}
	public void vote(WaActVoteEntry entry) {
		entry.setVoteCount(entry.getVoteCount()+1);
		waActVoteEntryDao.update(entry);
	}
	
	public WaActVoteEntry entry(WaActVote vote, Integer wxUserId, String name, String phone, String des, String[] picMediaIds) {
		WaActVoteEntry entry = new WaActVoteEntry();
	    entry.setVote(vote);
	    entry.setWxUser(wxUserId);
	    entry.setNamePerson(name);
	    entry.setPhonePerson(phone);
	    entry.setDes(des);
	    entry.setEntryTime(new Date());
	    entry.setStatus((vote.getVoteConfig().getEntryAppr()?OActEntryStatus.INIT:OActEntryStatus.APPR).getLine().getKey());
	    entry.setNumber(waActVoteEntryDao.nextNumber(vote.getPkey()));
	    entry.setAccount(vote.getAccount().getPkey());
	    entry.setVoteCount(Integer.valueOf(0));
	    waActVoteEntryDao.save(entry);

	    String[] urls = getImageUrls(vote.getPkey(), picMediaIds, vote.getAccount());
	    for(short i=0;i<urls.length;i++) {
	    	WaActVotePhoto photo = new WaActVotePhoto();
	    	photo.setVoteEntry(entry.getPkey());
	    	photo.setAccount(entry.getAccount());
	    	photo.setPhotoUrl(urls[i]);
	    	photo.setSort((short)(i+1));
	    	waActVotePhotoDao.save(photo);
	    }
	    return entry;
	}
	
	public String[] getImageUrls(Integer voteId, String[] mediaIds, WxAccount account) {
	    String[] urls = new String[mediaIds.length];
	    int j = mediaIds.length;
	    for (int i = 0; i < j; i++) { 
	    	String mediaId = mediaIds[i];
	    	String filename = String.valueOf(System.currentTimeMillis()) + ".jpg";
	    	String module = "vote"+File.separator+voteId;
	    	File file = new File(uploadUtil.getDefaultAbstractPath(module, filename));
	    	WeixinUtil.downloadMedia(account.getAccessToken(), mediaId, file);
	    	urls[i]=uploadUtil.getDefaultWebPath(module, filename);
	    }
	    return urls;
	}
	
}
