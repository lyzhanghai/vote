package yingjianhua.vote.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WaActVoteBanner;
import yingjianhua.vote.dao.AbstractDao;

@Repository
public class WaActVoteBannerDao extends AbstractDao<WaActVoteBanner, Integer> {

	public List<WaActVoteBanner> listByVote(Integer votePkey) {
		return list("select * from wa_act_vote_banner where vote=?", votePkey);
	}
	
}
