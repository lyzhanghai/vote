package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_vote_config database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_config")
@NamedQuery(name="WaActVoteConfig.findAll", query="SELECT w FROM WaActVoteConfig w")
public class WaActVoteConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="entry_appr")
	private Boolean entryAppr;

	private String name;

	@Column(name="pic_limit")
	private Integer picLimit;

	@Column(name="pic_width")
	private Integer picWidth;

	@Column(name="row_version")
	private Short rowVersion;

	@Column(name="show_ranking")
	private Byte showRanking;

	public WaActVoteConfig() {
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

	public Boolean getEntryAppr() {
		return this.entryAppr;
	}

	public void setEntryAppr(Boolean entryAppr) {
		this.entryAppr = entryAppr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPicLimit() {
		return this.picLimit;
	}

	public void setPicLimit(Integer picLimit) {
		this.picLimit = picLimit;
	}

	public Integer getPicWidth() {
		return this.picWidth;
	}

	public void setPicWidth(Integer picWidth) {
		this.picWidth = picWidth;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Byte getShowRanking() {
		return this.showRanking;
	}

	public void setShowRanking(Byte showRanking) {
		this.showRanking = showRanking;
	}

}