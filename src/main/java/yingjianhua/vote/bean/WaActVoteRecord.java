package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wa_act_vote_record database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_record")
@NamedQuery(name="WaActVoteRecord.findAll", query="SELECT w FROM WaActVoteRecord w")
public class WaActVoteRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private Integer act;

	@Column(name="entry_record")
	private Integer entryRecord;

	private String ip;

	@Column(name="row_version")
	private Short rowVersion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vote_time")
	private Date voteTime;

	@Column(name="wx_user")
	private Integer wxUser;

	public WaActVoteRecord() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public Integer getAccount() {
		return this.account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Integer getAct() {
		return this.act;
	}

	public void setAct(Integer act) {
		this.act = act;
	}

	public Integer getEntryRecord() {
		return this.entryRecord;
	}

	public void setEntryRecord(Integer entryRecord) {
		this.entryRecord = entryRecord;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Date getVoteTime() {
		return this.voteTime;
	}

	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}

	public Integer getWxUser() {
		return this.wxUser;
	}

	public void setWxUser(Integer wxUser) {
		this.wxUser = wxUser;
	}

}