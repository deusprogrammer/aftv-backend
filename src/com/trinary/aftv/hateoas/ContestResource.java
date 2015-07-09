package com.trinary.aftv.hateoas;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

public class ContestResource extends ResourceSupport {
	protected long id;
	protected String uuid;
	protected String title;	
	protected String description;
	protected Date lastEvent;
	
	/**
	 * @return the id
	 */
	public long getContestId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	 * @return the lastEvent
	 */
	public Date getLastEvent() {
		return lastEvent;
	}
	/**
	 * @param lastEvent the lastEvent to set
	 */
	public void setLastEvent(Date lastEvent) {
		this.lastEvent = lastEvent;
	}
}