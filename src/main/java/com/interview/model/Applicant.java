package com.interview.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Applicant {
	@Id
	private String id;
	private String name;
	private String age;
	private String applied_date;
	private Job job;
	private Interview interview;
	
	public Applicant(String id, String name, String age, String applied_date, Job job, Interview interview) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.applied_date = applied_date;
		this.job = job;
		this.interview = interview;
	}
	public String getApplied_date() {
		return applied_date;
	}

	public void setApplied_date(String applied_date) {
		this.applied_date = applied_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
	
}
