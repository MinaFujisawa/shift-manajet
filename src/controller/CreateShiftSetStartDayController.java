package controller;

import view.CreateShiftSetStartDayView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CreateShiftSetStartDayController extends Controller {

    public LocalDate currentDate (){
        LocalDate localDate = LocalDate.now();
        return localDate;
    }
}

