package controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Manager;
import model.Schedule;

public class ManagerScheduleController extends Controller{
	
	public Schedule getSchedule(Long scheduleId){
		Schedule schedule = new Schedule(scheduleId);
		return schedule;
	}
	
	public Schedule getNewSchedule(){
		Schedule schedule = new Schedule();
		schedule.setStartDate(new Date(System.currentTimeMillis()));
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(new Date(System.currentTimeMillis()));
		
		switch (new Manager(getLoggedUser()).getTeam().getScheduleType()) {
		case WEEKLY:
			endDate.add(Calendar.DAY_OF_WEEK, 7);
			break;
		case BIWEEKLY:
			endDate.add(Calendar.DAY_OF_WEEK, 14);
			break;
		case MONTHLY:
			endDate.add(Calendar.MONTH, 1);
			break;
		}		
		schedule.setEndDate(new Date(endDate.getTimeInMillis()));
		return schedule;
	}

}
