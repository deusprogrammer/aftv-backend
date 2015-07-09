package com.trinary.aftv.hateoas;

import org.springframework.hateoas.ResourceSupport;

public class VoteResource extends ResourceSupport {
	protected long id;
	protected Integer value;
	protected String comment;
	
	/**
	 * @return the id
	 */
	public long getVoteId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setVoteId(long id) {
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