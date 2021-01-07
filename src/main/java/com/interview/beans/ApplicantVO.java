package com.interview.beans;

public class ApplicantVO {
	private String id;
	private String name;
	private String age;
	private String applied_date;
	private String interview_date;
	private String status;
	private String job_name;
	
	
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInterview_date() {
		return interview_date;
	}
	public void setInterview_date(String interview_date) {
		this.interview_date = interview_date;
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
	public String getApplied_date() {
		return applied_date;
	}
	public void setApplied_date(String applied_date) {
		this.applied_date = applied_date;
	}
	
}
