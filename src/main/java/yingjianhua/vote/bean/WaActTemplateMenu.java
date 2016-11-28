package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_template_menu database table.
 * 
 */
@Entity
@Table(name="wa_act_template_menu")
@NamedQuery(name="WaActTemplateMenu.findAll", query="SELECT w FROM WaActTemplateMenu w")
public class WaActTemplateMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkey;

	private int account;

	@Column(name="curr_img_url")
	private String currImgUrl;

	@Column(name="img_url")
	private String imgUrl;

	private String name;

	@Column(name="page_type")
	private byte pageType;

	@Column(name="row_version")
	private short rowVersion;

	private int temp;

	public WaActTemplateMenu() {
	}

	public int getPkey() {
		return this.pkey;
	}

	public void setPkey(int pkey) {
		this.pkey = pkey;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getCurrImgUrl() {
		return this.currImgUrl;
	}

	public void setCurrImgUrl(String currImgUrl) {
		this.currImgUrl = currImgUrl;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getPageType() {
		return this.pageType;
	}

	public void setPageType(byte pageType) {
		this.pageType = pageType;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public int getTemp() {
		return this.temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

}