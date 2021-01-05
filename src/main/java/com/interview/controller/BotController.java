package com.interview.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.interview.beans.ChatMessage;
import com.interview.service.BotService;

@Controller
public class BotController {
	

	@Autowired
	private BotService botService;
	
	private Gson gson = new Gson();
	
	@MessageMapping("/message")
	@SendToUser("/queue/reply")
	public ChatMessage processMessageFromClient(@Payload ChatMessage message, Principal principal) throws Exception {
		//String msg = gson.fromJson(message, Map.class).get("msg").toString();
		//Candidate can = candidateRepository.findByName(candidateName);
		//System.out.println("candidate found"+can.getLocation());
		return botService.getBotResponse(message);
	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}