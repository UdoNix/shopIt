package de.hdm.server;

import java.util.logging.Logger;

public class ServersideSettings {

	private static final String LOGGER_NAME = "DatingApp Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);;

	public static Logger getLogger() {

		return log;
	}
	
}
