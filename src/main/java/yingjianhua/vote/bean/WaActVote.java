package yingjianhua.vote.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the wa_act_vote database table.
 * 
 */
@Entity
@Table(name="wa_act_vote")
@NamedQuery(name="WaActVote.findAll", query="SELECT w FROM WaActVote w")
public class WaActVote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account")
	private WxAccount account;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="act_config")
	private WaActConfig actConfig;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="act_end_time")
	private Date actEndTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="act_start_time")
	private Date actStartTime;

	@Column(name="act_template")
	private Integer actTemplate;

	@Lob
	private String des;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entry_end_time")
	private Date entryEndTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entry_start_time")
	private Date entryStartTime;

	@Column(name="js_menushare")
	private Integer jsMenushare;

	private String name;

	@Column(name="row_version")
	private Short rowVersion;

	private Byte status;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vote_config")
	private WaActVoteConfig voteConfig;

	public WaActVote() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public WxAccount getAccount() {
		return this.account;
	}

	public void setAccount(WxAccount account) {
		this.account = account;
	}

	public WaActConfig getActConfig() {
		return this.actConfig;
	}

	public void setActConfig(WaActConfig actConfig) {
		this.actConfig = actConfig;
	}

	public Date getActEndTime() {
		return this.actEndTime;
	}

	public void setActEndTime(Date actEndTime) {
		this.actEndTime = actEndTime;
	}

	public Date getActStartTime() {
		return this.actStartTime;
	}

	public void setActStartTime(Date actStartTime) {
		this.actStartTime = actStartTime;
	}

	public Integer getActTemplate() {
		return this.actTemplate;
	}

	public void setActTemplate(Integer actTemplate) {
		this.actTemplate = actTemplate;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getEntryEndTime() {
		return this.entryEndTime;
	}

	public void setEntryEndTime(Date entryEndTime) {
		this.entryEndTime = entryEndTime;
	}

	public Date getEntryStartTime() {
		return this.entryStartTime;
	}

	public void setEntryStartTime(Date entryStartTime) {
		this.entryStartTime = entryStartTime;
	}

	public Integer getJsMenushare() {
		return this.jsMenushare;
	}

	public void setJsMenushare(Integer jsMenushare) {
		this.jsMenushare = jsMenushare;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public WaActVoteConfig getVoteConfig() {
		return this.voteConfig;
	}

	public void setVoteConfig(WaActVoteConfig voteConfig) {
		this.voteConfig = voteConfig;
	}

}