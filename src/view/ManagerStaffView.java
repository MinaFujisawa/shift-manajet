/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;

import com.sun.org.apache.xpath.internal.operations.String;
import controller.ManagerStaffController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.stage.StageStyle;
import model.Position;

import java.net.URL;


public class ManagerStaffView extends View {

    private ManagerStaffController controller = new ManagerStaffController();
    int index = 2;

    @Override
    public void start(Stage stage) {
        try {
            //creates root elements
            BorderPane pane = new BorderPane();
            VBox vboxContainer = new VBox();
            GridPane grid = new GridPane();
            grid.getStyleClass().add("grid");
            grid.setHgap(10);
            grid.setVgap(10);
            grid.getColumnConstraints().add(new ColumnConstraints(300)); // column 1
            grid.getColumnConstraints().add(new ColumnConstraints(300)); // column 2
            grid.getColumnConstraints().add(new ColumnConstraints(300)); // column 3


            //creates components
            Label hName = new Label("NAME");
            Label hID = new Label("ID");
            Label hPassword = new Label("PASSWORD");

            Button addStaffBtn = new Button("Add Staff");

            //table layout
            grid.add(addStaffBtn, 0, 0, 4, 1);
            grid.add(hName, 0, 1, 1, 1);
            grid.add(hID, 1, 1, 1, 1);
            grid.add(hPassword, 2, 1, 1, 1);

            //button
            addStaffBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //ManagerStaffAddView msa = new ManagerStaffAddView();

                    try {
                        // creates modal window
                        Stage winStage = new Stage();
                        winStage.initModality(Modality.APPLICATION_MODAL);
                        winStage.initStyle(StageStyle.UNDECORATED);
                        winStage.setTitle("Add new staff");

                        /****************
                         * Contents in the modal window
                         ***************/
                        //creates root elements
                        GridPane gridModal = new GridPane();
                        gridModal.setHgap(10);
                        gridModal.setVgap(10);
                        gridModal.getColumnConstraints().add(new ColumnConstraints(100)); // column 1
                        gridModal.getColumnConstraints().add(new ColumnConstraints(100)); // column 2

                        //creates components
                        Text hName = new Text("NAME");
                        Text hPosi = new Text("POSITION");
                        Button addStaffBtn = new Button("Add Staff");
                        TextField nameField = new TextField();
                        Position position = new Position();
                        Long positionID = position.getId();

                        ChoiceBox cb = new ChoiceBox(FXCollections.observableList(controller.getAllPositions()));
                        System.out.println(controller.getAllPositions());


                        //Styles
                        gridModal.setMinWidth(500);

                        //table layout
                        gridModal.add(hName, 0, 0, 1, 1);
                        gridModal.add(nameField, 1, 0, 1, 1);
                        gridModal.add(hPosi, 0, 1, 1, 1);
                        gridModal.add(cb, 1, 1, 1, 1);
                        gridModal.add(addStaffBtn, 0, 2, 3, 1);

                        addStaffBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
//                                ManagerStaffView ms = new ManagerStaffView();

                                try {
                                    Text addedName = new Text();
                                    Text test = new Text("Test");
                                    addedName.setText(nameField.getText());
                                    Text id = new Text();
                                    id.setText(controller.getSaltString());
                                    Text password = new Text();
                                    password.setText(controller.getSaltString());

                                    addPerson(addedName, id, password, grid, index);
                                    controller.saveEmployeeInTeam(addedName.getText(), (Position)cb.getValue());
                                    winStage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        Scene winScene = new Scene(gridModal, 400, 400);
                        winStage.setScene(winScene);
                        winStage.show();


                        //msa.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            //sets styles
            //hName.setFont(Font.font(22));

            // sets alignment
            vboxContainer.setAlignment(Pos.TOP_CENTER);
            grid.setAlignment(Pos.TOP_CENTER);

            // add navi
            addNavigationBar(vboxContainer);

            // sets components
            pane.setTop(vboxContainer);
            pane.setCenter(grid);

            // creates scene
            Scene scene = new Scene(pane, 1280, 800);

            //Import general css file
            scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());

            pane.setId("staff");

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


    public void addPerson(Text name, Text id, Text pw, GridPane grid, int index) {
        grid.add(name, 0, index, 1, 1);
        grid.add(id, 1, index, 1, 1);
        grid.add(pw, 2, index, 1, 1);
        plusIndex();
    }

    public int plusIndex() {
        return index++;
    }

}
