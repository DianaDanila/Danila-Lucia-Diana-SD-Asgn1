package datamodel.sd.dao.factory;

import datamodel.sd.dao.GameDao;
import datamodel.sd.dao.MatchDao;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.impl.HibernateDaoFactory;
import datamodel.sd.dao.factory.impl.JdbcDaoFactory;

public abstract class DaoFactory {

	public enum Type {
		HIBERNATE,
		JDBC;
	}


	protected DaoFactory(){

	}

	public static DaoFactory getInstance(Type factoryType) {
		switch (factoryType) {
			case HIBERNATE:
				return new HibernateDaoFactory();
			case JDBC:
				return new JdbcDaoFactory();
			default:
				throw new IllegalArgumentException("Invalid factory");
		}
	}

	
	public abstract UserDao getUserDao();
	
	public abstract TournamentDao getTournamentDao();
	
	public abstract MatchDao getMatchDao();
	
	public abstract GameDao getGameDao();
}
