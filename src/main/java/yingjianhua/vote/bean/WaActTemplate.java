package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_template database table.
 * 
 */
@Entity
@Table(name="wa_act_template")
@NamedQuery(name="WaActTemplate.findAll", query="SELECT w FROM WaActTemplate w")
public class WaActTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkey;

	private int account;

	private String name;

	@Column(name="row_version")
	private short rowVersion;

	public WaActTemplate() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

}