package de.hdm.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Das asynchrone Gegenst�ck des Interface ReportGenerator. Es
 * wird semiautomatisch durch das Google Plugin erstellt und gepflegt.
 * 
 * @author ilonaBrinkmann
 *
 */

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> initReportGeneratorCallback);

}
