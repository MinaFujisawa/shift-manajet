package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.DatabaseConnection;

public class Employee extends User{
	
	private ArrayList<Shift> shifts;
	private Position position;
	
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

}
