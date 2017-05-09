package view;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controller.ManagerScheduleController;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Schedule;
import model.Team;

public class ManagerScheduleView extends View{
	
	private ManagerScheduleController controller = new ManagerScheduleController();
	
	private Date startDate;

	@Override
	public void start(Stage stage) throws Exception {
		
		VBox root = new VBox();
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());
		
		addNavigationBar(root);
		
		GridPane calendar = new GridPane();
		calendar.minWidth(800);
		calendar.setId("schedule-calendar");
		
		//set columns formating
		for(int i=0; i<8; i++){
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(12);
			col.halignmentProperty().set(HPos.CENTER);
			calendar.getColumnConstraints().add(col);
		}
		
		Text staffColumn = new Text("STAFF");
		calendar.add(staffColumn, 0, 1);
		
		
		Schedule schedule = controller.getNewSchedule();
		
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
		
		Team team = new Team(controller.getLoggedUserAsManager());
		for(int j=0; j<team.getEmployees().size(); j++){
			text = team.getEmployees().get(j).getName();
			calendar.add(new Text(text), 0, j+2);//starts from third line
		}
		
		root.getChildren().add(calendar);
		stage.setScene(scene);
		stage.show();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
