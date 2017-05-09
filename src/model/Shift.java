package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import application.DatabaseConnection;

public class Shift {
	
	private Long id;
	private Employee user;
	private Schedule schedule;
	private Date date;
	private Time startTime;
	private Time endTime;
	
	public Shift(){
		
	}
	
	public Shift(Long shiftId) {
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT * from shift where id="+id);
			if(rs.next()){
				this.id = rs.getLong("id");
				this.user = new Employee(rs.getLong("userId"));
				this.schedule = new Schedule(rs.getLong("scheduleId"));
				this.date = rs.getDate("date");
				this.startTime = rs.getTime("startTime");
				this.endTime = rs.getTime("endTime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Shift(Long shiftId, Employee employee) {
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT * from shift where id="+id);
			if(rs.next()){
				this.id = rs.getLong("id");
				this.user = employee;
				this.schedule = new Schedule(rs.getLong("scheduleId"));
				this.date = rs.getDate("date");
				this.startTime = rs.getTime("startTime");
				this.endTime = rs.getTime("endTime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
}
