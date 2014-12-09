package com.epam.jmp.jpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee extends AbstractEntity {
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus m_status;
	
	@Embedded 
	private Address m_address;
	
	@OneToOne(cascade=CascadeType.ALL)
	private PersonalInfo m_personalInfo;
	
	@ManyToMany
	private List<Project> m_projects;

	public EmployeeStatus getM_status() {
		return m_status;
	}
	public void setM_status(EmployeeStatus m_status) {
		this.m_status = m_status;
	}

	public Address getM_address() {
		return m_address;
	}

	public void setM_address(Address m_address) {
		this.m_address = m_address;
	}

	public PersonalInfo getM_personalInfo() {
		return m_personalInfo;
	}

	public void setM_personalInfo(PersonalInfo m_personalInfo) {
		this.m_personalInfo = m_personalInfo;
	}

	public List<Project> getM_projects() {
		return m_projects;
	}

	public void setM_projects(List<Project> m_projects) {
		this.m_projects = m_projects;
	}

}
