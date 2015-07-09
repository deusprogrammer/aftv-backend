package com.trinary.aftv.hateoas;

import java.util.List;

import com.trinary.aftv.persistence.entity.Voter;

public interface VoterResourceAssembler {

	public abstract VoterResource toResource(Voter arg0);

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#toResources(java.lang.Iterable)
	 */
	public abstract List<VoterResource> toResources(
			Iterable<? extends Voter> arg0);

}