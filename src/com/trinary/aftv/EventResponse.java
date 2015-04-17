package com.trinary.aftv;

public class EventResponse {
	public enum EventStatus {
		SUCCESS, FAILURE;
	}
	
	protected EventStatus status;
	protected String message;
	
	/**
	 * @return the status
	 */
	public EventStatus getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(EventStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
