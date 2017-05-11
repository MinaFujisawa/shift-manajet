package controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Schedule;
import model.Team;

public class ManagerScheduleController extends Controller{
	
	public Schedule getSchedule(Long scheduleId){
		return new Schedule(scheduleId);
	}
	
	public Schedule getNewSchedule(Date startDate){
		Schedule schedule = new Schedule();
		schedule.setStartDate(startDate);
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(startDate);
		
		switch (new Team(getLoggedUserAsManager()).getScheduleType()) {
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
