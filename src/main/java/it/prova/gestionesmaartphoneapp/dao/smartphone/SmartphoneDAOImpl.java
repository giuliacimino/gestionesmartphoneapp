package it.prova.gestionesmaartphoneapp.dao.smartphone;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneDAOImpl implements SmartphoneDAO{
	
	private EntityManager entityManager;

	@Override
	public List<Smartphone> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Smartphone get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Smartphone o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Smartphone o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Smartphone o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

}
