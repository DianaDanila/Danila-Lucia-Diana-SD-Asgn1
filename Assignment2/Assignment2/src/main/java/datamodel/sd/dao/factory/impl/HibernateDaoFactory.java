package datamodel.sd.dao.factory.impl;

import datamodel.sd.dao.GameDao;
import datamodel.sd.dao.MatchDao;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.dao.imp.hibernate.HibernateGameDao;
import datamodel.sd.dao.imp.hibernate.HibernateMatchDao;
import datamodel.sd.dao.imp.hibernate.HibernateTournamentDao;
import datamodel.sd.dao.imp.hibernate.HibernateUserDao;

public class HibernateDaoFactory extends DaoFactory {
	@Override
	public UserDao getUserDao() {

		return new HibernateUserDao();
	}

	@Override
	public TournamentDao getTournamentDao() {
		return new HibernateTournamentDao();
	}

	@Override
	public MatchDao getMatchDao() {
		return new HibernateMatchDao();
	}

	@Override
	public GameDao getGameDao() {
		return new HibernateGameDao();
	}

}
