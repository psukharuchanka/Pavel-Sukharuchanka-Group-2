package com.epam.jmp.jpa.service;

import com.epam.jmp.jpa.model.AbstractEntity;

public interface IEntityService {

	public void create(AbstractEntity entity);
	
	public AbstractEntity find(int id);
	
	public void delete(int id);
	
	public void update(int id, AbstractEntity entity);
}
