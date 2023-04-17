package by.htp.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.dao.DaoException;
import by.htp.main.dao.impl.UserDAO;
import by.htp.main.entity.User;
import by.htp.main.service.IUserService;
import by.htp.main.service.ServiceException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public User signIn(String login, String password) throws ServiceException {
		User user = null;
		try {
			user = userDAO.logination(login, password);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Transactional
	@Override
	public boolean registration(User user) throws ServiceException {

		boolean isRegistered = false;
		try {
			isRegistered = userDAO.registration(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isRegistered;
	}
}
