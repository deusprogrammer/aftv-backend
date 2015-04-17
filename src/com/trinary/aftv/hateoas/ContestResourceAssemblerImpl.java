package com.trinary.aftv.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.trinary.aftv.controllers.ContestController;
import com.trinary.aftv.persistence.entity.Contest;

public class ContestResourceAssemblerImpl extends ResourceAssemblerSupport<Contest, ContestResource> implements ContestResourceAssembler {
	public ContestResourceAssemblerImpl() {
		super(ContestController.class, ContestResource.class);
	}

	@Override
	public ContestResource toResource(Contest contest) {
		ContestResource resource = this.createResourceWithId(contest.getUuid(), contest);
		resource.add(linkTo(methodOn(ContestController.class).getNowPlaying(contest.getUuid())).withRel("nowPlaying"));
		resource.add(linkTo(methodOn(ContestController.class).getConfig(contest.getUuid())).withRel("config"));
		resource.add(linkTo(methodOn(ContestController.class).getEntries(contest.getUuid())).withRel("entries"));
		resource.add(linkTo(methodOn(ContestController.class).create(contest)).withRel("_create"));
		resource.add(linkTo(methodOn(ContestController.class).publishEvent(contest.getUuid(), null)).withRel("_publish"));
		return resource;
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#toResources(java.lang.Iterable)
	 */
	@Override
	public List<ContestResource> toResources(Iterable<? extends Contest> contests) {
		// TODO Auto-generated method stub
		return super.toResources(contests);
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#instantiateResource(java.lang.Object)
	 */
	@Override
	protected ContestResource instantiateResource(Contest entity) {
		ContestResource resource = new ContestResource();
		resource.setId(entity.getId());
		resource.setUuid(entity.getUuid());
		resource.setTitle(entity.getTitle());
		resource.setDescription(entity.getDescription());
		
		return resource;
	}
}
