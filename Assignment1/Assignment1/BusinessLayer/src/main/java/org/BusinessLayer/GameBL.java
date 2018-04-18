package org.BusinessLayer;

import dao.GamesDao;
import javafx.collections.ObservableList;
import pingpong.Game;

public class GameBL {
	public void updateScore(int idg, int side) {
		GamesDao.updateScore(idg, side);
	}

	public Game findById(int idg) {
		return GamesDao.findById(idg);
	}
	
	public void updateScoreByPlayer(int idg, int side, int l) {// =1 p1, =2 p2
		GamesDao.updateScoreByPlayer(idg, side, l);
	}
	
	public int getCurrentGame(int id) {
		return GamesDao.getCurrentGame(id);
	}
	
	public ObservableList<Game> getGames() {
		return GamesDao.getGames();
	}
}
