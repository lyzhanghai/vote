package yingjianhua.vote.dao.impl;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WaActVoteVstRcd;
import yingjianhua.vote.dao.AbstractDao;

@Repository
public class WaActVoteVstRcdDao extends AbstractDao<WaActVoteVstRcd, Long> {

	public Long countByVote(Integer votePkey) {
		return count("select count(*) from wa_act_vote_vst_rcd where vote=?", votePkey);
	}
	
}
