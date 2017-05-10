/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;


public class ManagerStaffAddView extends View {

    //private ManagerStaffViewController controller = new ManagerStaffViewController();

    @Override
    public void start(Stage newStage) {
        try {
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
            grid.add( hName , 0 , 0 , 1 , 1 );
            grid.add( nameField , 1 , 0 , 1 , 1 );
            grid.add( hPosi , 0 , 1 , 1 , 1 );
            grid.add( cb , 1 , 1 , 1 , 1 );
            grid.add( addStaffBtn , 0 , 2 , 3 , 1 );



            addStaffBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ManagerStaffView ms = new  ManagerStaffView();
                    Text name = new Text();
                    name.setText(nameField.getText());
                    Object position = cb.getSelectionModel().getSelectedItem();
                    try {
                        ms.setName(name);
                        //ms.plusIndex();
                        System.out.println("position :" + position);
                        newStage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //sets styles
            //hName.setFont(Font.font(22));


            // sets components
//            pane.setTop(vboxContainer);
//            pane.setCenter(grid);



            // creates scene
            Scene scene = new Scene(grid, 400, 400);

            //Import general css file
            scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());



            //Add scene to the stage
            newStage.setScene(scene);
            //Show stage
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } {
        }
    }


}
