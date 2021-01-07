package com.interview.beans;

public class ChatMessage {

	  private String msg;
	  
	  private ApplicantVO applicantVo;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ApplicantVO getApplicant() {
		return applicantVo;
	}

	public void setApplicant(ApplicantVO applicant) {
		this.applicantVo = applicant;
	}
	  
}