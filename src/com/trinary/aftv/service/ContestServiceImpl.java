package com.trinary.aftv.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.trinary.aftv.Event;
import com.trinary.aftv.EventData;
import com.trinary.aftv.hateoas.ContestEntryResourceAssembler;
import com.trinary.aftv.hateoas.ContestResourceAssembler;
import com.trinary.aftv.persistence.dao.ContestDAO;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;

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
		
		List<ContestEntry> entries = new ArrayList<ContestEntry>();
		entries.add(contestEntryService.create("1", "Video 1", "It's a video", contest));
		entries.add(contestEntryService.create("2", "Video 2", "It's a video", contest));
		entries.add(contestEntryService.create("3", "Video 3", "It's a video", contest));
		entries.add(contestEntryService.create("4", "Video 4", "It's a video", contest));
		contest.setEntries(entries);
		
		contestDAO.save(contest);
		
		return contest;
	}
	
	@Override
	public List<Contest> getAll() {
		return contestDAO.getAll();
	}

	@Override
	public Contest create(String uuid, String title, String description, List<ContestEntry> entries) {
		Contest contest = new Contest();
		contest.setUuid(uuid);
		contest.setTitle(title);
		contest.setDescription(description);
		contest.setEntries(entries);
		
		
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
	public void publishEvent(String uuid, Event event) throws Exception {
		Contest contest = this.getByUuid(uuid);
		ContestEntry entry = contestEntryService.getByContestAndUuid(contest, event.getEntryId());
		contest.setNowPlaying(entry);
		contestDAO.save(contest);
		
		EventData ed = new EventData();
		ed.setContest(assembler.toResource(contest));
		ed.setContestEntry(entryAssembler.toResource(entry));
		ed.setTrigger(event.getEventType());
		
		// Publish message to websocket.
		template.convertAndSend("/topic/" + uuid, ed);
	}
}