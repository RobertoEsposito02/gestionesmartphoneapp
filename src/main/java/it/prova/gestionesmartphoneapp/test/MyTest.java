package it.prova.gestionesmartphoneapp.test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.app.AppService;
import it.prova.gestionesmartphoneapp.service.smartphone.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.smartphone.SmartphoneService;

public class MyTest {
	public static void main(String[] args) {
		SmartphoneService smartphoneService = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appService = MyServiceFactory.getAppServiceInstance();
		
		try {
			System.out.println("ci sono: " + appService.listAll().size() + " app");
			System.out.println("ci sono: " + smartphoneService.listAll().size() + " smartphone");
			
			testInserisciNuovoSmartphone(smartphoneService);
			System.out.println("ci sono: " + smartphoneService.listAll().size() + " smartphone");
			
			testAggiornaSmartphone(smartphoneService);
			
			testInserisciNuovaApp(appService);
			System.out.println("ci sono: " + appService.listAll().size() + " app");
			
			testAggiornaApp(appService);
			
			testAggiungiAppASmartphone(smartphoneService, appService);
			
			//testRimuoviAppDaSmartphone(smartphoneService, appService);
			
			testRimozioneSmartphoneConDueApp(smartphoneService, appService);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testInserisciNuovoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception{
		System.out.println("---------------testInserisciNuovoSmartphone INIZIO-----------");
		
		Smartphone nuovaoSmartphone = new Smartphone("telefono1","3T",800,"2.0.0");
		smartphoneServiceInstance.inserisciNuovo(nuovaoSmartphone);
		if(nuovaoSmartphone.getId() == null)
			throw new RuntimeException("testInserisciNuovoSmartphone: FALLITO record non inserito");
		
		System.out.println("---------------testInserisciNuovoSmartphone PASSED-----------");
	}
	
	private static void testAggiornaSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception{
		System.out.println("---------------testAggiornaSmartphone INIZIO-----------");
		
		Smartphone nuovaoSmartphone = new Smartphone("telefono1","3T",800,"2.0.0");
		smartphoneServiceInstance.inserisciNuovo(nuovaoSmartphone);
		if(nuovaoSmartphone.getId() == null)
			throw new RuntimeException("testAggiornaSmartphone: FALLITO record non inserito");
		
		String nuovaVersioneOS = "2.0.2";
		nuovaoSmartphone.setVersioneOS(nuovaVersioneOS);
		if(nuovaoSmartphone.getVersioneOS() != nuovaVersioneOS)
			throw new RuntimeException("testAggiornaSmartphone: FALLITO record non aggiornato");
		
		
		System.out.println("---------------testAggiornaSmartphone PASSED-----------");
	}
	
	private static void testInserisciNuovaApp(AppService appServiceInstance) throws Exception{
		System.out.println("---------------testInserisciNuovaApp INIZIO-----------");
		
		App nuovaAppDaInserire = new App("app1",new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),"1.0.0");
		appServiceInstance.inserisciNuovo(nuovaAppDaInserire);
		if(nuovaAppDaInserire.getId() == null)
			throw new RuntimeException("testInserisciNuovaApp: FALLITO valore non valido");
		
		System.out.println("---------------testInserisciNuovaApp PASSED-----------");
	}
	
	private static void testAggiornaApp(AppService appServiceInstance) throws Exception{
		System.out.println("---------------testAggiornaApp INIZIO-----------");
		
		App nuovaAppDaInserire = new App("app1",new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),"1.0.0");
		appServiceInstance.inserisciNuovo(nuovaAppDaInserire);
		if(nuovaAppDaInserire.getId() == null)
			throw new RuntimeException("testAggiornaApp: FALLITO valore non valido");
		
		String nuovaVersione = "1.0.1";
		LocalDateTime upDateTime = nuovaAppDaInserire.getUpdateDateTime();
		//String localdateUpdate = upDateTime.format(null);
		//Date nuovaDate = new SimpleDateFormat("yyyy/MM/dd").parse(localdateUpdate);
		//nuovaAppDaInserire.setDataUltimoAggiornamento(nuovaDate);
		nuovaAppDaInserire.setVersione(nuovaVersione);
		nuovaAppDaInserire.setUpdateDateTime(upDateTime);
		appServiceInstance.aggiorna(nuovaAppDaInserire);
		
		if(!(nuovaAppDaInserire.getVersione().equals(nuovaVersione)))
			throw new RuntimeException("testAggiornaApp: FALLITO valore non aggiornato");
		
		System.out.println("---------------testAggiornaApp PASSED-----------");
	}
	
