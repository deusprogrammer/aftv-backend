package com.trinary.aftv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trinary.aftv.hateoas.ContestEntryResource;
import com.trinary.aftv.hateoas.ContestEntryResourceAssembler;
import com.trinary.aftv.service.ContestEntryService;

@Controller
@RequestMapping(value="/entry")
public class ContestEntryController {
	@Autowired ContestEntryService contestEntryService;
	@Autowired ContestEntryResourceAssembler assembler;
	
	@RequestMapping(value="/")
	@ResponseBody
	public List<ContestEntryResource> getAll() {
		try {
			return assembler.toResources(contestEntryService.getAll());
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/{uuid}")
	@ResponseBody
	public ContestEntryResource get(@PathVariable("uuid") String uuid) {
		try {
			return assembler.toResource(contestEntryService.getByUuid(uuid));
		} catch (Exception e) {
			return null;
		}
	}
}