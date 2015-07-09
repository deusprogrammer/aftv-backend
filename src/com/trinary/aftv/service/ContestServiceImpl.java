package com.trinary.aftv.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.trinary.aftv.EventDataDTO;
import com.trinary.aftv.commons.EventDTO;
import com.trinary.aftv.hateoas.ContestEntryResourceAssembler;
import com.trinary.aftv.hateoas.ContestResourceAssembler;
import com.trinary.aftv.persistence.dao.ContestDAO;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.persistence.OrderDirection;
import com.trinary.persistence.OrderPair;

@Transactional
public class ContestServiceImpl implements ContestService {
	@Autowired ContestDAO contestDAO;
	@Autowired ContestEntryService contestEntryService;
	@Autowired ContestResourceAssembler assembler;
	@Autowired ContestEntryResourceAssembler entryAssembler;
	@Autowired SimpMessagingTemplate template;

	@Override
	public Contest createTest() {
		Contest contest = create("IKKICON_2015", "Ikkicon 2015 AMV Contest", "It's a contest", Collections.<ContestEntry>emptyList());
		contestDAO.save(contest);
		
		List<ContestEntry> entries = new ArrayList<ContestEntry>();
		entries.add(contestEntryService.create("1", null, "Video 1", "It's a video", contest));
		entries.add(contestEntryService.create("2", "ELO", null, "It's a video", contest));
		entries.add(contestEntryService.create("3", "Some Guy", "Video 3", "It's a video", contest));
		entries.add(contestEntryService.create("4", "Meatloaf", "Video 4", "It's a video", contest));
		contest.setEntries(entries);
		
		contestDAO.save(contest);
		
		return contest;
	}
	
	@Override
	public List<Contest> getAll() {
		return contestDAO.getAll();
	}
	
	@Override
	public List<Contest> getAll(boolean active) throws Exception {
		if (!active) {
			return getAll();
		}
		
		Date secondsAgo = new Date(new Date().getTime() - TimeUnit.SECONDS.toMillis(30));
		
		List<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(Restrictions.ge("lastEvent", secondsAgo));
		List<OrderPair> sorting = new ArrayList<OrderPair>();
		sorting.add(new OrderPair("title", OrderDirection.ASCENDING));
		return contestDAO.findAll(criteria, sorting, null, null);
	}

	@Override
	public Contest create(String uuid, String title, String description, List<ContestEntry> entries) {
		Contest contest = new Contest();
		contest.setUuid(uuid);
		contest.setTitle(title);
		contest.setDescription(description);
		contest.setEntries(entries);
		contest.setLastEvent(null);
		
		return contest;
	}

	@Override
	public Contest getByUuid(String uuid) throws Exception {
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		criteria.add(Restrictions.eq("uuid", uuid));
		Contest contest = contestDAO.findOne(criteria, null);
		
		return contest;
	}

	@Override
	public Contest save(Contest contest) {
		// Link all entries to contests
		for (ContestEntry entry : contest.getEntries()) {
			entry.setContest(contest);
		}
		
		contestDAO.save(contest);
		return contest;
	}

	@Override
	public List<ContestEntry> getEntries(String uuid) throws Exception {
		Contest contest = this.getByUuid(uuid);
		List<ContestEntry> entries = new ArrayList<ContestEntry>(contest.getEntries());
		
		return entries;
	}

	@Override
	public ContestEntry getNowPlaying(String uuid) throws Exception {
		Contest contest = this.getByUuid(uuid);
		return contest.getNowPlaying();
	}

	@Override
	public void publishEvent(String uuid, EventDTO event) throws Exception {
		Contest contest = this.getByUuid(uuid);
		ContestEntry entry = contestEntryService.getByContestAndUuid(contest, event.getEntryId());
		contest.setNowPlaying(entry);
		contest.setLastEvent(new Date());
		contestDAO.save(contest);
		
		EventDataDTO ed = new EventDataDTO();
		ed.setContest(assembler.toResource(contest));
		ed.setContestEntry(entryAssembler.toResource(entry));
		ed.setTrigger(event.getEventType());
		
		// Publish message to websocket.
		template.convertAndSend("/topic/" + uuid, ed);
	}
}