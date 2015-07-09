package com.trinary.aftv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trinary.aftv.persistence.dao.VoteDAO;
import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.persistence.entity.Voter;

@Transactional
public class VoteServiceImpl implements VoteService {
	@Autowired VoteDAO voteDAO;
	
	/* (non-Javadoc)
	 * @see com.trinary.aftv.service.VoteService#save(com.trinary.aftv.persistence.entity.Vote)
	 */
	@Override
	public Vote save(Vote vote) {
		voteDAO.save(vote);
		return vote;
	}
	
	@Override
	public Vote get(long id) {
		return voteDAO.get(id);
	}
	
	@Override
	public ContestEntry getEntry(long id) {
		Vote vote = voteDAO.get(id);
		ContestEntry entry = vote.getEntry();
		return entry;
	}
	
	@Override
	public Voter getVoter(long id) {
		Vote vote = voteDAO.get(id);
		Voter voter = vote.getVoter();
		return voter;
	}
}