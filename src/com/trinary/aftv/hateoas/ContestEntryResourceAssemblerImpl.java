package com.trinary.aftv.hateoas;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.trinary.aftv.controllers.ContestEntryController;
import com.trinary.aftv.persistence.entity.ContestEntry;

public class ContestEntryResourceAssemblerImpl extends ResourceAssemblerSupport<ContestEntry, ContestEntryResource> implements ContestEntryResourceAssembler {

	public ContestEntryResourceAssemblerImpl() {
		super(ContestEntryController.class, ContestEntryResource.class);
	}

	/* (non-Javadoc)
	 * @see com.trinary.aftv.hateoas.ContestEntryResourceAssembler#toResource(com.trinary.aftv.persistence.entity.ContestEntry)
	 */
	@Override
	public ContestEntryResource toResource(ContestEntry entry) {
		ContestEntryResource resource = this.createResourceWithId(entry.getUuid(), entry);
		return resource;
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#toResources(java.lang.Iterable)
	 */
	@Override
	public List<ContestEntryResource> toResources(
			Iterable<? extends ContestEntry> entries) {
		// TODO Auto-generated method stub
		return super.toResources(entries);
	}

	/* (non-Javadoc)
	 * @see org.springframework.hateoas.mvc.ResourceAssemblerSupport#instantiateResource(java.lang.Object)
	 */
	@Override
	protected ContestEntryResource instantiateResource(ContestEntry entry) {
		ContestEntryResource resource = new ContestEntryResource();
		resource.setEntryId(entry.getId());
		resource.setUuid(entry.getUuid());
		resource.setTitle(entry.getTitle());
		resource.setDescription(entry.getDescription());
		resource.setArtist(entry.getArtist());
		
		return resource;
	}
}
