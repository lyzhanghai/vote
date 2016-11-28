package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wa_act_vote_entry database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_entry")
@NamedQuery(name="WaActVoteEntry.findAll", query="SELECT w FROM WaActVoteEntry w")
public class WaActVoteEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private String des;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entry_time")
	private Date entryTime;

	@Column(name="name_person")
	private String namePerson;

	private Integer number;

	@Column(name="phone_person")
	private String phonePerson;

	@Column(name="row_version")
	private Short rowVersion;

	private Byte status;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vote")
	private WaActVote vote;

	@Column(name="vote_count")
	private Integer voteCount;

	@Column(name="wx_user")
	private Integer wxUser;

	public WaActVoteEntry() {
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

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getEntryTime() {
		return this.entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getNamePerson() {
		return this.namePerson;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getPhonePerson() {
		return this.phonePerson;
	}

	public void setPhonePerson(String phonePerson) {
		this.phonePerson = phonePerson;
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

	public WaActVote getVote() {
		return this.vote;
	}

	public void setVote(WaActVote vote) {
		this.vote = vote;
	}

	public Integer getVoteCount() {
		return this.voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Integer getWxUser() {
		return this.wxUser;
	}

	public void setWxUser(Integer wxUser) {
		this.wxUser = wxUser;
	}

}