package com.trinary.aftv.hateoas;

import java.util.List;

import com.trinary.aftv.persistence.entity.ContestEntry;

public interface ContestEntryResourceAssembler {
	public ContestEntryResource toResource(ContestEntry entry);
	public List<ContestEntryResource> toResources(
			Iterable<? extends ContestEntry> entries);
}