package by.htp.main.dao;

import java.util.List;

import by.htp.main.entity.News;

public interface INewsDAO {
	
	List<News> getList() throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	int addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNewses(List<String> idNewses)throws NewsDAOException;
	void saveOrUpdateNews(News news) throws NewsDAOException;

}
