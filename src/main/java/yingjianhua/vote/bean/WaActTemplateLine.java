package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_template_line database table.
 * 
 */
@Entity
@Table(name="wa_act_template_line")
@NamedQuery(name="WaActTemplateLine.findAll", query="SELECT w FROM WaActTemplateLine w")
public class WaActTemplateLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkey;

	private int account;

	private int main;

	@Column(name="row_version")
	private short rowVersion;

	private byte type;

	private String url;

	public WaActTemplateLine() {
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

	public int getMain() {
		return this.main;
	}

	public void setMain(int main) {
		this.main = main;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}