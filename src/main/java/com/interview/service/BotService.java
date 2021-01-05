package com.interview.service;

import com.interview.beans.ChatMessage;

public interface BotService {

	ChatMessage getBotResponse(ChatMessage message);

}
