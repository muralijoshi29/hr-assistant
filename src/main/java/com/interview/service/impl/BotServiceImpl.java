package com.interview.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.beans.ChatMessage;
import com.interview.model.Applicant;
import com.interview.repository.ApplicantRepository;
import com.interview.service.BotService;

@Service
public class BotServiceImpl implements BotService {
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Override
	public ChatMessage getBotResponse(ChatMessage message) {
		String userMsg = message.getMsg();
		ChatMessage botMsg = new ChatMessage();
		StringBuilder str = new StringBuilder();
		str.append("BOT: ");
		Optional<Applicant> applicant = applicantRepository.findById(userMsg);
		if (applicant.isPresent()) {
			if(applicant.get().getInterview().getStatus().equals("Pass")) {
				str.append("Congratulations!! ");
			}else {
				str.append("Sorry. ");
			}
			str.append("Your interview status for the position of '");
			str.append(applicant.get().getJob().getName());
			str.append("' applied on '");
			str.append(applicant.get().getApplied_date());
			str.append("' is '");
			str.append(applicant.get().getInterview().getFeedback());
			str.append("'");
		}else {
			str.append("Sorry, we couldn't find your Applicant ID:'");
			str.append(userMsg);
			str.append("' in our system. Kindly retry with the correct Applicant ID.");
		}
		botMsg.setMsg(str.toString());
		return botMsg;
	}
	
	

}
