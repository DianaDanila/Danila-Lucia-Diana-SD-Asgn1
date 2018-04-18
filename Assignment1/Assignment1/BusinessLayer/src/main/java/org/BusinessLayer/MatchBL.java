package org.BusinessLayer;

import pingpong.Match;
import dao.MatchesDao;
import javafx.collections.ObservableList;

public class MatchBL {
	public ObservableList<Match> getMatches() {
		return MatchesDao.getMatches();
	}
}
