package datamodel.sd.dao.imp.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.sd.dao.TournamentDao;
import datamodel.sd.model.Tournament;
import datamodel.sd.util.HibernateUtil;

public class HibernateTournamentDao implements TournamentDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


	@Override
	public void closeConnection() {
		sessionFactory.close();
	}

	@Override
	public Tournament findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Tournament t = (Tournament) currentSession.get(Tournament.class, id);
		transaction.commit();
		return t;
	}

	@Override
	public void delete(Tournament objectToDelete) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.delete(objectToDelete);
		transaction.commit();
	}

	@Override
	public void deleteByName(String s) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.createQuery("delete Tournament where name= :n").setParameter("n", s).executeUpdate();
		transaction.commit();
	}

	@Override
	public void update(Tournament objectToUpdate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(objectToUpdate);
		transaction.commit();
	}

	@Override
	public void insert(Tournament objectToCreate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.persist(objectToCreate);
		transaction.commit();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tournament> findListByStatus(String s) {
		List<Tournament> list = new ArrayList<Tournament>();
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Tournament where status= :stat").setParameter("stat", s);
		list = q.list();
		transaction.commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tournament> findListByType(String t) {
		List<Tournament> list = new ArrayList<Tournament>();
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Tournament where type= :stat").setParameter("stat", t);
		list = q.list();
		transaction.commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tournament> findByName(String s) {
		List<Tournament> list = new ArrayList<Tournament>();
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Tournament where name= :stat").setParameter("stat", s);
		list = q.list();
		transaction.commit();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tournament> getTournaments() {

		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Tournament");
		List<Tournament> t = q.list();
		transaction.commit();
		return t;
	}

}
