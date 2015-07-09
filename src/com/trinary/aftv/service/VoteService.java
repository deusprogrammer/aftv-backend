package com.trinary.aftv.service;

import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.persistence.entity.Voter;

public interface VoteService {
	Vote save(Vote vote);
	ContestEntry getEntry(long id);
	Voter getVoter(long id);
	Vote get(long id);
}