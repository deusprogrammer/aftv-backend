package com.trinary.aftv.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.trinary.aftv.commons.EventDTO;
import com.trinary.aftv.commons.EventResponseDTO;
import com.trinary.aftv.commons.EventResponseDTO.EventStatus;
import com.trinary.aftv.hateoas.ContestConfigResource;
import com.trinary.aftv.hateoas.ContestEntryResource;
import com.trinary.aftv.hateoas.ContestEntryResourceAssembler;
import com.trinary.aftv.hateoas.ContestResource;
import com.trinary.aftv.hateoas.ContestResourceAssembler;
import com.trinary.aftv.hateoas.VoteResource;
import com.trinary.aftv.hateoas.VoteResourceAssembler;
import com.trinary.aftv.hateoas.VoterResourceAssembler;
import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.aftv.persistence.entity.Voter;
import com.trinary.aftv.service.ContestEntryService;
import com.trinary.aftv.service.ContestService;
import com.trinary.aftv.service.VoteService;
import com.trinary.aftv.service.VoterService;

@Controller
@RequestMapping(value="/contest")
public class ContestController {
	@Autowired ContestService contestService;
	@Autowired ContestResourceAssembler contestAssembler;
	@Autowired ContestEntryResourceAssembler entryAssembler;
	@Autowired ContestEntryService contestEntryService;
	@Autowired VoterService voterService;
	@Autowired VoterResourceAssembler voterAssembler;
	@Autowired VoteService voteService;
	@Autowired VoteResourceAssembler voteAssembler;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<ContestResource> getAll(@RequestParam(value="active", required=false) String active) {
		if (active != null && active.equals("true")) {
			try {
				return contestAssembler.toResources(contestService.getAll(true));
			} catch (Exception e) {
				e.printStackTrace();
				return Collections.emptyList();
			}
		}
		
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
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/entry", method=RequestMethod.GET)
	@ResponseBody
	public List<ContestEntryResource> getEntries(@PathVariable("uuid") String uuid) {
		try {
			return entryAssembler.toResources(contestService.getEntries(uuid));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/nowPlaying", method=RequestMethod.GET)
	@ResponseBody
	public ContestEntryResource getNowPlaying(@PathVariable("uuid") String uuid) {
		try {
			return entryAssembler.toResource(contestService.getNowPlaying(uuid));
		} catch (Exception e) {
			e.printStackTrace();
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
	public EventResponseDTO publishEvent(@PathVariable("uuid") String uuid, @RequestBody EventDTO event) {
		EventResponseDTO response = new EventResponseDTO();
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
	
	@RequestMapping(value="/{uuid}/entry/{entryId}", method=RequestMethod.GET)
	@ResponseBody
	public ContestEntryResource getEntry(
			@PathVariable("uuid") String uuid, 
			@PathVariable("entryId") String entryId) {
		try {
			Contest contest = contestService.getByUuid(uuid);
			return entryAssembler.toResource(contestEntryService.getByContestAndUuid(contest, entryId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/entry/{entryId}/thumbnail", method=RequestMethod.POST)
	@ResponseBody
	public void setThumbnail(
            @RequestParam("file") MultipartFile file,
			@PathVariable("uuid") String uuid, 
			@PathVariable("entryId") String entryId) {
		try {
			contestEntryService.addThumbnail(uuid, entryId, file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{uuid}/entry/{entryId}/thumbnail", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getThumbnail(HttpServletResponse response, @PathVariable("uuid") String uuid, @PathVariable("entryId") String entryId) {
		try {
			Contest contest = contestService.getByUuid(uuid);
			ContestEntry entry = contestEntryService.getByContestAndUuid(contest, entryId);
			
			final HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(entry.getThumbnail().getBytes(1, (int)entry.getThumbnail().length()), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}/entry/{entryId}/vote", method=RequestMethod.POST)
	@ResponseBody
	public VoteResource vote(HttpServletRequest request, @PathVariable("uuid") String uuid, @PathVariable("entryId") String entryId, @RequestBody Vote vote) {
		String ipAddress = request.getRemoteAddr();
		
		// Get entry
		ContestEntry entry = null;
		try {
			Contest contest = contestService.getByUuid(uuid);
			entry = contestEntryService.getByContestAndUuid(contest, entryId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		// Get voter
		Voter voter = null;
		try {
			voter = voterService.getVoterByIpAddress(ipAddress);
		} catch (Exception e) {
			voter = new Voter();
			voter.setIpAddress(ipAddress);
			
			// Save user
			voterService.save(voter);
		}
		
		// See if this voter already voted on this entry
		if (!voterService.hasVoterAlreadyVoted(uuid, ipAddress)) {
			vote.setEntry(entry);
			vote.setVoter(voter);
			voteService.save(vote);
		}
			
		return voteAssembler.toResource(vote);
	}
	
	@RequestMapping(value="/{uuid}/entry/{entryId}/votes")
	@ResponseBody
	public List<VoteResource> getVotes(@PathVariable("uuid") String uuid, @PathVariable("entryId") String entryId) {
		try {
			Contest contest = contestService.getByUuid(uuid);
			List<Vote> votes = contestEntryService.getVotes(contest, entryId);
			return voteAssembler.toResources(votes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	@ResponseBody
	public ContestResource test() {
		return contestAssembler.toResource(contestService.createTest());
	}
	
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) throws Exception {
        System.out.println("ERROR: " + e.getMessage());
        throw e;
    }
}
