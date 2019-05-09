package de.hdm.client;


import java.io.Serializable;

/* Mit Hilfe der LoginInfo Klasse soll der Login RPC-Dienst die Logininformation eines Nutzers als
 * Ergebnis ausgeben.
 */

	public class LoginInformation implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		/* 
		 * Prüfung, ob Anmeldung schon stattfand
		 */
		
		private boolean alreadyLoggedIn = false;
		
		/*
		 * Speichern der LoginURL und der LogoutURL
		 */
		
		private String loginURL = "";
		
		
		private String logoutURL = "";
		
		
		/*
		 * Speichern der hinterlegten Email-Adresse
		 */
		
		private String emailAddress = "";

		/*
		 * Abspeichern des Nutzernames
		 */
		
		private String nickname = "";
		
		/*
		 * Prüfung, ob Nutzer bereits eingeloggt ist
		 */

		public boolean isLoggedIn() {
			return alreadyLoggedIn;
		}
		
		/*
		 * Setzen des Logins
		 */

		public void setLoggedIn(boolean loggedIn) {
			this.alreadyLoggedIn = loggedIn;
		}

		/*
		 *  Auslesen der LoginURL
		 */
		public String getLoginURL() {
			return loginURL;
		}

		/*
		 * Setzen der LoginURL
		 */
		public void setLoginURL(String loginURL) {
			this.loginURL = loginURL;
		}

		/*
		 * Auslesen des LogoutURL
		 */
		
		public String getLogoutURL() {
			return logoutURL;
		}

		/*
		 * Setzen der LogoutURL 
		 */
		
		public void setLogoutURL(String logoutURL) {
			this.logoutURL = logoutURL;
		}
		
		/*
		 * Auslesen der E-Mail Adresse
		 * 
		 * @return emailAdresse
		 */

		public String getEmailAddress() {
			return emailAddress;
		}
		
		/*
		 * Setzen der E-Mail Adresse
		 * 
		 * @param emailAddress
		 */

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		/*
		  * Auslesen vom Nickname
		 * 
		 * @return nickname
		 */

		public String getNickname() {
			return nickname;
		}

		/*
		 * * Setzen vom Nickname
		 * 
		 * @param nickname
		 */
		
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
	}
		




