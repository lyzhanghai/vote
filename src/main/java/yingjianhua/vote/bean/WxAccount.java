package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wx_account database table.
 * 
 */
@Entity
@Table(name="wx_account")
@NamedQuery(name="WxAccount.findAll", query="SELECT w FROM WxAccount w")
public class WxAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="access_time")
	private Date accessTime;

	@Column(name="access_token")
	private String accessToken;

	@Column(name="account_appid")
	private String accountAppid;

	@Column(name="account_appsecret")
	private String accountAppsecret;

	@Column(name="account_desc")
	private String accountDesc;

	@Column(name="account_email")
	private String accountEmail;

	@Column(name="account_id")
	private String accountId;

	@Column(name="account_name")
	private String accountName;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="account_token")
	private String accountToken;

	@Column(name="account_type")
	private Byte accountType;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="agent_account")
	private WxAccount agentAccount;

	@Column(name="jsapi_ticket")
	private String jsapiTicket;

	@Column(name="mch_id")
	private String mchId;

	@Column(name="mch_key")
	private String mchKey;

	@Column(name="open_plat")
	private Integer openPlat;

	@Column(name="row_version")
	private Short rowVersion;

	@Column(name="user_sys")
	private Integer userSys;

	public WxAccount() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public Date getAccessTime() {
		return this.accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccountAppid() {
		return this.accountAppid;
	}

	public void setAccountAppid(String accountAppid) {
		this.accountAppid = accountAppid;
	}

	public String getAccountAppsecret() {
		return this.accountAppsecret;
	}

	public void setAccountAppsecret(String accountAppsecret) {
		this.accountAppsecret = accountAppsecret;
	}

	public String getAccountDesc() {
		return this.accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public String getAccountEmail() {
		return this.accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountToken() {
		return this.accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public Byte getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Byte accountType) {
		this.accountType = accountType;
	}

	public WxAccount getAgentAccount() {
		return this.agentAccount;
	}

	public void setAgentAccount(WxAccount agentAccount) {
		this.agentAccount = agentAccount;
	}

	public String getJsapiTicket() {
		return this.jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getMchId() {
		return this.mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return this.mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public Integer getOpenPlat() {
		return this.openPlat;
	}

	public void setOpenPlat(Integer openPlat) {
		this.openPlat = openPlat;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Integer getUserSys() {
		return this.userSys;
	}

	public void setUserSys(Integer userSys) {
		this.userSys = userSys;
	}

}