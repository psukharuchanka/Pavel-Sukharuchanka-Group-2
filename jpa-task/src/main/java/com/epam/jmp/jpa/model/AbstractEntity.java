package com.epam.jmp.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {
	
	@Id
	private long id = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AbstractEntity(long id) {
		super();
		this.id = id;
	}

	public AbstractEntity() {
		super();
	}

}
