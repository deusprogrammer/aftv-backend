package com.trinary.aftv.service;

import java.util.List;

import com.trinary.aftv.commons.EventDTO;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;

public interface ContestService {
	Contest save(Contest contest);
	List<Contest> getAll();
	List<Contest> getAll(boolean active) throws Exception;
	Contest create(String uuid, String title, String description, List<ContestEntry> entries);
	Contest getByUuid(String uuid) throws Exception;
	List<ContestEntry> getEntries(String uuid) throws Exception;
	ContestEntry getNowPlaying(String uuid) throws Exception;
	void publishEvent(String uuid, EventDTO event) throws Exception;
	
	Contest createTest();
	
}
