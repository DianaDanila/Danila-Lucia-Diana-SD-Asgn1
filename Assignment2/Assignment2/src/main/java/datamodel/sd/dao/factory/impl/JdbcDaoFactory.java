package datamodel.sd.dao.factory.impl;

import datamodel.sd.dao.GameDao;
import datamodel.sd.dao.MatchDao;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.dao.impl.jdbc.JdbcGameDao;
import datamodel.sd.dao.impl.jdbc.JdbcMatchDao;
import datamodel.sd.dao.impl.jdbc.JdbcTournamentDao;
import datamodel.sd.dao.impl.jdbc.JdbcUserDao;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		return new JdbcUserDao();
	}

	@Override
	public TournamentDao getTournamentDao() {
		return new JdbcTournamentDao();
	}

	@Override
	public MatchDao getMatchDao() {
		return new JdbcMatchDao();
	}

	@Override
	public GameDao getGameDao() {
		return new JdbcGameDao();
	}
}
