package de.hdm.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.client.LoginInformation;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInformation> callback);

}
