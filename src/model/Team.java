package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.DatabaseConnection;
import utils.SchedulePeriod;

public class Team {
	
	private Long id;
	private String name;
	private SchedulePeriod scheduleType;
	private Manager manager;
	private ArrayList<Employee> employees;
	
	public Team(){
		
	}
	
	public Team(Long teamId){
		try {
			ResultSet rs;
			rs = DatabaseConnection.executeQuery("SELECT * from team WHERE id = "+teamId);
			if(rs.next()){
				this.id = rs.getLong("id");
				this.name = rs.getString("name");
				this.scheduleType = SchedulePeriod.getEnum(rs.getInt("periodType"));
				this.manager = new Manager(rs.getLong("managerUserId"));
				this.employees = new ArrayList<Employee>();
				rs = DatabaseConnection.executeQuery("SELECT userId, positionId from userTeam WHERE id = "+teamId);
				while (rs.next()) {
					this.employees.add(new Employee(rs.getLong("userId"), rs.getLong("positionId")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Team(Manager manager){
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT id from team WHERE managerUserId = "+manager.getId());
			if(rs.next()){
				rs = DatabaseConnection.executeQuery("SELECT * from team WHERE id = "+rs.getLong("id"));
				if(rs.next()){
					this.id = rs.getLong("id");
					this.name = rs.getString("name");
					this.scheduleType = SchedulePeriod.getEnum(rs.getInt("periodType"));
					this.manager = new Manager(rs.getLong("managerUserId"));
					this.employees = new ArrayList<Employee>();
					rs = DatabaseConnection.executeQuery("SELECT userId, positionId from userTeam WHERE id = "+rs.getLong("id"));
					while (rs.next()) {
						this.employees.add(new Employee(rs.getLong("userId"), rs.getLong("positionId")));
					}
				}
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
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	
}
