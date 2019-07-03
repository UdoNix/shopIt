package de.hdm.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.client.LoginInformation;
import de.hdm.shared.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = 1L;

	public LoginInformation login(String requestUri) throws IllegalArgumentException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInformation loginInformation = new LoginInformation();

		if (user != null) {
			loginInformation.setLoggedIn(true);
			loginInformation.setEmailAddress(user.getEmail());
			loginInformation.setLogoutURL(userService.createLogoutURL(requestUri));
			getThreadLocalRequest().getSession().setAttribute("email", loginInformation.getEmailAddress());
		} else {
			loginInformation.setLoggedIn(false);
			loginInformation.setLoginURL(userService.createLoginURL(requestUri));
		}
		return loginInformation;
	}

}
