package com.trinary.aftv.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contestEntry")
public class ContestEntry {
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
	protected String artist;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="entry")
	protected List<Vote> votes;
	
	@ManyToOne
	@JoinColumn(name="contest", nullable=false)
	protected Contest contest;

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

	/**
	 * @return the vote
	 */
	public List<Vote> getVotes() {
		return votes;
	}

	/**
	 * @param vote the vote to set
	 */
	public void setVotes(List<Vote> vote) {
		this.votes = vote;
	}

	/**
	 * @return the contest
	 */
	public Contest getContest() {
		return contest;
	}

	/**
	 * @param contest the contest to set
	 */
	public void setContest(Contest contest) {
		this.contest = contest;
	}
}