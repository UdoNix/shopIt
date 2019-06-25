package de.hdm.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.client.LoginInformation;
import de.hdm.shared.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{
	
	private static final long serialVersionUID = 1L;
	
	public LoginInformation login(String requestUri) throws IllegalArgumentException{
		return null;
//		UserService userService = UserServiceFactory.getUserService();
//		User user = userService.getCurrentUser();
//		LoginInformation loginInformation = new LoginInformation();
//		
//		if (user != null){
//			loginInformation.setLoggedIn(true);
//			loginInformation.setEmailAdress(user.getEmailAdress());
//			loginInformation.setLogoutUrl(userService.createLogoutURL(requestUri));
//			
//		}
//		else{
//			loginInformation.setLoggedIn(false);
//			loginInformation.setLoginUrl(userService.createLoginURL(requestUri));
//		}
//		return loginInformation;
	}
	
}
