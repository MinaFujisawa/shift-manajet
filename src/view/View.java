package view;

import javax.swing.plaf.InsetsUIResource;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public abstract class View extends Application{
	
	public void addNavigationBar(Pane root){
		
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
		
		Button staff = new Button("STAFF");
		staff.setMinHeight(50);
		
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
