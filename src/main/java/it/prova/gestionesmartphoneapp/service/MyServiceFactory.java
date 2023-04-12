package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.MyDAOFactory;

public class MyServiceFactory {
	private static SmartphoneService smartphoneServiceInstance = null;
	private static AppService appServiceInstance = null;

	public static SmartphoneService getSmartphoneServiceInstance() {
		if (smartphoneServiceInstance == null)
			smartphoneServiceInstance = new SmartphoneServiceImpl();

		smartphoneServiceInstance.setSmartphoneDAO(MyDAOFactory.getSmartphoneDAOInstance());

		return smartphoneServiceInstance;
	}

	public static AppService getAppServiceInstance() {
		if (appServiceInstance == null)
			appServiceInstance = new AppServiceImpl();

		appServiceInstance.setAppDAO(MyDAOFactory.getAppDAOInstance());

		return appServiceInstance;
	}
}
