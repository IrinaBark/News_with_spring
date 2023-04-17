package by.htp.main.dao;

import by.htp.main.entity.User;

public interface IUserDAO {
	
	User logination(String login, String password) throws DaoException;
	boolean registration(User user) throws DaoException;

}
