package yingjianhua.vote.dao.impl;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WxUser;
import yingjianhua.vote.dao.AbstractDao;
@Repository
public class WxUserDao extends AbstractDao<WxUser, Integer> {

	public WxUser findByOpenidInAccount(Integer accountPkey, String openid) {
		return findUnique("select * from wx_user where open_id=? and account=?", openid, accountPkey);
	}
	
	public WxUser findByUnionidInAccount(Integer accountPkey, String unionid) {
		return findUnique("select * from wx_user where union_id=? and account=?", unionid, accountPkey);
	}
	
}
