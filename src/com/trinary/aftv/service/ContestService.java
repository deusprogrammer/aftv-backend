package com.trinary.aftv.service;

import java.util.List;

import com.trinary.aftv.Event;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;

public interface ContestService {
	public Contest save(Contest contest);
	public List<Contest> getAll();
	public Contest create(String uuid, String title, String description, List<ContestEntry> entries);
	public Contest getByUuid(String uuid) throws Exception;
	public List<ContestEntry> getEntries(String uuid) throws Exception;
	public ContestEntry getNowPlaying(String uuid) throws Exception;
	public void publishEvent(String uuid, Event event) throws Exception;
	
	public Contest createTest();
}
