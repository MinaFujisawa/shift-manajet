package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.DatabaseConnection;

public class Employee extends User{
	
	private Position position;
	private ArrayList<Shift> shifts = new ArrayList<Shift>();
	private ArrayList<Availability> availabilities = new ArrayList<Availability>();
	private ArrayList<AvailabilityException> exceptions = new ArrayList<AvailabilityException>();
	
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
			if(employee.shifts != null){
				Collections.sort(employee.shifts, new Comparator<Shift>() {
			        @Override
			        public int compare(Shift shift1, Shift shift2)
			        {
			            return  shift1.getDate().compareTo(shift2.getDate());
			        }
				});
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
			if(employee.availabilities != null){
				Collections.sort(employee.availabilities, new Comparator<Availability>() {
			        @Override
			        public int compare(Availability avl1, Availability avl2)
			        {
			            return  avl1.getWeekDay().compareTo(avl2.getWeekDay());
			        }
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void loadExceptions(Employee employee) {
		ResultSet rs;		
		try {
			rs = DatabaseConnection.executeQuery("SELECT id from availabilityException WHERE userId="+employee.getId()
				+ " AND date >= CURDATE()");
			while(rs.next()){
				AvailabilityException except = new AvailabilityException(rs.getLong("id"));
				except.setEmployee(employee);
				employee.exceptions.add(except);
			}
			if(employee.exceptions != null){
				Collections.sort(employee.exceptions, new Comparator<AvailabilityException>() {
			        @Override
			        public int compare(AvailabilityException except1, AvailabilityException except2)
			        {
			            return  except1.getDate().compareTo(except2.getDate());
			        }
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public ArrayList<Shift> getShifts() {
		if(this.shifts.size() == 0)
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
		if(availabilities.size() == 0){
			loadAvailabilities(this);
		}
		return availabilities;
	}

	public void setAvailabilities(ArrayList<Availability> availabilities) {
		this.availabilities = availabilities;
	}
	
	
	public ArrayList<AvailabilityException> getExceptions() {
		if(exceptions.size() == 0){
			loadExceptions(this);
		}
		return exceptions;
	}

	public void setExceptions(ArrayList<AvailabilityException> exceptions) {
		this.exceptions = exceptions;
	}

	public void saveEmployeePosition(Position pos, Team team){
		DatabaseConnection.executeUpdate("INSERT INTO userTeam VALUES ("
				+ "NULL, "
				+ this.getId() + ", "
				+ team.getId() + ", "
				+ pos.getId() + ") ");		
	}
	
}
