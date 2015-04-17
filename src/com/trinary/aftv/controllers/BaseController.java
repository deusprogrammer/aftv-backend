package com.trinary.aftv.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class BaseController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public ResourceSupport get() {
		ResourceSupport resource = new ResourceSupport();
		resource.add(linkTo(methodOn(BaseController.class).get()).withSelfRel());
		resource.add(linkTo(methodOn(ContestController.class).getAll()).withRel("contests"));
		resource.add(linkTo(methodOn(ContestEntryController.class).getAll()).withRel("entries"));
		
		return resource;
	}
}
