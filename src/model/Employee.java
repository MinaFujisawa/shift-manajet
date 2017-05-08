package model;

import java.util.ArrayList;

public class Employee extends User{
	
	private ArrayList<Shift> shifts;

	public ArrayList<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(ArrayList<Shift> shifts) {
		this.shifts = shifts;
	}
	
}
