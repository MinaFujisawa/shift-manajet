package view;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controller.ManagerScheduleController;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Availability;
import model.Position;
import model.Schedule;
import model.Shift;
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
		//User image column
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(6);
		col.halignmentProperty().set(HPos.CENTER);
		calendar.getColumnConstraints().add(col);
		
		//Employee name column
		col = new ColumnConstraints();
		col.setPercentWidth(10);
		col.halignmentProperty().set(HPos.CENTER);
		calendar.getColumnConstraints().add(col);
		
		//Week days column
		for(int i=0; i<7; i++){
			col = new ColumnConstraints();
			col.setPercentWidth(12);
			col.halignmentProperty().set(HPos.CENTER);
			calendar.getColumnConstraints().add(col);
		}
		
		Text staffColumn = new Text("STAFF");
		GridPane.setColumnSpan(staffColumn, 2);
		calendar.add(staffColumn, 0, 1);
		
		
		String text;
		Image image = new Image("file:resources/images/icon_user.png");
		
		//Column team on the left
		Team team = new Team(controller.getLoggedUserAsManager());
		ArrayList<Availability> availabilities;
		for(int j=0; j<team.getEmployees().size(); j++){
			//fill staff name
			text = team.getEmployees().get(j).getName();
			calendar.add(new ImageView(image), 0, j+2);
			calendar.add(new Text(text), 1, j+2);//starts from third line					
		}
		
		Schedule schedule = controller.getNewSchedule();
		
		GregorianCalendar days = new GregorianCalendar();
		days.setTime(schedule.getStartDate());
		GregorianCalendar end = new GregorianCalendar();
		end.setTime(schedule.getEndDate());
		
		SimpleDateFormat weekFormar = new SimpleDateFormat("EEE");
		
		int i=2;		
		while(days.getTimeInMillis() < end.getTimeInMillis()){
			text = weekFormar.format(days.getTime()).toUpperCase() + " " + days.get(Calendar.DAY_OF_MONTH);
			calendar.add(new Text(text), i, 1);
			days.add(Calendar.DAY_OF_MONTH, 1);
			
			//check if employee has exception for that day
			
			//if no exception get availability in that day if exists
			for(int j=0; j<team.getEmployees().size(); j++){
				//fill availability
				availabilities = team.getEmployees().get(j).getAvailabilities();
				for(int m=0; m<availabilities.size(); m++){
					if(availabilities.get(m).getWeekDay() == days.get(Calendar.DAY_OF_WEEK)){
						text = availabilities.get(m).getStartTime().toString().substring(0,5) 
								+ " - " + availabilities.get(m).getEndTime().toString().substring(0,5);
						calendar.add(new Text(text), i, j+2);
						break;
					}
				}
			}
			i++;
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
