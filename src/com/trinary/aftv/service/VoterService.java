package com.trinary.aftv.service;

import java.util.List;

import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.persistence.entity.Voter;

public interface VoterService {
	Voter getVoterByIpAddress(String ipAddress)
			throws Exception;
	Boolean hasVoterAlreadyVoted(String entryId,
			String ipAddress);
	Voter save(Voter voter);
	Voter get(long id);
	List<Vote> getVotes(long id);

}