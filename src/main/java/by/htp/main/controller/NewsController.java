package by.htp.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import by.htp.main.entity.News;
import by.htp.main.entity.NewsStatus;
import by.htp.main.entity.User;
import by.htp.main.service.ServiceException;
import by.htp.main.service.impl.NewsServiceImpl;
import by.htp.main.service.impl.UserServiceImpl;
import by.htp.main.validator.AccessValidator;
import by.htp.main.validator.AccessValidator.AccessValidationBuilder;
import by.htp.main.validator.UserValidator;
import by.htp.main.validator.UserValidator.UserValidationBuilder;

@Controller
@RequestMapping("/news")
public class NewsController {

	private static final String NEWS_PARAM = "news";
	private static final String PRESENTATION_PARAM = "presentation";
	private static final String PRESENTATION_VALUE_FOR_NEWS_LIST = "newsList";
	private static final String PRESENTATION_VALUE_FOR_ADD_NEWS = "addNews";
	private static final String PRESENTATION_VALUE_FOR_EDIT_NEWS = "updateNews";
	private static final String PRESENTATION_PARAM_VALUE_FOR_VIEW_NEWS = "viewNews";
	private static final String PRESENTATION_VALUE_FOR_CONFIRMATION_PAGE = "confirmationPage";
	private static final String PRESENTATION_VALUE_FOR_ERROR_PAGE = "error";
	private static final String USER_INFO_MESSAGE_PARAM = "user_info_message";
	private static final String INFO_MESSAGE_PARAM = "info_message";
	private static final String BASE_LAYOUT = "baseLayout";
	private static final String JSP_NEWS_TO_DELETE_PARAM = "idNews";
	private static final int NUMBER_OF_NEWS = 5;

	private static final String INFO_MESSAGE_LOCAL_KEY_UPDATE = "local.updateNews.name.updated_successfully";
	private static final String ERROR_NO_SELECTED_NEWS_LOCAL_KEY = "local.error.name.no_selected_news";

	private static final String GUEST_ROLE_VALUE = "guest";
	private static final String USER_PARAM = "user";
	private static final String ID_PARAM = "id";
	private static final String ROLE_PARAM = "role";
	private static final String USER_STATUS_ACTIVE = "active";
	private static final String USER_STATUS_NOT_ACTIVE = "not active";
	private static final String DEFAULT_NEWS_STATUS = "approved";

	private static final String ERROR_MESSAGE_PARAM = "errorMessage";
	private static final String USER_ROLE_PARAM = "role";
	private static final String QUEST_ROLE = "guest";
	private static final String USER_NOT_ACTIVE_VALUE = "not active";
	private static final String USER_INFO_PARAM = "user_info_message";
	private static final String USER_DATA_PARAM = "userData";

	private static final String AUTHENTIFICATION_ERROR_PARAM = "AuthenticationError";
	private static final String AUTHENTIFICATION_ERROR_LOCAL_KEY = "local.header.name.login_error";
	private static final String PRESENTATION_PARAM_VALUE_FOR_REGISTRATION = "registration";

	private static final String SUCCESSFUL_REGISTRATION_LOCAL_KEY = "local.header.name.successful_registration";
	private static final String FAILED_REGISTRATION_LOCAL_KEY = "local.header.name.failed_registration";
	private static final String INFO_MESSAGE_LOCAL_KEY_SAVE = "local.saveNews.name.saved_successfully";
	private static final String ERROR_ACCESS_DENIED = "local.errorValidation.name.access_denyed";
	private static final String ERROR_NOT_AUTHORIZED = "local.errorValidation.name.not_authorized";

	@Autowired
	private NewsServiceImpl newsService;
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping("/goToBasePage")
	public String basePage(Model theModel) {

		List<News> news = new ArrayList<News>();
		try {
			news = newsService.latestList(NUMBER_OF_NEWS);
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		theModel.addAttribute(NEWS_PARAM, news);
		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_NEWS_LIST);

		return BASE_LAYOUT;
	}

