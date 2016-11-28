package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_config database table.
 * 
 */
@Entity
@Table(name="wa_act_config")
@NamedQuery(name="WaActConfig.findAll", query="SELECT a FROM WaActConfig a")
public class WaActConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="act_rate")
	private Integer actRate;

	private String area;

	@Column(name="custom_menu")
	private Byte customMenu;

	private Integer cycle;

	@Column(name="cycle_limit")
	private Integer cycleLimit;

	@Column(name="cycle_limit_words")
	private String cycleLimitWords;

	@Column(name="entry_rate")
	private Integer entryRate;

	@Column(name="image_shape")
	private Byte imageShape;

	@Column(name="invalid_area")
	private String invalidArea;

	@Column(name="ip_limit")
	private Integer ipLimit;

	@Column(name="ip_limit_words")
	private String ipLimitWords;

	private String name;

	@Column(name="res_area")
	private Byte resArea;

	@Column(name="res_area_words")
	private String resAreaWords;

	@Column(name="row_version")
	private Short rowVersion;

	private Byte unit;

	@Column(name="view_rate")
	private Integer viewRate;

	public WaActConfig() {
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

	public Integer getActRate() {
		return this.actRate;
	}

	public void setActRate(Integer actRate) {
		this.actRate = actRate;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Byte getCustomMenu() {
		return this.customMenu;
	}

	public void setCustomMenu(Byte customMenu) {
		this.customMenu = customMenu;
	}

	public Integer getCycle() {
		return this.cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getCycleLimit() {
		return this.cycleLimit;
	}

	public void setCycleLimit(Integer cycleLimit) {
		this.cycleLimit = cycleLimit;
	}

	public String getCycleLimitWords() {
		return this.cycleLimitWords;
	}

	public void setCycleLimitWords(String cycleLimitWords) {
		this.cycleLimitWords = cycleLimitWords;
	}

	public Integer getEntryRate() {
		return this.entryRate;
	}

	public void setEntryRate(Integer entryRate) {
		this.entryRate = entryRate;
	}

	public Byte getImageShape() {
		return this.imageShape;
	}

	public void setImageShape(Byte imageShape) {
		this.imageShape = imageShape;
	}

	public String getInvalidArea() {
		return this.invalidArea;
	}

	public void setInvalidArea(String invalidArea) {
		this.invalidArea = invalidArea;
	}

	public Integer getIpLimit() {
		return this.ipLimit;
	}

	public void setIpLimit(Integer ipLimit) {
		this.ipLimit = ipLimit;
	}

	public String getIpLimitWords() {
		return this.ipLimitWords;
	}

	public void setIpLimitWords(String ipLimitWords) {
		this.ipLimitWords = ipLimitWords;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getResArea() {
		return this.resArea;
	}

	public void setResArea(Byte resArea) {
		this.resArea = resArea;
	}

	public String getResAreaWords() {
		return this.resAreaWords;
	}

	public void setResAreaWords(String resAreaWords) {
		this.resAreaWords = resAreaWords;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Byte getUnit() {
		return this.unit;
	}

	public void setUnit(Byte unit) {
		this.unit = unit;
	}

	public Integer getViewRate() {
		return this.viewRate;
	}

	public void setViewRate(Integer viewRate) {
		this.viewRate = viewRate;
	}

}