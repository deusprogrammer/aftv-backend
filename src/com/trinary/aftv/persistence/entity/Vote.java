package com.trinary.aftv.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vote")
public class Vote {
	@Id
	@GeneratedValue
	protected long id;
	
	@Column
	protected Integer value;
	
	@Column
	protected String comment;
	
	@ManyToOne
	@JoinColumn(name="voter", nullable=false)
	protected Voter voter;
	
	@ManyToOne
	@JoinColumn(name="entry", nullable=false)
	protected ContestEntry entry;

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
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the voter
	 */
	public Voter getVoter() {
		return voter;
	}

	/**
	 * @param voter the voter to set
	 */
	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	/**
	 * @return the entry
	 */
	public ContestEntry getEntry() {
		return entry;
	}

	/**
	 * @param entry the entry to set
	 */
	public void setEntry(ContestEntry entry) {
		this.entry = entry;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
