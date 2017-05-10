package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import application.DatabaseConnection;

public class Availability {
	
	private Long id;
	private Employee employee;
	private Integer weekDay;
	private Time startTime;
	private Time endTime;
	
	public Availability(){
		
	}
	
	public Availability(Long availabilityId){
		ResultSet rs;		
		try {
			rs = DatabaseConnection.executeQuery("SELECT * from availability WHERE id="+availabilityId);
			if(rs.next()){
				this.id = rs.getLong("id");
				this.employee = new Employee(rs.getLong("userId"));
				this.weekDay = rs.getInt("weekDay");
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
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
