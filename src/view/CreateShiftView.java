package view;

//import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

//import controller.LoginController;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import model.Shift;
//import java.sql.Date;
//import java.sql.Time;

/**
 * Created by marusiaochoaramirez on 4/5/17.
 */
public class CreateShiftView extends View {

    //private LoginController controller = new LoginController();
	
	private String date;
	private Time suggestedStartTime;
	private Time suggestedEndTime;

    @Override

    public void start(Stage stage){
        try {

            GridPane root = new GridPane();

            root.setHgap(10);
            root.setVgap(10);
            root.setPadding(new Insets(25, 25, 25, 25));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());

            Image userImage = new Image("file:resources/images/icon_user.png");
            ImageView userView = new ImageView(userImage);

            Label imageLabel = new Label("CREATE SHIFT FOR JACK", userView);
            GridPane.setColumnSpan(imageLabel, GridPane.REMAINING);

            Separator line1 = new Separator();
            line1.setValignment(VPos.BOTTOM);
            line1.setHalignment(HPos.CENTER);
            GridPane.setColumnSpan(line1, GridPane.REMAINING);

            Text textDate = new Text(date);

            Text fromText = new Text("FROM");
            Text toText = new Text("TO");
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            TextField fromHour = new TextField(format.format(suggestedStartTime));
            fromHour.setMaxSize(50, 10);
            fromHour.setEditable(true);


            TextField toHour = new TextField(format.format(suggestedEndTime));
            toHour.setMaxSize(50, 10);
            toHour.setEditable(true);

            Separator line2 = new Separator();
            line2.setValignment(VPos.BOTTOM);
            line2.setHalignment(HPos.CENTER);
            GridPane.setColumnSpan(line2, GridPane.REMAINING);


            Button btnCancel = new Button("Cancel");
            btnCancel.getStyleClass().add("subButton");

            btnCancel.setOnAction (new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });


            Button btnDelete = new Button("Delete");
            btnDelete.getStyleClass().add("subButton");



            Button btnSave = new Button ("Save");
            btnSave.setOnAction (new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	stage.close();
                }
            });

            HBox boxButtons = new HBox();
            boxButtons.alignmentProperty().setValue(Pos.BOTTOM_RIGHT);
            boxButtons.setSpacing(10);
            boxButtons.getChildren().addAll(btnCancel, btnDelete, btnSave);
            GridPane.setColumnSpan(boxButtons, GridPane.REMAINING);


            root.add(userView,0,0);
            root.add(imageLabel,0,0);
            root.add(line1, 0, 1);
            root.add(textDate, 0,2);
            root.add(fromHour, 0,5);
            root.add(fromText, 0, 4);
            root.add(toHour, 1,5);
            root.add(toText, 1, 4);
            root.add(line2, 0, 7);
            root.add(boxButtons, 0, 8);


            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Time getSuggestedStartTime() {
		return suggestedStartTime;
	}

	public void setSuggestedStartTime(Time suggestedStartTime) {
		this.suggestedStartTime = suggestedStartTime;
	}

	public Time getSuggestedEndTime() {
		return suggestedEndTime;
	}

	public void setSuggestedEndTime(Time suggestedEndTime) {
		this.suggestedEndTime = suggestedEndTime;
	}
    
}


