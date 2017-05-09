/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class ManagerHomeView extends View {


    @Override
    public void start(Stage stage) {
        try {
            //creates root elements
            BorderPane pane = new BorderPane();
            VBox vboxContainer = new VBox();
            VBox vboxContents = new VBox();

            //Creates components
            Label dummyText = new Label("HOME SCREEN");

            //sets styles
            dummyText.setFont(Font.font(22));

            // adds components to the vboxes
            addNavigationBar(vboxContainer);
            vboxContents.getChildren().addAll(dummyText);

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
