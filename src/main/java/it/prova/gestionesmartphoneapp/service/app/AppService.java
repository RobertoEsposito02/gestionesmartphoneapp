package it.prova.gestionesmartphoneapp.service.app;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;

public interface AppService {
	public void setAppDAO(AppDAO appDAO);
	
	public List<App> listAll() throws Exception;
}
