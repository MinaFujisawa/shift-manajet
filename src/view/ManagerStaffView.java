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
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.stage.StageStyle;

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

            //creates components
            Text hName = new Text("NAME");
            Text hID = new Text("ID");
            Text hPassword = new Text("PASSWORD");

            Button addStaffBtn = new Button("Add Staff");

            //table layout
            grid.add(addStaffBtn, 0, 0, 4, 1);
            grid.add(hName, 0, 1, 1, 1);
            grid.add(hID, 1, 1, 1, 1);
            grid.add(hPassword, 2, 1, 1, 1);

            // method test
//            Text mina = new Text("Mina");
//            Text fumin = new Text("Fumin");
//            addPerson(mina, grid, index);
//            addPerson(fumin, grid, index);

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
                        GridPane grid = new GridPane();

                        //creates components
                        Text hName = new Text("NAME");
                        Text hPosi = new Text("POSITION");
                        Button addStaffBtn = new Button("Add Staff");
                        TextField nameField = new TextField();
                        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                                "First", "Second", "Third")
                        );

                        //table layout
                        grid.add(hName, 0, 0, 1, 1);
                        grid.add(nameField, 1, 0, 1, 1);
                        grid.add(hPosi, 0, 1, 1, 1);
                        grid.add(cb, 1, 1, 1, 1);
                        grid.add(addStaffBtn, 0, 2, 3, 1);

                        addStaffBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
//                                ManagerStaffView ms = new ManagerStaffView();

                                try {
                                    Text addedName = new Text();
                                    Text test = new Text("Test");
                                    addedName.setText(nameField.getText());
                                    Object position = cb.getSelectionModel().getSelectedItem();

                                    //SEEMS CANNOT ADD TO GRID FROM HERE....
                                    grid.add(test, 0, 3, 1, 1);
                                    addPerson(addedName, grid, index);
                                    winStage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        Scene winScene = new Scene(grid, 400, 400);
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
            grid.setAlignment(Pos.CENTER);

            // add navi
            addNavigationBar(vboxContainer);

            // sets components
            pane.setTop(vboxContainer);
            pane.setCenter(grid);

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
        }
        {
        }
    }


    public void addPerson(Text name, GridPane grid, int index) {
        Text id = new Text();
        id.setText(controller.getSaltString());
        Text password = new Text();
        password.setText(controller.getSaltString());
        grid.add(name, 0, index, 1, 1);
        grid.add(id, 1, index, 1, 1);
        grid.add(password, 2, index, 1, 1);
        plusIndex();
    }

    public int plusIndex() {
        return index++;
    }

}
