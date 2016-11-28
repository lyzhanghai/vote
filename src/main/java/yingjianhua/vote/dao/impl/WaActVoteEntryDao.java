package yingjianhua.vote.dao.impl;

import org.springframework.stereotype.Repository;

import yingjianhua.vote.bean.WaActVoteEntry;
import yingjianhua.vote.dao.AbstractDao;
import yingjianhua.vote.tools.Page;

@Repository
public class WaActVoteEntryDao extends AbstractDao<WaActVoteEntry, Integer> {

	public Long countByVote(Integer votePkey) {
		return count("select count(*) from wa_act_vote_entry where vote=?", votePkey);
	}
	
	public Long sumVoteRecordByVote(Integer votePkey) {
		return sum("select sum(vote_count) from wa_act_vote_entry where vote=?", votePkey).longValue();
	}

	public Integer rankByVoteCount(Integer entryPkey) {
		return count("select count(*)+1 from wa_act_vote_entry a, wa_act_vote_entry b where b.pkey=? and a.vote=b.vote and a.vote_count>b.vote_count", entryPkey).intValue();
	}
	public Integer nextNumber(Integer voteId) {
		return count("select count(*)+1 from wa_act_vote_entry where vote=?", voteId).intValue();
	}
	
	private Page<WaActVoteEntry> pageCondition(Integer voteId, String condition, Integer start, Integer limit, byte orderBy) {
		String order = orderBy==1?" order by entry_time desc":orderBy==2?" order by vote_count desc":"";
		if(condition == null) {
			return pageSql(start, limit, "from wa_act_vote_entry where vote=?"+order, voteId);
		} else {
			try {
				Integer number = Integer.parseInt(condition);
				return pageSql(start, limit, "from wa_act_vote_entry where vote=? and (name_person like ? or number=?)"+order, voteId, "%"+condition+"%", number);
			} catch (NumberFormatException e) {
				return pageSql(start, limit, "from wa_act_vote_entry where vote=? and name_person like ?"+order, voteId, "%"+condition+"%");
			}
		}
	}
	public Page<WaActVoteEntry> pageOrderByRank(Integer voteId, String condition, Integer start, Integer limit) {
		return pageCondition(voteId, condition, start, limit, (byte)2);
	}
	public Page<WaActVoteEntry> pageOrderByEntryTime(Integer voteId, String condition, Integer start, Integer limit) {
		return pageCondition(voteId, condition, start, limit, (byte)1);
	}
}
