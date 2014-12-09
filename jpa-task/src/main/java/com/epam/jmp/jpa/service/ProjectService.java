package com.epam.jmp.jpa.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.jmp.jpa.model.AbstractEntity;
import com.epam.jmp.jpa.model.Employee;
import com.epam.jmp.jpa.model.Project;
import com.epam.jmp.jpa.model.Unit;

public class ProjectService extends AbstractEntityService {
	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectService");

	public static AbstractEntity find(int id) {
		return em.find(Project.class, id);
	}
	
	public static void addEmployee(int projectId, int employeeId){
		Employee employee = (Employee) EmployeeService.find(employeeId);
		Unit unit = (Unit) UnitService.find(projectId);
		unit.getEmployees().add(employee);
		UnitService.create(unit);
	}

}
