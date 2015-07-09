package com.trinary.aftv.hateoas;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.trinary.aftv.controllers.VoteController;
import com.trinary.aftv.persistence.entity.Vote;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class VoteResourceAssemblerImpl extends ResourceAssemblerSupport<Vote, VoteResource> implements VoteResourceAssembler {

	public VoteResourceAssemblerImpl() {
		super(VoteController.class, VoteResource.class);
	}

	/* (non-Javadoc)
	 * @see com.trinary.aftv.hateoas.VoteResourceAssembler#toResource(com.trinary.aftv.persistence.entity.Vote)
	 */
	@Override
	public VoteResource toResource(Vote arg0) {
		VoteResource resource = this.createResourceWithId(arg0.getId(), arg0);
		resource.add(linkTo(methodOn(VoteController.class).getEntry(arg0.getId())).withRel("entry"));
		resource.add(linkTo(methodOn(VoteController.class).getVoter(arg0.getId())).withRel("owner"));
		return resource;
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#toResources(java.lang.Iterable)
	 */
	/* (non-Javadoc)
	 * @see com.trinary.aftv.hateoas.VoteResourceAssembler#toResources(java.lang.Iterable)
	 */
	@Override
	public List<VoteResource> toResources(Iterable<? extends Vote> arg0) {
		// TODO Auto-generated method stub
		return super.toResources(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#instantiateResource(java.lang.Object)
	 */
	@Override
	protected VoteResource instantiateResource(Vote entity) {
		VoteResource resource = new VoteResource();
		resource.setVoteId(entity.getId());
		resource.setComment(entity.getComment());
		resource.setValue(entity.getValue());
		return resource;
	}
}