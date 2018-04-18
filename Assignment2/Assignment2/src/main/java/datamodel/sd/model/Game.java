package datamodel.sd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private int scoreP1;

	@Column
	private int scoreP2;

	@Column
	private int winner;

	@OneToOne
	@JoinColumn
	private Match match;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScoreP1() {
		return scoreP1;
	}

	public void setScoreP1(int scoreP1) {
		this.scoreP1 = scoreP1;
	}

	public int getScoreP2() {
		return scoreP2;
	}

	public void setScoreP2(int scoreP2) {
		this.scoreP2 = scoreP2;
	}

	public int getWinner() {
		return winner;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

}
