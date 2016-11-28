package yingjianhua.vote.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import yingjianhua.vote.bean.WaActVoteBanner;
import yingjianhua.vote.service.AbstractService;

@Service
public class WaActVoteBannerService extends AbstractService<WaActVoteBanner> {
	
	public List<WaActVoteBanner> listByVote(Integer votePkey) {
		return waActVoteBannerDao.listByVote(votePkey);
	}
	
}
