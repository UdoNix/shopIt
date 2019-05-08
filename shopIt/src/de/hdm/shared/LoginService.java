package de.hdm.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.client.LoginInformation;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
		public LoginInformation login(String requestUri);
		
	
		
		
		
	}
	

	
	