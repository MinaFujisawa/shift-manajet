/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;
import controller.CreateShiftSetStartDayController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;


public class CreateShiftSetStartDayView extends View {

    private CreateShiftSetStartDayController controller = new CreateShiftSetStartDayController();

    @Override
    public void start(Stage stage) {
        try {
            //creates root elements
            BorderPane pane = new BorderPane();
            VBox vboxContainer = new VBox();
            VBox vboxContents = new VBox();

            //Creates components
            Label heading = new Label("SET THE START DATE");
            Label subHeading = new Label("This shift will start at");
            DatePicker checkInDatePicker = new DatePicker();

            //sets default date
            checkInDatePicker.setValue(controller.currentDate());


            Button submitBtn = new Button("START CREATING THE SHIFT");
            submitBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ManagerScheduleView mv = new ManagerScheduleView();

                    LocalDate startDate = checkInDatePicker.getValue();
                    mv.setStartDate(Date.valueOf(startDate));
                    try {
                        mv.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //sets styles
            heading.setFont(Font.font(22));
            subHeading.setFont(Font.font(14));
            VBox.setMargin(subHeading, new Insets(40, 0, 0, 0));
            VBox.setMargin(checkInDatePicker, new Insets(25, 0, 50, 0));

            // adds components to the vboxes
            addNavigationBar(vboxContainer, stage);
            vboxContents.getChildren().addAll(heading, subHeading, checkInDatePicker, submitBtn);

            // sets alignment
            vboxContainer.setAlignment(Pos.TOP_CENTER);
            vboxContents.setAlignment(Pos.CENTER);

            // sets components
            pane.setTop(vboxContainer);
            pane.setCenter(vboxContents);



            // creates scene
            Scene scene = new Scene(pane, 1280, 800);

            //Import general css file
            scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());

            pane.setId("set-start-date");



            //Add scene to the stage
            stage.setScene(scene);
            //Show stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } {
        }
    }


}
