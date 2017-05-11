package view;

import controller.BasicSettingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Employee;
import model.Position;

import java.net.URL;

public class BasicSettingView extends View {

    private BasicSettingController controller = new BasicSettingController();
    int index = 1;

    public void start(Stage stage) {
        try {
            //creates root elements
            BorderPane pane = new BorderPane();
            VBox vboxContainer = new VBox();
            VBox vboxContents = new VBox();

            /*
            * Title
            */
            Label title = new Label("BASIC SETTING");
            title.setFont(new Font(26));

            /*
            * Form
            */

            //Form for company name
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(50, 0, 50, 0));
            TextField companyName = new TextField();
            Label labelCompanyName = new Label();
            labelCompanyName.setText("Company name:");
            labelCompanyName.setLabelFor(companyName);

            grid.add(labelCompanyName, 0, 0, 1, 1);
            grid.add(companyName, 1, 0, 3, 1);

            //form for period type
            ToggleGroup group = new ToggleGroup();
            RadioButton rb1 = new RadioButton("Every month");
            rb1.setToggleGroup(group);
            RadioButton rb2 = new RadioButton("Every week");
            rb2.setToggleGroup(group);
            RadioButton rb3 = new RadioButton("Every 2week");
            rb3.setToggleGroup(group);
            Label lb = new Label();
            lb.setText("Period Type:");
            //lb.setLabelFor(companyName);

            grid.add(lb, 0, 3, 1, 1);
            grid.add(rb1, 1, 3, 1, 1);
            grid.add(rb2, 2, 3, 1, 1);
            grid.add(rb3, 3, 3, 1, 1);


            /*
            * Add shift position
            */

            Label positionTitle = new Label("Create shift position");
            positionTitle.setFont(new Font(26));


            TextField addPositionName = new TextField();
            addPositionName.setPromptText("e.g. Waiter");

            GridPane posGrid = new GridPane();
            posGrid.setAlignment(Pos.CENTER);
            posGrid.setHgap(10);
            posGrid.setVgap(10);
            posGrid.setPadding(new Insets(50, 0, 50, 0));
            Button addButton = new Button("Add");


            posGrid.add(addPositionName,0,0,4,1);
            posGrid.add(addButton,5,0,1,1);

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Text posName = new Text();
                    posName.setText(addPositionName.getText());
                    addPos(posName,posGrid,index);
                }
            });



            /*
            * Start button
            */

            Button btnStart = new Button("START!");
            btnStart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                    	ManagerHomeView managerHome = new ManagerHomeView();
                    	managerHome.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //sets styles

            // adds components to the vboxes
            vboxContents.getChildren().addAll(title, grid, positionTitle, posGrid, btnStart);

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

            pane.setId("Basic-setting");


            //Add scene to the stage
            stage.setScene(scene);
            //Show stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        {
        }
    }

    public void addPos(Text name, GridPane grid, int index) {
        Button deleteButton = new Button("X");
        deleteButton.getStyleClass().add("deleteButton");
        grid.add(name, 0, index, 5, 1);
        grid.add(deleteButton, 6, index, 1, 1);
        plusIndex();
    }

    public int plusIndex() {
        return index++;
    }
}