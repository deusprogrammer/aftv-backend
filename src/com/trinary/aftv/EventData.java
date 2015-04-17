package com.trinary.aftv;

import com.trinary.aftv.hateoas.ContestEntryResource;
import com.trinary.aftv.hateoas.ContestResource;

public class EventData {
	protected ContestResource contest;
	protected ContestEntryResource contestEntry;
	protected String trigger;
	
	/**
	 * @return the contest
	 */
	public ContestResource getContest() {
		return contest;
	}
	
	/**
	 * @param contest the contest to set
	 */
	public void setContest(ContestResource contest) {
		this.contest = contest;
	}
	
	/**
	 * @return the contestEntry
	 */
	public ContestEntryResource getContestEntry() {
		return contestEntry;
	}
	
	/**
	 * @param contestEntry the contestEntry to set
	 */
	public void setContestEntry(ContestEntryResource contestEntry) {
		this.contestEntry = contestEntry;
	}

	/**
	 * @return the trigger
	 */
	public String getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
}