package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_vote_photo database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_photo")
@NamedQuery(name="WaActVotePhoto.findAll", query="SELECT w FROM WaActVotePhoto w")
public class WaActVotePhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="photo_url")
	private String photoUrl;

	@Column(name="row_version")
	private Short rowVersion;

	private Short sort;

	@Column(name="vote_entry")
	private Integer voteEntry;

	public WaActVotePhoto() {
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

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Integer getVoteEntry() {
		return this.voteEntry;
	}

	public void setVoteEntry(Integer voteEntry) {
		this.voteEntry = voteEntry;
	}

}