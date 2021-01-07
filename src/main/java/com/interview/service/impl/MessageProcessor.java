package com.interview.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.LANGUAGE;
import com.interview.beans.ApplicantVO;
import com.interview.beans.ChatMessage;
import com.interview.model.Applicant;
import com.interview.repository.ApplicantRepository;
import com.interview.service.Processor;

@Service
public class MessageProcessor implements Processor{

	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Override
	public ChatMessage process(ChatMessage botResponse) {
		String text = botResponse.getMsg().toLowerCase();
		for(String greet: LANGUAGE.GREETINGS) {
			if (text.contains(greet)) {
				botResponse.setMsg(LANGUAGE.BOT_GREETINGS);
				return botResponse;
			}
		}
		for(String bye: LANGUAGE.ENDCHAT) {
			if (text.contains(bye)) {
				botResponse.setMsg(LANGUAGE.BOT_ENDCHAT);
				return botResponse;
			}
		}
		if ((text.contains(LANGUAGE.STATUS) || text.contains(LANGUAGE.FEEDBACK)) && text.contains(LANGUAGE.INTERVIEW)) {
			botResponse.setMsg(LANGUAGE.BOT_APPLICANT_ID);
			return botResponse;
		}
		if (text.contains(LANGUAGE.APPLICANTID_PREFIX)) {
			int start = text.indexOf(LANGUAGE.APPLICANTID_PREFIX);
			String applicantId = text.substring(start);
			Optional<Applicant> applicant = applicantRepository.findById(applicantId.toUpperCase());
			if (applicant.isPresent()) {
				ApplicantVO appVo = new ApplicantVO();
				appVo.setId(applicant.get().getId());
				appVo.setName(applicant.get().getName());
				appVo.setApplied_date(applicant.get().getApplied_date());
				appVo.setInterview_date(applicant.get().getInterview().getDate());
				appVo.setStatus(applicant.get().getInterview().getStatus());
				appVo.setJob_name(applicant.get().getJob().getName());
				botResponse.setMsg(LANGUAGE.ENTER_DATE);
				botResponse.setApplicant(appVo);
			}else {
				botResponse.setMsg(LANGUAGE.NOT_FOUND);
				botResponse.setApplicant(null);
			}
		}
		else if (botResponse.getApplicant() != null) {
			if (text.contains(botResponse.getApplicant().getInterview_date().toLowerCase())) {
				StringBuilder sb = new StringBuilder();
				if(botResponse.getApplicant().getStatus().equals(LANGUAGE.SHORTLISTED)) {
					sb.append(LANGUAGE.CONGRATS);
				}else {
					sb.append(LANGUAGE.NOLUCK);
				}
				sb.append(" Job Title = ");
				sb.append(botResponse.getApplicant().getJob_name());
				sb.append(", Applied On = ");
				sb.append(botResponse.getApplicant().getApplied_date());
				botResponse.setMsg(sb.toString());
			}else {
				botResponse.setMsg(LANGUAGE.VALIDATION_FAIL);
			}
		}else {
			botResponse.setMsg(LANGUAGE.SORRY);
		}
		return botResponse;
	}


}
