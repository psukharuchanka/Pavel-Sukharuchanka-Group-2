package com.epam.jmp.jpa.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.jmp.jpa.model.AbstractEntity;
import com.epam.jmp.jpa.model.Employee;
import com.epam.jmp.jpa.model.Unit;

public class UnitService extends AbstractEntityService {
	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitService");

	public static AbstractEntity find(int id) {
		return em.find(Unit.class, id);
	}
	
	public static void addEmployee(int unitId, int employeeId){
		Employee employee = (Employee) EmployeeService.find(employeeId);
		Unit unit = (Unit) UnitService.find(unitId);
		unit.getEmployees().add(employee);
		UnitService.create(unit);
	}

}
