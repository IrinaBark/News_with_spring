package by.htp.main.service;

import java.util.List;

import by.htp.main.entity.News;

public interface INewsService {
	
	  int save(News news) throws ServiceException;
	  void find(News news) throws ServiceException;
	  void update(News news) throws ServiceException;
	  void delete(List<String> idNews)  throws ServiceException;
	  
	  List<News> latestList(int count)  throws ServiceException;
	  List<News> list()  throws ServiceException;
	  News findById(int id) throws ServiceException;

}
