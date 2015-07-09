package com.trinary.aftv.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trinary.aftv.persistence.dao.ContestEntryDAO;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.aftv.persistence.entity.Vote;

@Transactional
public class ContestEntryServiceImpl implements ContestEntryService {
	@Autowired ContestEntryDAO contestEntryDAO;
	@Autowired ContestService contestService;
	
	@Override
	public ContestEntry create(String uuid, String artist, String title, String description,
			Contest contest) {
		ContestEntry entry = new ContestEntry();
		entry.setUuid(uuid);
		entry.setArtist(artist);
		entry.setTitle(title);
		entry.setDescription(description);
		entry.setContest(contest);
		
		contestEntryDAO.save(entry);
		
		return entry;
	}
	
	@Override
	public void save(ContestEntry entry) {
		contestEntryDAO.save(entry);
	}
	
	@Override
	public void addThumbnail(String uuid, String entryId, byte[] bytes) throws Exception {
		Contest contest = contestService.getByUuid(uuid);
		ContestEntry entry = getByContestAndUuid(contest, entryId);
		entry.setThumbnail(new SerialBlob(bytes));
		save(entry);
	}
	
	@Override
	public List<ContestEntry> getAll() throws Exception {
		return contestEntryDAO.getAll();
	}

	@Override
	public ContestEntry getByUuid(String uuid) throws Exception {
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		criteria.add(Restrictions.eq("uuid", uuid));
		ContestEntry entry = contestEntryDAO.findOne(criteria, null);
		return entry;
	}
	
	@Override
	public ContestEntry getByContestAndUuid(Contest contest, String uuid) throws Exception {
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		criteria.add(Restrictions.eq("contest", contest));
		criteria.add(Restrictions.eq("uuid", uuid));
		ContestEntry entry = contestEntryDAO.findOne(criteria, null);
		return entry;
	}
	
	@Override
	public List<Vote> getVotes(Contest contest, String uuid) {
		try {
			ContestEntry entry = getByContestAndUuid(contest, uuid);
			return new ArrayList<Vote>(entry.getVotes());
		} catch (Exception e) {
			return null;
		}
	}
}