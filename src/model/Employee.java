package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.DatabaseConnection;

public class Employee extends User{
	
	private ArrayList<Shift> shifts;
	private Position position;
	private ArrayList<Availability> availabilities;
	
	public Employee(){
		
	}
	
	public Employee(Long employeeId){
		super(employeeId);
	}
	
	public Employee(Long employeeId, Long positionId){
		super(employeeId);
		this.shifts = new ArrayList<Shift>();
		this.position = new Position(positionId);
		loadShifts(this);
	}
	
	public Employee(String employeeName){
		ResultSet rs;		
		try {
			rs = DatabaseConnection.executeQuery("SELECT * from user WHERE name='"+employeeName+"'");
			if(rs.next()){
				populateUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	private void loadShifts(Employee employee) {
		ResultSet rs;		
		try {
			rs = DatabaseConnection.executeQuery("SELECT id from shift WHERE userId="+employee.getId());
			while(rs.next()){
				Shift shift = new Shift(rs.getLong("id"));
				shift.setUser(employee);
				employee.shifts.add(shift);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void loadAvailabilities(Employee employee) {
		ResultSet rs;		
		try {
			rs = DatabaseConnection.executeQuery("SELECT id from availability WHERE userId="+employee.getId());
			while(rs.next()){
				Availability aval = new Availability(rs.getLong("id"));
				aval.setEmployee(employee);
				employee.availabilities.add(aval);
			}
			Collections.sort(employee.availabilities, new Comparator<Availability>() {
		        @Override
		        public int compare(Availability avl1, Availability avl2)
		        {
		            return  avl1.getWeekDay().compareTo(avl2.getWeekDay());
		        }
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public ArrayList<Shift> getShifts() {
		if(this.shifts == null)
		{
			loadShifts(this);
		}
		return this.shifts;
	}

	public void setShifts(ArrayList<Shift> shifts) {
		this.shifts = shifts;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public ArrayList<Availability> getAvailabilities() {
		if(availabilities == null){
			loadAvailabilities(this);
		}
		return availabilities;
	}

	public void setAvailabilities(ArrayList<Availability> availabilities) {
		this.availabilities = availabilities;
	}
	
	public void saveEmployeePosition(Position pos, Team team){
		DatabaseConnection.executeQuery("INSERT INTO userTeam VALUES ("
				+ "NULL, "
				+ this.getId() + ", "
				+ team.getId() + ", "
				+ pos.getId() + ") ");		
	}
	
}
