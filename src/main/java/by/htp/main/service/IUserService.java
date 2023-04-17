package by.htp.main.service;

import by.htp.main.entity.User;

public interface IUserService {
	
	User signIn(String login, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;

}
