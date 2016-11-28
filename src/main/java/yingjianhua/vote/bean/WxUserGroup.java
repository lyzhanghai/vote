package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wx_user_group database table.
 * 
 */
@Entity
@Table(name="wx_user_group")
@NamedQuery(name="WxUserGroup.findAll", query="SELECT w FROM WxUserGroup w")
public class WxUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private String name;

	@Column(name="row_version")
	private Short rowVersion;

	@Column(name="sync_status")
	private Byte syncStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;

	private Integer wxid;

	public WxUserGroup() {
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

	public Byte getSyncStatus() {
		return this.syncStatus;
	}

	public void setSyncStatus(Byte syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getWxid() {
		return this.wxid;
	}

	public void setWxid(Integer wxid) {
		this.wxid = wxid;
	}

}