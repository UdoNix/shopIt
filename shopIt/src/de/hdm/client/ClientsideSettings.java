package de.hdm.client;


import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.LoginService;
import de.hdm.shared.LoginServiceAsync;
import de.hdm.shared.ReportGenerator;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.CommonSettings;
import de.hdm.shared.EditorServiceAsync;


public class ClientsideSettings extends CommonSettings {
	
	private static EditorServiceAsync EditorService = null;
	
	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitigen
	 * Dienst ReportGenerator
	 */
	
	/*
	 * Report Service Proxy für Verbindungsaufnahme mit server-seitigem Dienst ReportGenerator
	 */
	
	private static ReportGeneratorAsync reportGenerator = null;
	
	
	/*
	 * Report Service Proxy für Verbindungsaufnahme mit server-seitigem Dienst ReportGenerator
	 */
	
	private static LoginServiceAsync loginService = null;
	
	/*
	 * Name des client-seitigen Loggers
	 */
	private static final String LOGGER_NAME = "PinnwandVerwaltung Web Client";
	/*
	 * Instanz des client-seitigen Loggers
	 */
	
	/**
	 * Instanz des client-seitigen Loggers
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	/*
	 * Auslesen des applikationsweiten (Client-seitigen) zentralen Loggers
	 * 
	 * @return Logger-Instanz
	 */
	
	public static Logger getLogger() {
		return log;
	}
	
	
	/*
	 * Anlegen und Auslesen des Editor Service
	 */

	public static EditorServiceAsync getEditorServiceAsync() {
		//Prüfung ob eine EditorService-Instanz existiert
		if (EditorService == null) {
			//Instantiierung des EditorService
			EditorService = GWT.create(de.hdm.shared.EditorService.class); //?!
		}
		
		//Rueckgabe des Editor Service
		return EditorService;
	}
	
	
	/*
	 * Anlegen und Auslesen des Login Service
	 */
	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}
	
	/*
	 * Anlegen und Auslesen des Report Generators
	 */
	public static ReportGeneratorAsync getReportGenerator() {
		//Prüfung, ob es eine ReportGenerator-Instanz existiert
		if (reportGenerator == null) {
			//Instantiierung ReportGenerator
			reportGenerator = GWT.create(ReportGenerator.class);
			
			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe(
							"Der ReportGenerator konnte nicht initialisiert werden!");
					
				}

				@Override
				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info(
							"Der ReportGenerator wurde initialisiert.");
					
				}
				
			};
			
			reportGenerator.init(initReportGeneratorCallback);
		}
		
		//Rueckgabe des ReportGenerators
		return reportGenerator;
	}
	
	

}