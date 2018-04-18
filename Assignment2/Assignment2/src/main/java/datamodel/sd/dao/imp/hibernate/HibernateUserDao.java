package datamodel.sd.dao.imp.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.sd.dao.UserDao;
import datamodel.sd.model.User;
import datamodel.sd.util.HibernateUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HibernateUserDao implements UserDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void closeConnection() {
		sessionFactory.close();
	}

	@Override
	public User findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		User u = (User) currentSession.get(User.class, id);
		transaction.commit();
		return u;
	}

	@Override
	public void delete(User objectToDelete) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.delete(objectToDelete);
		transaction.commit();

	}

	@Override
	public void update(User objectToUpdate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(objectToUpdate);
		transaction.commit();

	}

	@Override
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
		if(list.size()==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Invalid username or password!");
			alert.showAndWait();
			return null;
		}
		User u = list.get(0);
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {

		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from User");
		List<User> users = q.list();
		transaction.commit();
		return users;
	}

}
