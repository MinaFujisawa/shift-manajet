package view;

import controller.BasicSettingController;
import controller.LoginController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;


import static com.sun.javafx.tools.resource.DeployResource.Type.data;

public class BasicSettingView extends View {

    private BasicSettingController controller = new BasicSettingController();


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
            title.setFont(new Font(30));


            /*
            * Form
            */

            //Form for company name
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(80, 0, 50, 0));
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
            positionTitle.setFont(new Font(20));

            TableView<String> table = new TableView<>();
            ObservableList<String> data = FXCollections.observableArrayList();

            table.setEditable(true);

            TableColumn<String, String> positionNameCol = new TableColumn<>("Position name");
            positionNameCol.setMinWidth(400);
            positionNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("name"));


            table.setItems(data);
            table.getColumns().addAll(positionNameCol);

            TextField addPositionName = new TextField();
            addPositionName.setPromptText("Position name");
            addPositionName.setMaxWidth(positionNameCol.getPrefWidth());


            // add bottom
            Button addButton = new Button("Add");
            final Long posID;
            addButton.setOnAction((ActionEvent e) -> {
                data.add(addPositionName.getText());
                addPositionName.clear();
            });


            // delete bottom
            TableColumn<String, String> deleteCol = new TableColumn<>("Delete");
            deleteCol.setMinWidth(100);
            deleteCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            deleteCol.setCellFactory(param -> new TableCell<String, String>() {
                private final Button deleteButton = new Button("delete");

//                @Override
//                protected void updateItem(Position position, boolean empty) {
//                    super.updateItem(position, empty);
//
//                    if (position == null) {
//                        setGraphic(null);
//                        return;
//                    }
//
//                    setGraphic(deleteButton);
//                    //deleteButton.setOnAction(event -> data.remove(position));
//                }
            });

            table.getColumns().addAll(deleteCol);


            /*
            * Start button
            */

            Button btnStart = new Button("START!");
            btnStart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ManagerScheduleView mv = new ManagerScheduleView();

                    try {
                        mv.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //sets styles

            // adds components to the vboxes
            vboxContents.getChildren().addAll(title, grid, positionTitle, table, addPositionName, addButton, btnStart);

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

            //pane.setId("set-start-date");


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
}