package com.demo.job;

import java.util.List;

import com.demo.Company.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long id;
	public String title;
	public String desc;
	public String minSalary;
	public String maxSalary;
	public String location;
	
	
	public Job() {
		super();
	}

	public Job(Long id, String title, String desc, String minSalary, String maxSalary, String location) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.location = location;
	}

	@ManyToOne
	private Company companies;

	public Company getCompanies() {
		return companies;
	}

	public void setCompanies(Company companies) {
		this.companies = companies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public String getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", desc=" + desc + ", minSalary=" + minSalary + ", maxSalary="
				+ maxSalary + ", location=" + location + "]";
	}
	
	
}
