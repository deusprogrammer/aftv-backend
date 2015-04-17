package com.trinary.aftv.service;

import java.util.List;

import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;

public interface ContestEntryService {
	public ContestEntry create(String uuid, String title, String description, Contest contest);
	public ContestEntry getByUuid(String uuid) throws Exception;
	public ContestEntry getByContestAndUuid(Contest contest, String uuid)
			throws Exception;
	public List<ContestEntry> getAll() throws Exception;
}
