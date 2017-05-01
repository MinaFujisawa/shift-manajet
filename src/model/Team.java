package model;

import utils.SchedulePeriod;

public class Team {
	
	private Long id;
	private String name;
	private SchedulePeriod scheduleType;
	private Manager manager;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SchedulePeriod getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(SchedulePeriod scheduleType) {
		this.scheduleType = scheduleType;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