	@RequestMapping("/updateNews")
	public String updateNews(@ModelAttribute("news") @Valid News news, BindingResult bindingResult, Model theModel,
			HttpSession session) {

		AccessValidationBuilder builder = new AccessValidator.AccessValidationBuilder();
		AccessValidator validator = builder.checkPermission(session).validate();

		if (!validator.getErrors().isEmpty()) {
			theModel.addAttribute(ERROR_MESSAGE_PARAM, validator.buildErrorMessage());
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		if (bindingResult.hasErrors()) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_EDIT_NEWS);
			return BASE_LAYOUT;
		}
		NewsStatus newsStatus = new NewsStatus(2, DEFAULT_NEWS_STATUS);
		news.setNewsStatus(newsStatus);
		User user = (User) session.getAttribute(USER_DATA_PARAM);
		news.setUser(user);
		try {
			newsService.update(news);
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		theModel.addAttribute(USER_INFO_MESSAGE_PARAM, INFO_MESSAGE_LOCAL_KEY_UPDATE);
		theModel.addAttribute(ID_PARAM, news.getIdNews());
		return "redirect:goToViewNews";

	}

	@RequestMapping("/addNews")
	public String addNews(@ModelAttribute("news") @Valid News news, BindingResult bindingResult, HttpSession session,
			Model theModel) {

		AccessValidationBuilder builder = new AccessValidator.AccessValidationBuilder();
		AccessValidator validator = builder.checkPermission(session).validate();

		if (!validator.getErrors().isEmpty()) {
			theModel.addAttribute(ERROR_MESSAGE_PARAM, validator.buildErrorMessage());
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}

		if (bindingResult.hasErrors()) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ADD_NEWS);
			return BASE_LAYOUT;
		}

		NewsStatus newsStatus = new NewsStatus(2, DEFAULT_NEWS_STATUS);
		news.setNewsStatus(newsStatus);
		User user = (User) session.getAttribute(USER_DATA_PARAM);
		news.setUser(user);

		int id = 0;
		try {
			id = newsService.save(news);
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		theModel.addAttribute(USER_INFO_MESSAGE_PARAM, INFO_MESSAGE_LOCAL_KEY_SAVE);
		theModel.addAttribute(ID_PARAM, id);
		return "redirect:goToViewNews";

	}

	@RequestMapping("/goToAddNews")
	public String goToAddNews(Model theModel, HttpSession session) {

		AccessValidationBuilder builder = new AccessValidator.AccessValidationBuilder();
		AccessValidator validator = builder.checkPermission(session).validate();

		if (!validator.getErrors().isEmpty()) {
			theModel.addAttribute(ERROR_MESSAGE_PARAM, validator.buildErrorMessage());
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		News news = new News();
		theModel.addAttribute(NEWS_PARAM, news);
		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ADD_NEWS);
		return BASE_LAYOUT;
	}

	@RequestMapping("/goToEditNews")
	public String goToEditNews(@RequestParam("id") String id, Model theModel, HttpSession session) {
		
		AccessValidationBuilder builder = new AccessValidator.AccessValidationBuilder();
		AccessValidator validator = builder.checkPermission(session).validate();

		if (!validator.getErrors().isEmpty()) {
			theModel.addAttribute(ERROR_MESSAGE_PARAM, validator.buildErrorMessage());
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}

		News news = new News();
		try {
			news = newsService.findById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}

		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_EDIT_NEWS);
		theModel.addAttribute(NEWS_PARAM, news);
		return BASE_LAYOUT;
	}

	@RequestMapping("/goToErrorPage")
	public String goToEditNews(Model theModel) {

		theModel.addAttribute(ERROR_MESSAGE_PARAM, ERROR_ACCESS_DENIED);
		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
		return BASE_LAYOUT;
	}

	@RequestMapping("/goToConfirmationPage")
	public String goToConfirmationPage(HttpServletRequest request, Model theModel) {

		String[] idNews = request.getParameterValues(JSP_NEWS_TO_DELETE_PARAM);

		if (idNews == null) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			theModel.addAttribute(ERROR_MESSAGE_PARAM, ERROR_NO_SELECTED_NEWS_LOCAL_KEY);
			return BASE_LAYOUT;
		}

		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_CONFIRMATION_PAGE);
		theModel.addAttribute(JSP_NEWS_TO_DELETE_PARAM, idNews);

		return BASE_LAYOUT;

	}

	@RequestMapping("/deleteNews")
	public String deleteNews(HttpServletRequest request, Model theModel, HttpSession session) {
		
		AccessValidationBuilder builder = new AccessValidator.AccessValidationBuilder();
		AccessValidator validator = builder.checkPermission(session).validate();

		if (!validator.getErrors().isEmpty()) {
			theModel.addAttribute(ERROR_MESSAGE_PARAM, validator.buildErrorMessage());
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}

		String[] idNews = request.getParameterValues(JSP_NEWS_TO_DELETE_PARAM);
		List<String> idNewsToDelete = new ArrayList<String>();

		for (String idOfNews : idNews) {
			idNewsToDelete.add(idOfNews);
		}
		try {
			newsService.delete(idNewsToDelete);
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		return "redirect:goToNewsList";
	}

	@RequestMapping("/goToNewsList")
	public String goToNewsList(Model theModel) {

		List<News> news = new ArrayList<News>();
		try {
			news = newsService.list();
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		theModel.addAttribute(NEWS_PARAM, news);
		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_NEWS_LIST);
		return BASE_LAYOUT;
	}

	@RequestMapping("/goToViewNews")
	public String goToViewNews(@RequestParam("id") String id, Model theModel) {
		News news = new News();
		try {
			news = newsService.findById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_PARAM_VALUE_FOR_VIEW_NEWS);
		theModel.addAttribute(NEWS_PARAM, news);

		return BASE_LAYOUT;
	}

	@RequestMapping("/doSignIn")
	public String signIn(@RequestParam("login") String login, @RequestParam("password") String password,
			HttpSession session, Model theModel) {

		User user;
		UserValidationBuilder userValidationBuilder = new UserValidator.UserValidationBuilder();
		UserValidator validator = userValidationBuilder.checkLogin(login).checkPassword(password).validate();

		if (!validator.getErrors().isEmpty()) {
			theModel.addAttribute(AUTHENTIFICATION_ERROR_PARAM, AUTHENTIFICATION_ERROR_LOCAL_KEY);
			return "redirect:goToNewsList";
		} else {
			try {
				user = userService.signIn(login, password);
				if (user != null && !user.getUserRole().getTitle().equals(GUEST_ROLE_VALUE)) {

					session.setAttribute(USER_PARAM, USER_STATUS_ACTIVE);
					session.setAttribute(ROLE_PARAM, user.getUserRole().getTitle());
					session.setAttribute(USER_DATA_PARAM, user);
					return "redirect:goToNewsList";
				} else {
					session.setAttribute(USER_PARAM, USER_STATUS_NOT_ACTIVE);
					theModel.addAttribute(AUTHENTIFICATION_ERROR_PARAM, ERROR_NOT_AUTHORIZED);
					return "redirect:goToNewsList";
				}
			} catch (ServiceException e) {
				theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
				return BASE_LAYOUT;
			}
		}
	}

	@RequestMapping("/signOut")
	public String signOut(HttpSession session) {

		session.setAttribute(USER_PARAM, USER_NOT_ACTIVE_VALUE);
		session.setAttribute(USER_ROLE_PARAM, QUEST_ROLE);
		session.setAttribute(USER_INFO_PARAM, null);
		return "redirect:goToBasePage";
	}

	@RequestMapping("/goToRegistrationPage")
	public String goToRegistrationPage(Model theModel) {
		
		theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_PARAM_VALUE_FOR_REGISTRATION);
		theModel.addAttribute(USER_PARAM, new User());
		return BASE_LAYOUT;
	}

	@RequestMapping("/registration")
	public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model theModel) {

		if (bindingResult.hasErrors()) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_PARAM_VALUE_FOR_REGISTRATION);
			return BASE_LAYOUT;
		}
		boolean registeredSuccessfully = false;
		try {
			registeredSuccessfully = userService.registration(user);
			if (registeredSuccessfully) {
				theModel.addAttribute(INFO_MESSAGE_PARAM, SUCCESSFUL_REGISTRATION_LOCAL_KEY);
				return "redirect:goToBasePage";
			} else {
				theModel.addAttribute(INFO_MESSAGE_PARAM, FAILED_REGISTRATION_LOCAL_KEY);
				return "redirect:goToBasePage";
			}
		} catch (ServiceException e) {
			theModel.addAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_ERROR_PAGE);
			return BASE_LAYOUT;
		}
	}
}
