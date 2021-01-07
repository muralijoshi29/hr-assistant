package com.interview.service;

import com.interview.beans.ChatMessage;

public interface Processor {

	ChatMessage process(ChatMessage msg);
}
