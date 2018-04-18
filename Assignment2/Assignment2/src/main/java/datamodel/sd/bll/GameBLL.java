package datamodel.sd.bll;

import java.util.List;

import datamodel.sd.dao.GameDao;
import datamodel.sd.dao.imp.hibernate.HibernateMatchDao;
import datamodel.sd.model.Game;

public class GameBLL {
	private GameDao gdao;

	public GameBLL(GameDao gdao) {
		this.gdao = gdao;
	}

	public Game findById(int id) {
		return gdao.findById(id);
	}

	public void update(Game g) {
		gdao.update(g);
	}

	public void insert(Game g) {
		gdao.insert(g);
	}

	public void delete(Game g) {
		gdao.delete(g);
	}

	public void updateScore(int idg, int side) {
		Game g = this.findById(idg);
		int idw = g.getWinner();

		if (idw == 0) {
			try {
				int s = 0;
				int os = 0;
				int idm;

				if (side == 1) {
					s = g.getScoreP1();
					os = g.getScoreP2();
					s++;
					g.setScoreP1(s);
					this.update(g);
				} else if (side == 2) {
					s = g.getScoreP2();
					os = g.getScoreP1();
					s++;
					g.setScoreP2(s);
					this.update(g);
				} else {
					System.out.println("invalid side to increment");
				}

				if (s == 11) {
					if (os < 10) {
						idm = g.getMatch().getId();
						idw = new HibernateMatchDao().getPlayerByGameSide(idm, side);
						g.setWinner(idw);
						this.update(g);
					}
				} else {
					if (s - os == 2) {
						idm = g.getMatch().getId();
						idw = new HibernateMatchDao().getPlayerByGameSide(idm, side);
						g.setWinner(idw);
						this.update(g);
					}
				}

			} catch (Exception e) {
				System.out.println("Nu mere" + e);
			}

		} else {
			System.out.println("Invalid update of Score");
		}

	}
	
	public List<Game> getGames() {
		return gdao.getGames();
	}
}
