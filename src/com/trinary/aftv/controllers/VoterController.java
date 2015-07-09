package com.trinary.aftv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trinary.aftv.hateoas.VoteResource;
import com.trinary.aftv.hateoas.VoteResourceAssembler;
import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.service.VoterService;

@Controller
@RequestMapping("/voter")
public class VoterController {
	@Autowired VoterService service;
	@Autowired VoteResourceAssembler voteAssembler;
	
	@RequestMapping("/{id}/votes")
	@ResponseBody
	public List<VoteResource> getVotes(@PathVariable("id") long id) {
		List<Vote> votes = service.getVotes(id);
		return voteAssembler.toResources(votes);
	}
}