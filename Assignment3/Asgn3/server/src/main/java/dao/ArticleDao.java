package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Article;
import util.HibernateUtil;

public class ArticleDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void closeConnection() {
		sessionFactory.close();
	}
	
	public Article findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Article a = (Article) currentSession.get(Article.class, id);
		transaction.commit();
		return a;
	}
	
	public void delete(Article objectToDelete) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.delete(objectToDelete);
		transaction.commit();
	}
	
	public void update(Article objectToUpdate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.update(objectToUpdate);
		transaction.commit();		
	}
	
	public void insert(Article objectToCreate) {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		currentSession.persist(objectToCreate);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticles() {
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction transaction = currentSession.beginTransaction();
		Query q = currentSession.createQuery("from Article");
		List<Article> articles = q.list();
		for(Article a: articles) {
			System.out.println(a);
		}
		transaction.commit();
		return articles;
	}
}
