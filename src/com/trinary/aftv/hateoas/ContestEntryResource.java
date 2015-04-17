package com.trinary.aftv.hateoas;

import org.springframework.hateoas.ResourceSupport;

public class ContestEntryResource extends ResourceSupport {
	protected long id;
	protected String uuid;
	protected String title;
	protected String description;
	protected String artist;
	
	/**
	 * @return the id
	 */
	public long getEntryId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setEntryId(long id) {
		this.id = id;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
}
