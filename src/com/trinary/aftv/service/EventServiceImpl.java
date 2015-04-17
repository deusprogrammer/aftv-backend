package com.trinary.aftv.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.trinary.aftv.persistence.entity.ContestEntry;

@Transactional
public class EventServiceImpl implements EventService {
	@Autowired protected SimpMessagingTemplate template;
	
	@Override
	public void broadcast(ContestEntry entry) {
		template.convertAndSend(entry);
	}

}
