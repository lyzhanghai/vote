package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_vote_prize database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_prize")
@NamedQuery(name="WaActVotePrize.findAll", query="SELECT w FROM WaActVotePrize w")
public class WaActVotePrize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkey;

	private int account;

	private int amount;

	private int prize;

	private int prizeitem;

	@Column(name="row_version")
	private short rowVersion;

	private int sort;

	private String unit;

	private int vote;

	public WaActVotePrize() {
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

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrize() {
		return this.prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public int getPrizeitem() {
		return this.prizeitem;
	}

	public void setPrizeitem(int prizeitem) {
		this.prizeitem = prizeitem;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getVote() {
		return this.vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

}