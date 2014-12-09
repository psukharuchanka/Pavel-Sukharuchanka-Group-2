package com.epam.jmp.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.jmp.jpa.model.AbstractEntity;

public abstract class AbstractEntityService {
	
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("AbstractEntityService");
	protected static EntityManager em = emf.createEntityManager();
	  
	public static void create(AbstractEntity entity) {
		em.persist(entity);
		
	}

	public static AbstractEntity find(int id) 
	{
		throw new UnsupportedOperationException("Method should be realized in child classes");
	}

	public static void delete(int id) {
		AbstractEntity entity = find(id);
	    if (entity != null) {
	      em.remove(entity);
	    }
		
	}

	public static void update(int id, AbstractEntity entity) {
		AbstractEntity found = find(id);
		entity.setId(found.getId());
		em.persist(entity);
	}

}
