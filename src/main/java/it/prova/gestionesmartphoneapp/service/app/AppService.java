package it.prova.gestionesmartphoneapp.service.app;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;

public interface AppService {
	public void setAppDAO(AppDAO appDAO);
	
	public List<App> listAll() throws Exception;
	
	public App caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(App appInstance) throws Exception;

	public void inserisciNuovo(App appInstance) throws Exception;

	public void rimuovi(Long idApp) throws Exception;
	
	public void rimuoviAppDaSmartphoneDissociando(Long idApp) throws Exception;
}
