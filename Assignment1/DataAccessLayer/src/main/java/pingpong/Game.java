package pingpong;

public class Game {
	

	public Game(int idgames, int scoreP1, int scoreP2, int idm, int idw) {
		this.idgames = idgames;
		this.scoreP1 = scoreP1;
		this.scoreP2 = scoreP2;
		this.idm = idm;
		this.idw = idw;
	}

	@Override
	public String toString() {
		return "Game [idgame=" + idgames + ", scoreP1=" + scoreP1 + ", scoreP2=" + scoreP2 + ", idm=" + idm + ", idw="
				+ idw + "]";
	}

	private int idgames;
	private int scoreP1;
	private int scoreP2;
	private int idm;
	private int idw;

	public Game() {
	}

	

	public int getIdgames() {
		return idgames;
	}

	public void setIdgames(int idgames) {
		this.idgames = idgames;
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

	public int getIdm() {
		return idm;
	}

	public void setIdm(int idm) {
		this.idm = idm;
	}

	public int getIdw() {
		return idw;
	}

	public void setIdw(int idw) {
		this.idw = idw;
	}

}
