package com.trinary.aftv;

public class Event {
	protected String contestId;
	protected String entryId;
	protected String eventType;

	/**
	 * @return the contestId
	 */
	public String getContestId() {
		return contestId;
	}
	
	/**
	 * @param contestId the contestId to set
	 */
	public void setContestId(String contestId) {
		this.contestId = contestId;
	}
	
	/**
	 * @return the entryId
	 */
	public String getEntryId() {
		return entryId;
	}
	
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}
	
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
}