package com.epam.jmp.jpa.model;

import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

public class Unit extends AbstractEntity {

	@OneToMany(mappedBy = "department")
	@OrderBy("name ASC")
	private List<Employee> employees;
	
	private String unitName;
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
