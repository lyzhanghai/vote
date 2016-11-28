package yingjianhua.vote.dao.impl;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WxUserGroup;
import yingjianhua.vote.dao.AbstractDao;

@Repository
public class WxUserGroupDao extends AbstractDao<WxUserGroup, Integer> {

	public WxUserGroup findByWxIdAccount(Integer wxId, Integer accountPkey) {
		return findUnique("select * from wx_user_group where wxid=? and account=?", wxId, accountPkey);
	}
}
