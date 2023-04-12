package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;

public interface AppService {
	public List<App> listAllApps() throws Exception;

	public App caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(App appInstance) throws Exception;

	public void inserisciNuovo(App appInstance) throws Exception;

	public void rimuovi(Long idApp) throws Exception;
	
	public App cercaPerNome (String nome) throws Exception;
	public void aggiornaVersioneAppEDataDiAggiornamento(Long idApp) throws Exception;


	
	// per injection
		public void setAppDAO(AppDAO appDAO);

}
