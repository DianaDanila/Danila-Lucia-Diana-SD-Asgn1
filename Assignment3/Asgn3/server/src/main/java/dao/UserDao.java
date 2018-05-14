package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.User;
import util.HibernateUtil;

public class UserDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void closeConnection() {
		sessionFactory.close();
	}

	public User findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		User u = (User) currentSession.get(User.class, id);
		transaction.commit();
		return u;
	}

	public void delete(User objectToDelete) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.delete(objectToDelete);
		transaction.commit();

	}

	public void update(User objectToUpdate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(objectToUpdate);
		transaction.commit();

	}

	public void insert(User objectToCreate) {

		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.persist(objectToCreate);
		transaction.commit();

	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from User where name= :stat").setParameter("stat", name);
		List<User> list = q.list();
		transaction.commit();
		try {
			User u = list.get(0);
			return u;
		} catch (IndexOutOfBoundsException e){
			return null;
		} 
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from User where admin= :stat").setParameter("stat", false);
		List<User> users = q.list();
		transaction.commit();
		return users;
	}

}
