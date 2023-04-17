package by.htp.main.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.main.dao.INewsDAO;
import by.htp.main.dao.NewsDAOException;
import by.htp.main.entity.News;

@Repository
public class NewsDAO implements INewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getList() throws NewsDAOException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> theQuery = currentSession.createQuery("from News order by date(date_of_creation) desc",
					News.class);
			List<News> newsList = theQuery.getResultList();
			return formatDate(newsList);
		} catch (HibernateException e) {
			throw new NewsDAOException("Error while executing database query", e);
		}
	}

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<News> theQuery = currentSession.createQuery("from News order by date(date_of_creation) desc", News.class);
		theQuery.setMaxResults(count);
		List<News> latestList = theQuery.getResultList();
		return formatDate(latestList);
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		News theNews = currentSession.get(News.class, id);
		return theNews;
	}

	@Override
	public int addNews(News news) throws NewsDAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		int id = (int) currentSession.save(news);

		return id;
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(news);
	}

	@Override
	public void deleteNewses(List<String> idNewses) throws NewsDAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		for (String s : idNewses) {
			Integer id;
			try {
				id = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				throw new NewsDAOException("error while parse to Integer", e);
			}
			News news = currentSession.get(News.class, id);
			currentSession.delete(news);
		}
	}

	@Override
	public void saveOrUpdateNews(News news) throws NewsDAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(news);
	}
	private static List<News> formatDate(List<News> news){
		for(int i = 0; i < news.size(); i++) {
			String[] parts = news.get(i).getNewsDate().split(" ");
			String part = parts[0];
			news.get(i).setNewsDate(part);
		}
		return news;
	}
}
