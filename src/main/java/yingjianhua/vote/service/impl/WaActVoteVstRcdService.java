package yingjianhua.vote.service.impl;

import org.springframework.stereotype.Service;

import yingjianhua.vote.bean.WaActVoteVstRcd;
import yingjianhua.vote.service.AbstractService;

@Service
public class WaActVoteVstRcdService extends AbstractService<WaActVoteVstRcd> {

	public Long countByVote(Integer votePkey) {
		return waActVoteVstRcdDao.countByVote(votePkey);
	}
	
}
