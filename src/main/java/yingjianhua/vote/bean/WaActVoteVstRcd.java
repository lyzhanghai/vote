package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wa_act_vote_vst_rcd database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_vst_rcd")
@NamedQuery(name="WaActVoteVstRcd.findAll", query="SELECT w FROM WaActVoteVstRcd w")
public class WaActVoteVstRcd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkey;

	private Integer account;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	private String ip;

	private Byte pagetype;

	@Column(name="row_version")
	private Short rowVersion;

	private Integer user;

	private Byte visittype;

	private Integer vote;

	public WaActVoteVstRcd() {
	}

	public Long getPkey() {
		return this.pkey;
	}

	public void setPkey(Long pkey) {
		this.pkey = pkey;
	}

	public Integer getAccount() {
		return this.account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Byte getPagetype() {
		return this.pagetype;
	}

	public void setPagetype(Byte pagetype) {
		this.pagetype = pagetype;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Integer getUser() {
		return this.user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Byte getVisittype() {
		return this.visittype;
	}

	public void setVisittype(Byte visittype) {
		this.visittype = visittype;
	}

	public Integer getVote() {
		return this.vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

}