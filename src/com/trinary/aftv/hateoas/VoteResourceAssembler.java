package com.trinary.aftv.hateoas;

import java.util.List;

import com.trinary.aftv.persistence.entity.Vote;

public interface VoteResourceAssembler {

	public abstract VoteResource toResource(Vote arg0);

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#toResources(java.lang.Iterable)
	 */
	public abstract List<VoteResource> toResources(Iterable<? extends Vote> arg0);

}