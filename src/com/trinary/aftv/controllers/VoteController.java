package com.trinary.aftv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trinary.aftv.hateoas.ContestEntryResource;
import com.trinary.aftv.hateoas.ContestEntryResourceAssembler;
import com.trinary.aftv.hateoas.VoteResource;
import com.trinary.aftv.hateoas.VoteResourceAssembler;
import com.trinary.aftv.hateoas.VoterResource;
import com.trinary.aftv.hateoas.VoterResourceAssembler;
import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.persistence.entity.Voter;
import com.trinary.aftv.service.VoteService;

@Controller
@RequestMapping("/vote")
public class VoteController {
	@Autowired VoteService service;
	@Autowired ContestEntryResourceAssembler entryAssembler;
	@Autowired VoterResourceAssembler voterAssembler;
	@Autowired VoteResourceAssembler voteAssembler;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public VoteResource get(@PathVariable("id") long id) {
		Vote vote = service.get(id);
		return voteAssembler.toResource(vote);
	}
	
	@RequestMapping("/{id}/entry")
	@ResponseBody
	public ContestEntryResource getEntry(@PathVariable("id") long id) {
		ContestEntry entry = service.getEntry(id);
		return entryAssembler.toResource(entry);
	}
	
	@RequestMapping("/{id}/owner")
	@ResponseBody
	public VoterResource getVoter(@PathVariable("id") long id) {
		Voter voter = service.getVoter(id);
		return voterAssembler.toResource(voter);
	}
}