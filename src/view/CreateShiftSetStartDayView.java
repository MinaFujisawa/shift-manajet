/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;
import controller.CreateShiftSetStartDayController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.net.URL;
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

            Button btn = new Button("START CREATING THE SHIFT");

            //sets styles
            heading.setFont(Font.font(22));
            subHeading.setFont(Font.font(14));
            VBox.setMargin(subHeading, new Insets(40, 0, 0, 0));
            VBox.setMargin(checkInDatePicker, new Insets(25, 0, 50, 0));

            // adds components to the vboxes
            addNavigationBar(vboxContainer);
            vboxContents.getChildren().addAll(heading, subHeading, checkInDatePicker, btn);

            // sets alignment
            vboxContainer.setAlignment(Pos.TOP_CENTER);
            vboxContents.setAlignment(Pos.CENTER);

            // sets components
            pane.setTop(vboxContainer);
            pane.setCenter(vboxContents);

            //
            LocalDate startDate = checkInDatePicker.getValue();
            System.out.println(startDate);

            // creates scene
            Scene scene = new Scene(pane, 1280, 800);

            //Import general css file
            scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());

            pane.setId("set-start-date");


//            @Override
//            public void handle (ActionEvent event){
//                getStartDate(checkInDatePicker);
//            }


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
