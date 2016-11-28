package yingjianhua.vote.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_act_vote_prize_record database table.
 * 
 */
@Entity
@Table(name="wa_act_vote_prize_record")
@NamedQuery(name="WaActVotePrizeRecord.findAll", query="SELECT w FROM WaActVotePrizeRecord w")
public class WaActVotePrizeRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkey;

	private int account;

	private int prizeitem;

	@Column(name="row_version")
	private short rowVersion;

	private int vote;

	private int winner;

	public WaActVotePrizeRecord() {
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

	public int getVote() {
		return this.vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public int getWinner() {
		return this.winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

}