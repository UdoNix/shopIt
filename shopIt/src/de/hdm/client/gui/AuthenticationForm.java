package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

@Deprecated
public class AuthenticationForm {
	
		private Label welcomeLbl = new Label("Zugang über deinen Google Account");
		private Button googleBtn = new Button();
		private Image GoogleImg = new Image("images/googleLogo.png");
		private Image ourLogo = new Image("");
		private String loginUrl;
		
		public AuthenticationForm() {
		}
		
		public AuthenticationForm(String loginUrl) {
			this.loginURL = loginUrl;		
		}
		
		public void onLoad(){
			
			
		}
		
		private class LoginClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				
				//Weiterleitung des Users an Google Login
				Window.Location.assign(loginUrl);
			}
			
		}

	}


