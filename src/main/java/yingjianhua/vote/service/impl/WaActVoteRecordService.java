package yingjianhua.vote.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import yingjianhua.vote.bean.WaActVote;
import yingjianhua.vote.bean.WaActVoteRecord;
import yingjianhua.vote.service.AbstractService;

@Service
public class WaActVoteRecordService extends AbstractService<WaActVoteRecord> {

	public Integer countByVote(Integer votePkey) {
		return waActVoteEntryDao.sumVoteRecordByVote(votePkey).intValue();
	}
	
	public WaActVoteRecord vote(WaActVote vote, Integer entryPkey, Integer wxUserPkey, String ip, Integer accountPkey) {
	    WaActVoteRecord record = new WaActVoteRecord();
	    record.setAct(vote.getPkey());
	    record.setEntryRecord(entryPkey);
	    record.setWxUser(wxUserPkey);
	    record.setVoteTime(new Date());
	    record.setIp(ip);
	    record.setAccount(accountPkey);
	    waActVoteRecordDao.save(record);
	    return record;
	}
	
}
