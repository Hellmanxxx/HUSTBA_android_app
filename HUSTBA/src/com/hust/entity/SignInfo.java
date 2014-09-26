package com.hust.entity;

public class SignInfo {
	private int id;
	private String name;
	private int totalDays;
	private int signDays;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the totalDays
	 */
	public int getTotalDays() {
		return totalDays;
	}
	/**
	 * @param totalDays the totalDays to set
	 */
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	/**
	 * @return the signDays
	 */
	public int getSignDays() {
		return signDays;
	}
	/**
	 * @param signDays the signDays to set
	 */
	public void setSignDays(int signDays) {
		this.signDays = signDays;
	}
	/**
	 * @return the lateDays
	 */
	public int getLateDays() {
		return lateDays;
	}
	/**
	 * @param lateDays the lateDays to set
	 */
	public void setLateDays(int lateDays) {
		this.lateDays = lateDays;
	}
	private int lateDays;
	
}
