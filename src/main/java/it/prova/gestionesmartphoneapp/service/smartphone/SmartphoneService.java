package it.prova.gestionesmartphoneapp.service.smartphone;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneService {
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);
	
	public List<Smartphone> listAll() throws Exception;
	
	public Smartphone caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Smartphone smartphoneInstance) throws Exception;

	public void inserisciNuovo(Smartphone smartphoneInstance) throws Exception;

	public void rimuovi(Long idSmartphone) throws Exception;
	
	public void aggiungiApp(Smartphone smartphone ,App app) throws Exception;
	
	public void rimuoviApp(Smartphone smartphone, App app) throws Exception;
}
