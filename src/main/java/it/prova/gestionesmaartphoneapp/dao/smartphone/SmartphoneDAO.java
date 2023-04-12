package it.prova.gestionesmaartphoneapp.dao.smartphone;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneDAO extends IBaseDAO<Smartphone>{
	public Smartphone findByIdFetchingApps (Long id) throws Exception;
	public void deleteSmartphoneAfterDisinstallTwoApps(Long idSmartphone) throws Exception;
	public void updateVersioneOS(Long idSmartphone) throws Exception;

	

}
