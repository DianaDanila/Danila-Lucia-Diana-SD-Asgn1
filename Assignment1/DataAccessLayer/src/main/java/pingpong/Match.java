package pingpong;

import java.util.List;

public class Match {
	private int idmatch;

	private User p1;
	private User p2;
	private Tournament t;
	private int idw;

	private List<Game> games;

	public Match() {
	}

	public int getIdmatch() {
		return idmatch;
	}

	public void setIdmatch(int idmatch) {
		this.idmatch = idmatch;
	}

	public Match(int idmatch, User p1, User p2, Tournament t, int idw, List<Game> games) {
		this.idmatch = idmatch;
		this.p1 = p1;
		this.p2 = p2;
		this.t = t;
		this.idw = idw;
		this.games = games;
	}

	@Override
	public String toString() {
		return "Match [idmatch=" + idmatch + ", p1=" + p1 + ", p2=" + p2 + ", t=" + t + ", idw=" + idw + ", games="
				+ games + "]";
	}

	public User getP1() {
		return p1;
	}

	public void setP1(User p1) {
		this.p1 = p1;
	}

	public User getP2() {
		return p2;
	}

	public void setP2(User p2) {
		this.p2 = p2;
	}

	public Tournament getT() {
		return t;
	}

	public void setT(Tournament t) {
		this.t = t;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
