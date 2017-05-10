/**
 * Created by MinaFujisawa on 2017/05/04.
 */
package view;
import com.sun.org.apache.xpath.internal.operations.String;
import controller.ManagerStaffController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @Override
    public void start(Stage stage) {
        try {
            //creates root elements
            BorderPane pane = new BorderPane();
            VBox vboxContainer = new VBox();
            GridPane grid = new GridPane();


            Text hName = new Text("NAME");
            Text hID = new Text("ID");
            Text hPassword = new Text("PASSWORD");

            Button addStaffBtn = new Button("Add Staff");

            //table layout
            grid.add( addStaffBtn , 0 , 0 , 4 , 1 );
            grid.add( hName , 0 , 1 , 1 , 1 );
            grid.add( hID , 1 , 1 , 1 , 1 );
            grid.add( hPassword , 2 , 1 , 1 , 1 );

            int index = 2;
            addPerson(name, grid, index);
            //grid.add( name , 0 , index , 1 , 1 );
//            grid.add( name2 , 0 , 3 , 1 , 1 );



            addStaffBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ManagerStaffAddView msa = new ManagerStaffAddView();

                    try {
                        // 新しいウインドウを生成
                        Stage newStage = new Stage();
                        // モーダルウインドウに設定
                        newStage.initModality(Modality.APPLICATION_MODAL);

                        newStage.initStyle(StageStyle.UNDECORATED);
                        newStage.setTitle("ABC");


//                        newStage.setScene(newScene);

                        // 新しいウインドウ内に配置するコンテンツを生成
                        HBox hbox = new HBox();
                        Label label = new Label("登録画面");
                        label.setFont(new Font(20d));
                        hbox.getChildren().add(label);

                        newStage.setScene(new Scene(hbox));

                        // 新しいウインドウを表示
                        newStage.show();


                        msa.start(stage);
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
        } {
        }
    }
    Text name = new Text();
    public void setName(Text name) {
        this.name = name;
    }
//    public void plusIndex(){
//        index++;
//    }
    public void addPerson(Text name, GridPane grid, int index) {
        Text id = new Text();
        id.setText(controller.getSaltString());
        Text password = new Text();
        password.setText(controller.getSaltString());
        grid.add( name , 0 , index , 1 , 1 );
        grid.add( id , 1 , index , 1 , 1 );
        grid.add( password , 2 , index , 1 , 1 );
        index++;
    }

//    Scene newScene;
//    public void setScene(Scene Scene){
//        newScene = Scene;
//    }


}
