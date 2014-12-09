package com.epam.jmp.jpa.model;

import java.util.List;

import javax.persistence.ManyToMany;

public class Project extends AbstractEntity{
	
	@ManyToMany(mappedBy="m_projects")
	private List<Employee> m_employees;
	
	private String projectCode;
	
	public List<Employee> getM_employees() {
		return m_employees;
	}

	public void setM_employees(List<Employee> m_employees) {
		this.m_employees = m_employees;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
}
