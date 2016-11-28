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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the wx_user database table.
 * 
 */
@Entity
@Table(name="wx_user")
@NamedQuery(name="WxUser.findAll", query="SELECT w FROM WxUser w")
public class WxUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private String city;

	private String country;

	@Column(name="image_url")
	private String imageUrl;

	private String nickname;

	@Column(name="open_id")
	private String openId;

	private String province;

	private String rem;

	@Column(name="row_version")
	private Short rowVersion;

	private Byte sex;

	private Byte status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="subscribe_time")
	private Date subscribeTime;

	@Column(name="sync_status")
	private boolean syncStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sync_time")
	private Date syncTime;

	@Column(name="union_id")
	private String unionId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_group")
	private WxUserGroup userGroup;

	public WxUser() {
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRem() {
		return this.rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Byte getSex() {
		return this.sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public boolean getSyncStatus() {
		return this.syncStatus;
	}

	public void setSyncStatus(boolean syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Date getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public WxUserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(WxUserGroup userGroup) {
		this.userGroup = userGroup;
	}

}