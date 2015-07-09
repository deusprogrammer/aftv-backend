package com.trinary.aftv.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="contest")
public class Contest {
	@Id
	@GeneratedValue
	protected long id;
	
	@Column
	protected String uuid;
	
	@Column
	protected String title;
	
	@Column
	protected String description;
	
	@Column
	protected Date lastEvent;
	
	@OneToOne
	protected ContestEntry nowPlaying;

	@OneToOne
	protected ContestConfig config;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="contest")
	protected List<ContestEntry> entries;

	/**
	 * @return the id
	 */
	public long getId() {
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
	 * @return the nowPlaying
	 */
	public ContestEntry getNowPlaying() {
		return nowPlaying;
	}

	/**
	 * @param nowPlaying the nowPlaying to set
	 */
	public void setNowPlaying(ContestEntry nowPlaying) {
		this.nowPlaying = nowPlaying;
	}

	/**
	 * @return the config
	 */
	public ContestConfig getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(ContestConfig config) {
		this.config = config;
	}

	/**
	 * @return the entries
	 */
	public List<ContestEntry> getEntries() {
		return entries;
	}

	/**
	 * @param entries the entries to set
	 */
	public void setEntries(List<ContestEntry> entries) {
		this.entries = entries;
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
