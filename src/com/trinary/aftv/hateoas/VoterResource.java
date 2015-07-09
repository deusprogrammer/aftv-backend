package com.trinary.aftv.hateoas;

import org.springframework.hateoas.ResourceSupport;

public class VoterResource extends ResourceSupport {
	protected long id;
	protected String ipAddress;
	
	/**
	 * @return the id
	 */
	public long getVoterId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setVoterId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}