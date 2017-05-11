/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;

import com.sun.org.apache.xpath.internal.operations.String;
import controller.ManagerStaffController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    int index = 3;

    @Override
    public void start(Stage stage) {
        try {
            //creates root elements
            BorderPane pane = new BorderPane();
            VBox vboxContainer = new VBox();
            GridPane grid = new GridPane();
            grid.getStyleClass().add("grid");
            grid.setHgap(20);
            grid.setVgap(20);
            grid.getColumnConstraints().add(new ColumnConstraints(250)); // column 1
            grid.getColumnConstraints().add(new ColumnConstraints(250)); // column 2
            grid.getColumnConstraints().add(new ColumnConstraints(250)); // column 3
            grid.setPadding(new Insets(50, 0, 0, 0));



            //creates components
            Label hName = new Label("NAME");
            Label hID = new Label("ID");
            Label hPassword = new Label("PASSWORD");

            Image iconPlus = new Image("file:resources/images/button_plus.png");
            ImageView iconPlusView = new ImageView(iconPlus);
            Button addStaffBtn = new Button("Add Staff", iconPlusView);
            addStaffBtn.getStyleClass().add("addStaffBtn");

            Separator border = new Separator();

            //table layout
            grid.add(addStaffBtn, 0, 0, 3, 1);
            grid.add(border, 0, 1, 3, 1);
            grid.add(hName, 0, 2, 1, 1);
            grid.add(hID, 1, 2, 1, 1);
            grid.add(hPassword, 2, 2, 1, 1);



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
                        VBox vbModal = new VBox();

                        GridPane gridModal = new GridPane();
                        gridModal.setHgap(20);
                        gridModal.setVgap(10);
//                        gridModal.getColumnConstraints().add(new ColumnConstraints(100)); // column 1
//                        gridModal.getColumnConstraints().add(new ColumnConstraints(100)); // column 2
                        gridModal.setPadding(new Insets(50, 50, 50, 50));
//                        gridModal.setMinWidth(500);


                        //creates components
                        Text modTitle = new Text("ADD STAFF");
                        modTitle.setFont(new Font(20));

                        Text hName = new Text("NAME");
                        Text hPosi = new Text("POSITION");
                        TextField nameField = new TextField();
//                        Position position = new Position();
                        Separator border2 = new Separator();

                        ChoiceBox cb = new ChoiceBox(FXCollections.observableList(controller.getAllPositions()));
                        cb.getStyleClass().add("choiceBox");

                        Button addBtn = new Button("Add Staff");
                        addBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    Text addedName = new Text();
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

                        Button cancelBtn = new Button("Cancel");
                        cancelBtn.getStyleClass().add("subButton");

                        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
//                                ManagerStaffView ms = new ManagerStaffView();

                                try {
                                    winStage.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        //table layout
                        gridModal.add(hName, 0, 0, 1, 1);
                        gridModal.add(nameField, 1, 0, 3, 1);
                        gridModal.add(hPosi, 0, 1, 1, 1);
                        gridModal.add(cb, 1, 1, 3, 1);
                        gridModal.add(border2, 0, 2, 4, 1);
                        gridModal.add(cancelBtn, 2, 3, 1, 1);
                        gridModal.add(addBtn, 3, 3, 1, 1);


                        VBox.setMargin(modTitle, new Insets(50, 0, 0, 0));
                        vbModal.setAlignment(Pos.TOP_CENTER);


                        vbModal.getChildren().addAll(modTitle, gridModal);

                        Scene winScene = new Scene(vbModal);

                        //Import general css file
                        winScene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());

                        GridPane.setMargin(border2, new Insets(20, 0, 10, 0));


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
            addNavigationBar(vboxContainer, stage);

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
