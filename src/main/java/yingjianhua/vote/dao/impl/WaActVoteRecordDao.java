package yingjianhua.vote.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WaActVoteRecord;
import yingjianhua.vote.dao.AbstractDao;

@Repository
public class WaActVoteRecordDao extends AbstractDao<WaActVoteRecord, Integer> {

	public Integer countByWxUser(Integer recordPkey, Integer userPkey, Date begin, Date end) {
		return count("select count(*) from wa_act_vote_record where pkey=? and wx_user=? and vote_time between ? and ?", recordPkey, userPkey, begin, end).intValue();
	}
	public Integer countByIp(Integer recordPkey, String ip, Date begin, Date end) {
		return count("select count(*) from wa_act_vote_record where pkey=? and ip=? and vote_time between ? and ?", recordPkey, ip, begin, end).intValue();
	}
}
