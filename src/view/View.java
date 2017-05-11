package view;

import controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public abstract class View extends Application{
	
	public void addNavigationBar(Pane root, Stage stage){
		
		Button logoMenu = new Button();
		Image logoImage = new Image("file:resources/images/logo.png");
		ImageView imageView = new ImageView(logoImage);
		logoMenu.setGraphic(imageView);
		logoMenu.disableProperty().set(true);
		logoMenu.setStyle("-fx-opacity: 100;");
		
		Image userImage = new Image("file:resources/images/icon_user.png");
		ImageView userView = new ImageView(userImage);
		
		
		Button createShift = new Button("CREATE SHIFT");
		createShift.setAlignment(Pos.CENTER);
		createShift.setMinHeight(50);
		createShift.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CreateShiftSetStartDayView startDay = new CreateShiftSetStartDayView();
                startDay.start(stage);
			}
			
		});
		
		Button staff = new Button("STAFF");
		staff.setMinHeight(50);
		staff.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ManagerStaffView staffView = new ManagerStaffView();
				staffView.start(stage);
			}
		});
		
		Button user = new Button();
		user.setGraphic(userView);
		user.setText(Controller.getLoggedUser()!=null ? Controller.getLoggedUser().getName() : "No user");
		user.setId("logged-user");

		
		Pane spacer = new Pane();
	    spacer.setMinSize(1, 1);
	    HBox.setHgrow(spacer, Priority.ALWAYS);
	    
		HBox menuBar = new HBox();		
		menuBar.setId("menu-nav-bar");		
				
		menuBar.getChildren().addAll(logoMenu, createShift, staff, spacer, user);

		root.getChildren().add(menuBar);
	}

}
