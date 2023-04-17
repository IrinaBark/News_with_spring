package by.htp.main.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.main.dao.DaoException;
import by.htp.main.dao.IUserDAO;
import by.htp.main.entity.User;
import by.htp.main.entity.UserRole;
import by.htp.main.entity.UserStatus;

import org.mindrot.jbcrypt.BCrypt;

@Repository
public class UserDAO implements IUserDAO{
	
	private static final int DEFAULT_ROLE_ID = 1;
	private static final int DEFAULT_STATUS_ID = 1;
	private static final String SQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User logination(String login, String password) throws DaoException {

		User user;

		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> theQuery = currentSession.createQuery("from User user WHERE user.login = :login", User.class);
		theQuery.setParameter("login", login);

		user = theQuery.uniqueResult();
		if (user != null && BCrypt.checkpw(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean registration(User user) throws DaoException {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User user WHERE user.login = :login", User.class);
		theQuery.setParameter("login", user.getLogin());
         
		try {
		User theUser = theQuery.uniqueResult();

		if (theUser != null) {
			return false;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SQL_DATETIME_FORMAT);
			LocalDateTime date = LocalDateTime.now();
			String dateTime = formatter.format(date);

			String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

			user.setPassword(hashpw);
			user.setDateOfCreation(dateTime);
			user.setUserRole(new UserRole(DEFAULT_ROLE_ID));
			user.setUserStatus(new UserStatus(DEFAULT_STATUS_ID));

			currentSession.save(user);
			return true;
		}
		} catch (HibernateException e) {
			throw new DaoException("error during working with database",e);
		}
	}

}
