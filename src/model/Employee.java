package model;

import java.util.ArrayList;

public class Employee extends User{
	
	private ArrayList<Shift> shifts;
	private Position position;
	
	public Employee(){
		
	}
	
	public Employee(Long userId){
		
	}

	public ArrayList<Shift> getShifts() {
		return shifts;
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
