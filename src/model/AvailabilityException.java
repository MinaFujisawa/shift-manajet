package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import application.DatabaseConnection;

public class AvailabilityException {
	
	private Long id;
	private Employee employee;
	private Date date;
	private Time startTime;
	private Time endTime;
	
	public AvailabilityException(){
		
	}
	
	public AvailabilityException(Long id){
		try {
			ResultSet rs;
			rs = DatabaseConnection.executeQuery("SELECT * from availabilityException WHERE id = "+id);
			if(rs.next()){
				this.id = rs.getLong("id");
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
