package org.BusinessLayer;

import dao.TournamentsDao;
import javafx.collections.ObservableList;
import pingpong.Tournament;

public class TournamentBL {
	public ObservableList<Tournament> getTournaments() {
		return TournamentsDao.getTournamentss();
	}

	public void insert(Tournament t) {
		TournamentsDao.insert(t);
	}

	public void updateN(int id, String n) {
		TournamentsDao.updateName(id, n);
	}

	public void delete(int id) {
		TournamentsDao.deleteById(id);
	}
}
