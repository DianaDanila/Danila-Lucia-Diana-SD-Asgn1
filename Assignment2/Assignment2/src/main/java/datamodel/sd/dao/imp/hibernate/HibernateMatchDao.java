package datamodel.sd.dao.imp.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.sd.dao.MatchDao;
import datamodel.sd.model.Match;
import datamodel.sd.model.User;
import datamodel.sd.util.HibernateUtil;

public class HibernateMatchDao implements MatchDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


	@Override
	public void closeConnection() {
		sessionFactory.close();
	}

	@Override
	public Match findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Match m = (Match) currentSession.get(Match.class, id);
		transaction.commit();
		return m;
	}

	@Override
	public void delete(Match objectToDelete) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.delete(objectToDelete);
		transaction.commit();
	}

	@Override
	public void update(Match objectToUpdate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(objectToUpdate);
		transaction.commit();
	}

	@Override
	public void insert(Match objectToCreate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.persist(objectToCreate);
		transaction.commit();
	}


	public int getPlayerByGameSide(int idm, int side) {
		int id = 0;
		Match m = this.findById(idm);
		User u = new User();
		if (side == 1) {
			u = m.getPlayer1();
		} else {
			u = m.getPlayer2();
		}
		id = u.getId();
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public List<Match> getMatchs() {

		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Match");
		List<Match> m = q.list();
		transaction.commit();
		return m;
	}

}
