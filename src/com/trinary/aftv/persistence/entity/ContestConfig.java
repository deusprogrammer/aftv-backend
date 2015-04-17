package com.trinary.aftv.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contestConfig")
public class ContestConfig {
	@Id
	@GeneratedValue
	protected long id;
	
	@Column
	protected Integer min;
	
	@Column
	protected Integer max;
	
	@Column
	protected Integer ratingIncrement;
	
	@Column
	@Enumerated(EnumType.STRING)
	protected VotingInterfaceStyle style;

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
	 * @return the min
	 */
	public Integer getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * @return the ratingIncrement
	 */
	public Integer getRatingIncrement() {
		return ratingIncrement;
	}

	/**
	 * @param ratingIncrement the ratingIncrement to set
	 */
	public void setRatingIncrement(Integer ratingIncrement) {
		this.ratingIncrement = ratingIncrement;
	}

	/**
	 * @return the style
	 */
	public VotingInterfaceStyle getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(VotingInterfaceStyle style) {
		this.style = style;
	}
}