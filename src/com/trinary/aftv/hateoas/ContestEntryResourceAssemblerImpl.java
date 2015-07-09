package com.trinary.aftv.hateoas;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.trinary.aftv.controllers.ContestController;
import com.trinary.aftv.controllers.ContestEntryController;
import com.trinary.aftv.persistence.entity.ContestEntry;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
		resource.add(linkTo(methodOn(ContestController.class).get(entry.getContest().getUuid())).withRel("contest"));
		resource.add(linkTo(methodOn(ContestController.class).getVotes(entry.getContest().getUuid(), entry.getUuid())).withRel("votes"));
		resource.add(linkTo(methodOn(ContestController.class).getThumbnail(null, entry.getContest().getUuid(), entry.getUuid())).withRel("thumbnail"));
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
