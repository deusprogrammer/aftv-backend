package com.trinary.aftv.hateoas;

import java.util.List;

import com.trinary.aftv.persistence.entity.Contest;

public interface ContestResourceAssembler {
	public ContestResource toResource(Contest contest);
	public List<ContestResource> toResources(Iterable<? extends Contest> contests);
}
