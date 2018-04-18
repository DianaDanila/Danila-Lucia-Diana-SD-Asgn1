package datamodel.sd.dao.imp.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.sd.dao.GameDao;
import datamodel.sd.model.Game;
import datamodel.sd.util.HibernateUtil;

public class HibernateGameDao implements GameDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void closeConnection() {
		sessionFactory.close();
	}

	@Override
	public Game findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Game g = (Game) currentSession.get(Game.class, id);
		transaction.commit();
		return g;
	}

	@Override
	public void delete(Game objectToDelete) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.delete(objectToDelete);
		transaction.commit();
	}

	@Override
	public void update(Game objectToUpdate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(objectToUpdate);
		transaction.commit();
	}

	@Override
	public void insert(Game objectToCreate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.persist(objectToCreate);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Game> getGames() {

		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Game");
		List<Game> g = q.list();
		transaction.commit();
		return g;
	}

}
