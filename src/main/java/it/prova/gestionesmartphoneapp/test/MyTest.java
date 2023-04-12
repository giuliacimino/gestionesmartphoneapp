package it.prova.gestionesmartphoneapp.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class MyTest {
	public static void main(String[] args) {
		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();

		try {

			
			initApp(appServiceInstance);
			System.out.println("In tabella App ci sono " + appServiceInstance.listAllApps().size() + " elementi.");
//			System.out.println("nella tabella Smartphone sono presenti " +smartphoneServiceInstance.listAllSmartphone().size() + " elementi." );
//			testInserisciNuovo(smartphoneServiceInstance);
//			System.out.println("nella tabella Smartphone sono presenti " +smartphoneServiceInstance.listAllSmartphone().size() + " elementi." );
//			
//			testAggiorna(smartphoneServiceInstance);
//			System.out.println("nella tabella Smartphone sono presenti " +smartphoneServiceInstance.listAllSmartphone().size() + " elementi." );

//			testInserisciNuovaApp(appServiceInstance);
//			System.out.println("nella tabella App sono presenti " + appServiceInstance.listAllApps().size() + " elementi." );

//			testInstallaAppSuSmartphone(smartphoneServiceInstance, appServiceInstance);
//			System.out.println("nella tabella App sono presenti " + appServiceInstance.listAllApps().size() + " elementi." );

//			testDisinstallaAppDaSmartphone(smartphoneServiceInstance, appServiceInstance);
//			System.out.println("nella tabella App sono presenti " + appServiceInstance.listAllApps().size() + " elementi.");
			
//			testRimozioneSmartphoneConDueApp(smartphoneServiceInstance, appServiceInstance);
//			System.out.println("nella tabella Smartphone sono presenti " +smartphoneServiceInstance.listAllSmartphone().size() + " elementi." );
			
//			testAggiornaVersioneOS(smartphoneServiceInstance);
//			System.out.println("nella tabella Smartphone sono presenti " +smartphoneServiceInstance.listAllSmartphone().size() + " elementi." );

//			testAggiornamentoVersioneAppEDataAggiornamento(appServiceInstance);
//			System.out.println("nella tabella App sono presenti " + appServiceInstance.listAllApps().size() + " elementi.");


		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	
	private static void initApp(AppService appServiceInstance) throws Exception {
		if (appServiceInstance.cercaPerNome("Facebook") == null)
			appServiceInstance.inserisciNuovo(new App("Facebook", LocalDate.of(2018, 05, 10), LocalDate.of(2021, 11, 15), 5));
		if (appServiceInstance.cercaPerNome("Just Eat") == null)
			appServiceInstance.inserisciNuovo(new App("Just Eat", LocalDate.of(2016, 07, 01), LocalDate.of(2023, 02, 20), 5));
	}
	
	private static void testInserisciNuovo(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovo inizio.............");

		Smartphone smartphoneInstance = new Smartphone("apple", "iphone 13", 800, 16);
		smartphoneServiceInstance.inserisciNuovo(smartphoneInstance);
		if (smartphoneInstance.getId() == null)
			throw new RuntimeException("testInserisciNuovo fallito ");

		System.out.println(".......testInserisciNuovo fine.............");
	}

	//
	private static void testAggiorna(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println(".......testAggiorna inizio.......");
		List<Smartphone> listaSmartphone = smartphoneServiceInstance.listAllSmartphone();
		if (listaSmartphone.size() < 1)
			throw new RuntimeException("errore: non sono presenti smartphone sul db.");
		Smartphone smartphoneDaAggiornare = listaSmartphone.get(0);
		Integer nuovaVersione = 17;
		smartphoneDaAggiornare.setVersioneOS(nuovaVersione);
		smartphoneServiceInstance.aggiorna(smartphoneDaAggiornare);
		System.out.println(smartphoneDaAggiornare);
		System.out.println(".......testAggiorna fine.......");
	}

	//
	private static void testInserisciNuovaApp(AppService appServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovaApp inizio.............");

		App appInstance = new App("twitter", LocalDate.of(2022, 9, 10), LocalDate.of(2023, 4, 10), 11);
		appServiceInstance.inserisciNuovo(appInstance);
		if (appInstance.getId() == null)
			throw new RuntimeException("testInserisciNuovaApp fallito ");

		System.out.println(".......testInserisciNuovaApp fine.............");
	}

	//
	private static void testInstallaAppSuSmartphone(SmartphoneService smartphoneServiceInstance,
			AppService appServiceInstance) throws Exception {
		System.out.println(".......testInstallaAppSuSmartphone inizio.............");

		App appEsistente = appServiceInstance.cercaPerNome("twitter");
		if (appEsistente == null)
			throw new RuntimeException("testCollegaSportAAtletaEsistente fallito: app inesistente ");

		List<Smartphone> listaSmartphones = smartphoneServiceInstance.listAllSmartphone();
		if (listaSmartphones.size() < 1) {
			throw new RuntimeException("non sono presenti smartphone sul db.");
		}

		// mi creo un utente inserendolo direttamente su db
		Smartphone nuovoSmartphone = new Smartphone("samsung", "s23", 750, 5);
		smartphoneServiceInstance.inserisciNuovo(nuovoSmartphone);
		if (nuovoSmartphone.getId() == null)
			throw new RuntimeException("testInserisciNuovoAtleta fallito: atleta non inserito ");

		smartphoneServiceInstance.aggiungiApp(nuovoSmartphone, appEsistente);
		// per fare il test ricarico interamente l'oggetto e la relazione
		Smartphone smartphoneRicaricato = smartphoneServiceInstance
				.caricaSmartphoneSingoloConApps(nuovoSmartphone.getId());
		if (smartphoneRicaricato.getApps().size() != 1)
			throw new RuntimeException("testInstallaAppSuSmartphone fallito: smartphone non aggiunto ");

		System.out.println(".......testInstallaAppSuSmartphone fine.............");
	}

	//
	private static void testDisinstallaAppDaSmartphone(SmartphoneService smartphoneServiceInstance,
			AppService appServiceInstance) throws Exception {
		System.out.println(".......testDisinstallaAppDaSmartphone inizio.............");

		// carico un ruolo e lo associo ad un nuovo utente
		App appEsistente = appServiceInstance.cercaPerNome("twitter");
		if (appEsistente == null)
			throw new RuntimeException("testDisinstallaAppDaSmartphone fallito: app inesistente ");

		// mi creo un atleta inserendolo direttamente su db
		Smartphone smartphoneNuovo = new Smartphone("Huawei", "h2", 450, 1);
		smartphoneServiceInstance.inserisciNuovo(smartphoneNuovo);
		if (smartphoneNuovo.getId() == null)
			throw new RuntimeException("errore: atleta non inserito ");
		smartphoneServiceInstance.aggiungiApp(smartphoneNuovo, appEsistente);

		// ora ricarico il record e provo a disassociare lo sport
		Smartphone smartphoneRicaricato = smartphoneServiceInstance
				.caricaSmartphoneSingoloConApps(smartphoneNuovo.getId());
		boolean confermoAppPresente = false;
		for (App appItem : smartphoneRicaricato.getApps()) {
			if (appItem.getNome().equals(appEsistente.getNome())) {
				confermoAppPresente = true;
				break;
			}
		}

		if (!confermoAppPresente)
			throw new RuntimeException("testDisinstallaAppDaSmartphone fallito: smartphone e app non associati ");

		// ora provo la rimozione vera e propria ma poi forzo il caricamento per fare un
		// confronto 'pulito'
		smartphoneServiceInstance.rimuoviAppDaSmartphone(smartphoneRicaricato.getId(), appEsistente.getId());
		smartphoneRicaricato = smartphoneServiceInstance.caricaSmartphoneSingoloConApps(smartphoneNuovo.getId());
		if (!smartphoneRicaricato.getApps().isEmpty())
			throw new RuntimeException("testDisinstallaAppDaSmartphone fallito: app ancora associata ");

		System.out.println(".......testDisinstallaAppDaSmartphone fine.............");
	}

	//

	private static void testRimozioneSmartphoneConDueApp(SmartphoneService smartphoneServiceInstance,
			AppService appServiceInstance) throws Exception {
		System.out.println(".....testRimozioneSmartphoneConDueApp inizio........");

		Smartphone smartphoneDaEliminare = new Smartphone("oppo", "op1", 400, 16);
		smartphoneServiceInstance.inserisciNuovo(smartphoneDaEliminare);
		List<App> listaApp = appServiceInstance.listAllApps();
		if (listaApp.size() < 1) {
			throw new RuntimeException(
					"errore: non sono presenti smartphone in elenco.");
		}

		App app1 = listaApp.get(0);
		App app2 = listaApp.get(1);
		smartphoneServiceInstance.aggiungiApp(smartphoneDaEliminare, app1);
		smartphoneServiceInstance.aggiungiApp(smartphoneDaEliminare, app2);

		Smartphone smartphoneRicaricato = smartphoneServiceInstance
				.caricaSmartphoneSingoloConApps(smartphoneDaEliminare.getId());
		if (smartphoneRicaricato.getApps().size() != 2) {
			throw new RuntimeException("errore: non sono state aggiunte le app.");
		}

		smartphoneServiceInstance.rimozioneSmartphoneConDueApp(smartphoneRicaricato.getId());
		Smartphone smartphoneCheSiDeveEliminare = smartphoneServiceInstance
				.caricaSmartphoneSingoloConApps(smartphoneDaEliminare.getId());
		if (smartphoneCheSiDeveEliminare != null) {
			throw new RuntimeException("errore: rimozione non avvenuta.");
		}

		System.out.println(".......testRimozioneSmartphoneConDueApp fine.....");
	}
	
	//
	
	private static void testAggiornaVersioneOS (SmartphoneService smartphoneServiceInstance) throws Exception{
		System.out.println("........testAggiornaVersioneOS inizio.......");
		List<Smartphone> listaSmartphonePresentiInElenco = smartphoneServiceInstance.listAllSmartphone();
		if (listaSmartphonePresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testAggiornamentoVersioneOSSmartphone FALLITO: non sono presenti smartphone in elenco.");
		}

		Smartphone smartphoneDaAggiornare = listaSmartphonePresentiInElenco.get(0);
		System.out.println("Versione prima dell'aggiornamento: " + smartphoneDaAggiornare.getVersioneOS());

		LocalDateTime dateCreateDateTimeIniziale = smartphoneDaAggiornare.getCreateDateTime();
		LocalDateTime dateUpdateDateTimeIniziale = smartphoneDaAggiornare.getUpdateDateTime();

		if (!dateCreateDateTimeIniziale.equals(dateUpdateDateTimeIniziale)) {
			throw new RuntimeException("errore: le date non coincidono");
		}

		smartphoneServiceInstance.aggiornaVersioneOS(smartphoneDaAggiornare.getId());
		if (smartphoneDaAggiornare.getUpdateDateTime().isAfter(dateUpdateDateTimeIniziale))
			throw new RuntimeException("errore: le date di modifica sono disallineate.");
		if (!smartphoneDaAggiornare.getCreateDateTime().equals(dateCreateDateTimeIniziale))
			throw new RuntimeException("errore: le date non coincidono");
		Smartphone smartphoneRicaricato = smartphoneServiceInstance.caricaSingoloElemento(smartphoneDaAggiornare.getId());
		System.out.println("Versione dopo dell'aggiornamento: " + smartphoneRicaricato.getVersioneOS());

		System.out.println(".......testAggiornaVersioneOS fine..........");
	}
	
	//
	private static void testAggiornamentoVersioneAppEDataAggiornamento(AppService appServiceInstance) throws Exception {
		System.out.println(".......testAggiornamentoVersioneAppEDataAggiornamento inizio......");
		List<App> listaAppPresentiInElenco = appServiceInstance.listAllApps();
		if (listaAppPresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"errore: non sono presenti app in elenco.");
		}

		App appDaAggiornare = listaAppPresentiInElenco.get(0);
		System.out.println("Versione prima dell'aggiornamento: " + appDaAggiornare.getVersione());

		LocalDateTime dateCreateDateTimeIniziale = appDaAggiornare.getCreateDateTime();
		LocalDateTime dateUpdateDateTimeIniziale = appDaAggiornare.getUpdateDateTime();

		if (!dateCreateDateTimeIniziale.equals(dateUpdateDateTimeIniziale)) {
			throw new RuntimeException(
					"testAggiornamentoVersioneAppEDataAggiornamento FALLITO: le date non coincidono");
		}

		appServiceInstance.aggiornaVersioneAppEDataDiAggiornamento(appDaAggiornare.getId());
		if (appDaAggiornare.getUpdateDateTime().isAfter(dateUpdateDateTimeIniziale))
			throw new RuntimeException(
					"testAggiornamentoVersioneAppEDataAggiornamento FALLITO: le date di modifica sono disallineate.");
		if (!appDaAggiornare.getCreateDateTime().equals(dateCreateDateTimeIniziale))
			throw new RuntimeException("testInserimentoNuovoSmartphone FALLITO: le date non coincidono");
		App appReloaded = appServiceInstance.caricaSingoloElemento(appDaAggiornare.getId());
		System.out.println("Versione dopo dell'aggiornamento: " + appReloaded.getVersione());

		System.out.println(".......testAggiornamentoVersioneAppEDataAggiornamento fine........");
	}

}
