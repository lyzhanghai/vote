package yingjianhua.vote.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WaActVotePhoto;
import yingjianhua.vote.dao.AbstractDao;

@Repository
public class WaActVotePhotoDao extends AbstractDao<WaActVotePhoto, Integer> {
	
	public List<WaActVotePhoto> listByEntry(Integer entryPkey) {
		return list("select * from wa_act_vote_photo where vote_entry=?", entryPkey);
	}
	
	public WaActVotePhoto getFirstPhoto(Integer entryPkey) {
		return findUnique("select * from wa_act_vote_photo where vote_entry=? order by sort limit 1", entryPkey);
	}

}
