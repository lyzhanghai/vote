package yingjianhua.vote.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import yingjianhua.vote.dao.AbstractDao;
import yingjianhua.vote.dao.impl.WaActConfigDao;
import yingjianhua.vote.dao.impl.WaActVoteBannerDao;
import yingjianhua.vote.dao.impl.WaActVoteConfigDao;
import yingjianhua.vote.dao.impl.WaActVoteDao;
import yingjianhua.vote.dao.impl.WaActVoteEntryDao;
import yingjianhua.vote.dao.impl.WaActVotePhotoDao;
import yingjianhua.vote.dao.impl.WaActVoteRecordDao;
import yingjianhua.vote.dao.impl.WaActVoteVstRcdDao;
import yingjianhua.vote.dao.impl.WxAccountDao;
import yingjianhua.vote.dao.impl.WxUserDao;
import yingjianhua.vote.dao.impl.WxUserGroupDao;
import yingjianhua.vote.tools.GenericsUtils;
import yingjianhua.vote.tools.Page;

public abstract class AbstractService<T> {

	protected AbstractDao dao;
	@Resource
	protected WxAccountDao wxAccountDao;
	@Resource
	protected WxUserDao wxUserDao;
	@Resource
	protected WxUserGroupDao wxUserGroupDao;
	@Resource
	protected WaActVoteDao waActVoteDao;
	@Resource
	protected WaActVoteBannerDao waActVoteBannerDao;
	@Resource
	protected WaActVoteEntryDao waActVoteEntryDao;
	@Resource
	protected WaActVoteRecordDao waActVoteRecordDao;
	@Resource
	protected WaActVoteVstRcdDao waActVoteVstRcdDao;
	@Resource
	protected WaActVotePhotoDao waActVotePhotoDao;
	@Resource
	protected WaActVoteConfigDao waActVoteConfigDao;
	@Resource
	protected WaActConfigDao waActConfigDao;
	
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractService() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	@PostConstruct
	public void init() {
		String clazzName = entityClass.getSimpleName();
		String clazzService = clazzName.substring(0, 1).toLowerCase() 
				+ clazzName.substring(1) + "Dao";
		try {
			Field clazzField = AbstractService.class.getDeclaredField(clazzService);
			Field baseField = AbstractService.class.getDeclaredField("dao");
			baseField.set(this, clazzField.get(this)); //service就有值了
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<T> list(Integer start, Integer limit, String where) {
		return dao.list(start, limit, where);
	}
	
	public T get(Serializable id) {
		return (T)dao.get(id);
	}
	
	public T load(Serializable id) {
		return (T)dao.load(id);
	}
	
	public Page<T> page(Integer start, Integer limit, String where) {
		return dao.pageHql(start, limit, where);
	}
	
	public void save(T bean) {
		dao.save(bean);
	}
	
	public void update(T bean) {
		dao.update(bean);
	}
	
	public void delete(T bean) {
		dao.delete(bean);
	}
}
