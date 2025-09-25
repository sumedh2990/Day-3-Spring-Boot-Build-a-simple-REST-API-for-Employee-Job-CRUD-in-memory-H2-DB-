package com.demo.Company;

import java.util.List;

import com.demo.job.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String desc;
	
	public Company() {
		super();
	}
	
	public Company(Long id, String name, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
	}
	
	@OneToMany(mappedBy = "companies")
	@JsonIgnore
	private List<Job> jobs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", desc=" + desc + ", jobs=" + jobs + "]";
	}


	
	
	
}
