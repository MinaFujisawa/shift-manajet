package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CreateShiftSetStartDayController extends Controller {
    public LocalDate currentDate (){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        //LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}

