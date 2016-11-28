package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_vote_banner database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_banner")
@NamedQuery(name="WaActVoteBanner.findAll", query="SELECT w FROM WaActVoteBanner w")
public class WaActVoteBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private String description;

	@Column(name="pic_url")
	private String picUrl;

	@Column(name="row_version")
	private Short rowVersion;

	private Integer sort;

	private String url;

	private Integer vote;

	public WaActVoteBanner() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getVote() {
		return this.vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

}