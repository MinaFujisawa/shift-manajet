package controller;

import model.Employee;
import model.Position;
import model.Team;
import view.CreateShiftSetStartDayView;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class ManagerStaffController extends Controller {

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void saveEmployeeInTeam(String employeeName, Position position){
        Employee employee = new Employee(employeeName);
        Team team = new Team(getLoggedUserAsManager());
        employee.saveEmployeePosition(position, team);
    }
}

