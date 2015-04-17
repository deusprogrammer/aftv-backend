package com.trinary.aftv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trinary.aftv.Event;
import com.trinary.aftv.EventResponse;
import com.trinary.aftv.EventResponse.EventStatus;
import com.trinary.aftv.hateoas.ContestConfigResource;
import com.trinary.aftv.hateoas.ContestEntryResource;
import com.trinary.aftv.hateoas.ContestEntryResourceAssembler;
import com.trinary.aftv.hateoas.ContestResource;
import com.trinary.aftv.hateoas.ContestResourceAssembler;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.service.ContestService;

@Controller
@RequestMapping(value="/contest")
public class ContestController {
	@Autowired ContestService contestService;
	@Autowired ContestResourceAssembler contestAssembler;
	@Autowired ContestEntryResourceAssembler entryAssembler;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<ContestResource> getAll() {
		return contestAssembler.toResources(contestService.getAll());
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public ContestResource create(@RequestBody Contest contest) {
		Contest existingContest = null;
		try {
			existingContest = contestService.getByUuid(contest.getUuid());
		} catch (Exception e) {
			return contestAssembler.toResource(contestService.save(contest));
		}
		
		return contestAssembler.toResource(existingContest);
	}
	
	@RequestMapping(value="/{uuid}", method=RequestMethod.GET)
	@ResponseBody
	public ContestResource get(@PathVariable("uuid") String uuid) {
		try {
			return contestAssembler.toResource(contestService.getByUuid(uuid));
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/entries", method=RequestMethod.GET)
	@ResponseBody
	public List<ContestEntryResource> getEntries(@PathVariable("uuid") String uuid) {
		try {
			return entryAssembler.toResources(contestService.getEntries(uuid));
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/nowPlaying", method=RequestMethod.GET)
	@ResponseBody
	public ContestEntryResource getNowPlaying(@PathVariable("uuid") String uuid) {
		try {
			return entryAssembler.toResource(contestService.getNowPlaying(uuid));
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/config", method=RequestMethod.GET)
	@ResponseBody
	public ContestConfigResource getConfig(@PathVariable("uuid") String uuid) {
		return null;
	}
	
	@RequestMapping(value="/{uuid}/publish", method=RequestMethod.POST)
	@ResponseBody
	public EventResponse publishEvent(@PathVariable("uuid") String uuid, @RequestBody Event event) {
		EventResponse response = new EventResponse();
		try {
			contestService.publishEvent(uuid, event);
		} catch (Exception e) {
			response.setMessage("Event failed to publish because of: " + e.getMessage());
			response.setStatus(EventStatus.FAILURE);
			return response;
		}
		response.setMessage("Event published successfully!");
		response.setStatus(EventStatus.SUCCESS);
		return response;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	@ResponseBody
	public ContestResource test() {
		return contestAssembler.toResource(contestService.createTest());
	}
}
