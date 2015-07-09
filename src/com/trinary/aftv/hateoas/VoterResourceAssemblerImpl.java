package com.trinary.aftv.hateoas;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.trinary.aftv.controllers.VoterController;
import com.trinary.aftv.persistence.entity.Voter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class VoterResourceAssemblerImpl extends ResourceAssemblerSupport<Voter, VoterResource> implements VoterResourceAssembler {

	public VoterResourceAssemblerImpl() {
		super(VoterController.class, VoterResource.class);
	}

	/* (non-Javadoc)
	 * @see com.trinary.aftv.hateoas.VoterResourceAssembler#toResource(com.trinary.aftv.persistence.entity.Voter)
	 */
	@Override
	public VoterResource toResource(Voter arg0) {
		VoterResource resource = this.createResourceWithId(arg0.getId(), arg0);
		resource.add(linkTo(methodOn(VoterController.class).getVotes(arg0.getId())).withRel("votes"));
		return resource;
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#toResources(java.lang.Iterable)
	 */
	/* (non-Javadoc)
	 * @see com.trinary.aftv.hateoas.VoterResourceAssembler#toResources(java.lang.Iterable)
	 */
	@Override
	public List<VoterResource> toResources(Iterable<? extends Voter> arg0) {
		// TODO Auto-generated method stub
		return super.toResources(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#instantiateResource(java.lang.Object)
	 */
	@Override
	protected VoterResource instantiateResource(Voter entity) {
		VoterResource resource = new VoterResource();
		resource.setVoterId(entity.getId());
		resource.setIpAddress(entity.getIpAddress());
		return resource;
	}
}
