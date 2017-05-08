package view;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controller.ManagerScheduleController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Schedule;

public class ManagerScheduleView extends View{
	
	private ManagerScheduleController controller = new ManagerScheduleController();

	@Override
	public void start(Stage stage) throws Exception {
		
		VBox root = new VBox();
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());
		
		addNavigationBar(root);
		
		GridPane calendar = new GridPane();
		calendar.minWidth(800);
		calendar.setId("schedule-calendar");
		
		Text staffColumn = new Text("STAFF");
		calendar.add(staffColumn, 0, 1);
		
		
		Schedule schedule = controller.getNewSchedule();
		
//		schedule.setStartDate(new Date(System.currentTimeMillis()));
//		GregorianCalendar endDate = new GregorianCalendar();
//		endDate.setTime(new Date(System.currentTimeMillis()));
//		endDate.add(Calendar.DAY_OF_MONTH, 7);
//		schedule.setEndDate(new Date(endDate.getTimeInMillis()));
		
		
		GregorianCalendar days = new GregorianCalendar();
		days.setTime(schedule.getStartDate());
		GregorianCalendar end = new GregorianCalendar();
		end.setTime(schedule.getEndDate());
		
		SimpleDateFormat weekFormar = new SimpleDateFormat("EEE");
		
		int i=1;
		String text = new String();
		while(days.getTimeInMillis() < end.getTimeInMillis()){
			text = weekFormar.format(days.getTime()).toUpperCase() + " " + days.get(Calendar.DAY_OF_MONTH);
			calendar.add(new Text(text), i++, 1);
			days.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		root.getChildren().add(calendar);
		stage.setScene(scene);
		stage.show();
	}

}
