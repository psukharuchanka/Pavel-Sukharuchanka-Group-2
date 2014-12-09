/**
 * 
 */
package com.epam.jmp.jpa.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.jmp.jpa.model.AbstractEntity;
import com.epam.jmp.jpa.model.Employee;

public class EmployeeService extends AbstractEntityService {
	
	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");

	public static AbstractEntity find(int id) {
		return em.find(Employee.class, id);
	}

}
