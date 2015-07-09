package com.trinary.aftv.service;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.aftv.persistence.entity.Vote;

public interface ContestEntryService {
	ContestEntry create(String uuid, String artist, String title, String description, Contest contest);
	ContestEntry getByUuid(String uuid) throws Exception;
	ContestEntry getByContestAndUuid(Contest contest, String uuid)
			throws Exception;
	List<ContestEntry> getAll() throws Exception;
	void save(ContestEntry entry);
	List<Vote> getVotes(Contest contest, String uuid);
	void addThumbnail(String uuid, String entryId, byte[] bytes) throws SerialException, SQLException, Exception;
}