	private static void testAggiungiAppASmartphone(SmartphoneService smartphoneServiceInstance, AppService appServiceInstance) throws Exception{
		System.out.println("---------------testAggiungiAppASmartphone INIZIO-----------");
		
		Smartphone nuovaoSmartphone = new Smartphone("telefono1","3T",800,"2.0.0");
		smartphoneServiceInstance.inserisciNuovo(nuovaoSmartphone);
		if(nuovaoSmartphone.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO smartphone non inserito");
		
		App nuovaAppDaInserire = new App("app1",new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),"1.0.0");
		appServiceInstance.inserisciNuovo(nuovaAppDaInserire);
		if(nuovaAppDaInserire.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO app non inserita");
		
		smartphoneServiceInstance.aggiungiApp(nuovaoSmartphone, nuovaAppDaInserire);
		/*
		Smartphone smartphoneReload = smartphoneServiceInstance.caricaSingoloElemento(nuovaoSmartphone.getId());
		if(smartphoneReload.getApps().isEmpty())
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO app non collegata a smartphone");
		*/
		System.out.println("---------------testAggiungiAppASmartphone PASSED-----------");
	}
	
	private static void testRimuoviAppDaSmartphone(SmartphoneService smartphoneServiceInstance, AppService appServiceInstance) throws Exception{
		System.out.println("---------------testRimuoviAppDaSmartphone INIZIO-----------");
		
		Smartphone nuovaoSmartphone = new Smartphone("telefono1","3T",800,"2.0.0");
		smartphoneServiceInstance.inserisciNuovo(nuovaoSmartphone);
		if(nuovaoSmartphone.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO smartphone non inserito");
		
		App nuovaAppDaInserire = new App("app1",new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),"1.0.0");
		appServiceInstance.inserisciNuovo(nuovaAppDaInserire);
		if(nuovaAppDaInserire.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO app non inserita");
		
		smartphoneServiceInstance.aggiungiApp(nuovaoSmartphone, nuovaAppDaInserire);
		
		smartphoneServiceInstance.rimuoviApp(nuovaoSmartphone, nuovaAppDaInserire);
		
		
		System.out.println("---------------testRimuoviAppDaSmartphone PASSED-----------");
	}
	
	private static void testRimozioneSmartphoneConDueApp(SmartphoneService smartphoneServiceInstance, AppService appServiceInstance) throws Exception{
		System.out.println("---------------testRimuoviAppDaSmartphone INIZIO-----------");
		
		Smartphone nuovaoSmartphone = new Smartphone("telefono1","3T",800,"2.0.0");
		smartphoneServiceInstance.inserisciNuovo(nuovaoSmartphone);
		if(nuovaoSmartphone.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO smartphone non inserito");
		
		App nuovaAppDaInserire = new App("app1",new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),"1.0.0");
		appServiceInstance.inserisciNuovo(nuovaAppDaInserire);
		if(nuovaAppDaInserire.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO app non inserita");
		
		App nuovaAppDaInserire2 = new App("app1",new SimpleDateFormat("yyyy/MM/dd").parse("2022/01/01"),new SimpleDateFormat("yyyy/MM/dd").parse("2022/04/01"),"1.0.0");
		appServiceInstance.inserisciNuovo(nuovaAppDaInserire2);
		if(nuovaAppDaInserire2.getId() == null)
			throw new RuntimeException("testAggiungiAppASmartphone: FALLITO app non inserita");
		
		smartphoneServiceInstance.aggiungiApp(nuovaoSmartphone, nuovaAppDaInserire);
		smartphoneServiceInstance.aggiungiApp(nuovaoSmartphone, nuovaAppDaInserire2);
		
		Smartphone smartphoneReloaded = smartphoneServiceInstance.caricaSingoloElemento(nuovaoSmartphone.getId());
		smartphoneServiceInstance.rimuovi(smartphoneReloaded.getId());
		
		System.out.println("---------------testRimuoviAppDaSmartphone PASSED-----------");
	}
}
