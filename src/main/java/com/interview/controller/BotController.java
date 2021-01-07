package com.interview.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.interview.beans.ChatMessage;
import com.interview.service.impl.MessageProcessor;

@Controller
public class BotController {
	
	@Autowired
	private MessageProcessor processor;
	
	
	@MessageMapping("/message")
	@SendToUser("/queue/reply")
	public ChatMessage processMessageFromClient(@Payload ChatMessage message, MessageHeaders messageHeaders, Principal principal) throws Exception {
		return processor.process(message);
	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}