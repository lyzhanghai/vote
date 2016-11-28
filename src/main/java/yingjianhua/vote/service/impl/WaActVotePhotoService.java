package yingjianhua.vote.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import yingjianhua.vote.bean.WaActVotePhoto;
import yingjianhua.vote.service.AbstractService;

@Service
public class WaActVotePhotoService extends AbstractService<WaActVotePhoto> {

	public List<WaActVotePhoto> listByEntry(Integer entryPkey) {
		return waActVotePhotoDao.listByEntry(entryPkey);
	}
	
	public WaActVotePhoto getFirstPhoto(Integer entryPkey) {
		return waActVotePhotoDao.getFirstPhoto(entryPkey);
	}
}
