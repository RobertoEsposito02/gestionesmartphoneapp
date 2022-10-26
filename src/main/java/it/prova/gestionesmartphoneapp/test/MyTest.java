package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.service.app.AppService;
import it.prova.gestionesmartphoneapp.service.smartphone.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneService;

public class MyTest {
	public static void main(String[] args) {
		SmartphoneService smartphoneService = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appService = MyServiceFactory.getAppServiceInstance();
		
		try {
			System.out.println("ci sono: " + appService.listAll().size() + " elementi");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}
}
