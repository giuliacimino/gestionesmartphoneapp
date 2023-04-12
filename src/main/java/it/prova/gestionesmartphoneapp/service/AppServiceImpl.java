package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;

public class AppServiceImpl implements AppService{
	
	private AppDAO appDAO;
	
	
	@Override
	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
		
	}

	@Override
	public List<App> listAllApps() throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					appDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return appDAO.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
	}

	@Override
	public App caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			appDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return appDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(App appInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(App appInstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					appDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					appDAO.insert(appInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
		
	}

	@Override
	public void rimuovi(Long idApp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public App cercaPerNome(String nome) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			appDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return appDAO.findByNome(nome);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiornaVersioneAppEDataDiAggiornamento(Long idApp) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();

			// injection
			appDAO.setEntityManager(entityManager);

			appDAO.updateVersioneEDataAggiornamento(idApp);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}


	
	

}
