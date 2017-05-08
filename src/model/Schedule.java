package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;

public class Schedule {
	
	private Long id;
	private Date startDate ;
	private Date endDate ;
	private Team team;
	
	public Schedule(){		
	}
	
	public Schedule(Long scheduleId){		
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT * from schedule WHERE id = "+scheduleId);
			if(rs.next()){
				this.id = rs.getLong("id");
				this.startDate = rs.getDate("startDate");
				this.endDate = rs.getDate("endDate");
				this.team = new Team(rs.getLong("teamId"));
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
}
