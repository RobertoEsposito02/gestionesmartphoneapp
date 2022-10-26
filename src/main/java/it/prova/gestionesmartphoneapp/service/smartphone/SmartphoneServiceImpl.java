package it.prova.gestionesmartphoneapp.service.smartphone;

import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;

public class SmartphoneServiceImpl implements SmartphoneService{

	private SmartphoneDAO smartphoneDAO;
	
	@Override
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO) {
		this.smartphoneDAO = smartphoneDAO;
	}

}
