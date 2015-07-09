package com.trinary.aftv.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trinary.aftv.persistence.dao.VoteDAO;
import com.trinary.aftv.persistence.dao.VoterDAO;
import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.persistence.entity.Voter;

@Transactional
public class VoterServiceImpl implements VoterService {
	@Autowired VoterDAO voterDAO;
	@Autowired VoteDAO voteDAO;
	
	@Override
	public Voter save(Voter voter) {
		voterDAO.save(voter);
		return voter;
	}
	
	@Override
	public Voter get(long id) {
		Voter voter = voterDAO.get(id);
		return voter;
	}
	
	@Override
	public List<Vote> getVotes(long id) {
		Voter voter = voterDAO.get(id);
		return new ArrayList<Vote>(voter.getVotes());
	}
	
	/* (non-Javadoc)
	 * @see com.trinary.aftv.service.VoterService#getVoterByIpAddress(java.lang.String)
	 */
	@Override
	public Voter getVoterByIpAddress(String ipAddress) throws Exception {
		List<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(Restrictions.eq("ipAddress", ipAddress));
		
		Voter voter = voterDAO.findOne(criteria, null);
		
		return voter;
	}
	
	/* (non-Javadoc)
	 * @see com.trinary.aftv.service.VoterService#hasVoterAlreadyVoted(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean hasVoterAlreadyVoted(String entryId, String ipAddress) {
		List<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(Restrictions.eq("entry.id", entryId));
		criteria.add(Restrictions.eq("voter.ipAddress", ipAddress));
		
		Vote vote;
		try {
			vote = voteDAO.findOne(criteria, null);
		} catch (Exception e) {
			return false;
		}
		
		return vote != null;
	}
}