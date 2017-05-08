package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;
import utils.SchedulePeriod;

public class Manager extends User{
	
	private Team team;
	
	public Manager(){
		
	}
	
	public Manager(Long managerId){
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT * from user WHERE id = "+managerId);
			if(rs.next()){
				populateUser(rs);
				this.team = getTeamFromDb(managerId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Manager(User user){
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setType(user.getType());
		this.setTeam(this.getTeamFromDb(user.getId()));		
	}
	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	private Team getTeamFromDb(Long managerId) {
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT * from team WHERE managerUserId = "+managerId);
			if(rs.next()){
				Team t = new Team();
				t.setId(rs.getLong("id"));
				t.setManager(this);
				t.setName(rs.getString("name"));
				t.setScheduleType(SchedulePeriod.getEnum(rs.getInt("periodType")));
				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
