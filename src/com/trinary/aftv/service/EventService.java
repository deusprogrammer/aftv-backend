package com.trinary.aftv.service;

import com.trinary.aftv.persistence.entity.ContestEntry;

public interface EventService {
	public void broadcast(ContestEntry entry);
}