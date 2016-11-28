package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wx_open_plat database table.
 * 
 */
@Entity
@Table(name="wx_open_plat")
@NamedQuery(name="WxOpenPlat.findAll", query="SELECT w FROM WxOpenPlat w")
public class WxOpenPlat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private String email;

	private String name;

	@Column(name="row_version")
	private Short rowVersion;

	@Column(name="user_sys")
	private Integer userSys;

	public WxOpenPlat() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getUserSys() {
		return this.userSys;
	}

	public void setUserSys(Integer userSys) {
		this.userSys = userSys;
	}

}