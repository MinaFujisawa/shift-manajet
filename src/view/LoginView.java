package view;

import java.net.URL;

import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView extends View{
	
	private LoginController controller = new LoginController();
	
	@Override
	public void start(Stage stage) {
		try {
			
			//Creating grid that will be the root element in the page
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			
			//Create scene (window) and adding the grid that is the root element
			Scene scene = new Scene(grid,300,275);
			//Import general css file
			scene.getStylesheets().add(new URL("file:resources/css/application.css").toExternalForm());
			
			
			//Create username input field
			TextField username = new TextField();
			//add input password to the grid(root) on the column/line index
			grid.add(username, 1, 1); 
			
			//Create label to username input
			Label labelUsername = new Label();
			//Set label text
			labelUsername.setText("User:");
			//Set label for input username
			labelUsername.setLabelFor(username);
			//add label to the grid(root) on the column/line index
			grid.add(labelUsername, 0, 1);					
			
			
			//Create password input field
			PasswordField password = new PasswordField();
			//add input password to the grid(root) on the column/line index
			grid.add(password, 1, 2); 
			
			//Create label to password input
			Label labelPassword = new Label();
			//Set label text
			labelPassword.setText("Password:");
			//Set label to password input
			labelPassword.setLabelFor(password);
			
			//add label to the grid(root) on the column/line index
			grid.add(labelPassword, 0, 2); 
			
			//Create a text element to display messages to the user
			Text loginMessage = new Text();
			//add to the grid(root) on the column/line index
			grid.add(loginMessage, 0, 5); 
			//Set colspan to the text element
			GridPane.setColumnSpan(loginMessage, 2);
			
			//Create login button
			Button btnLogin = new Button("Login");
			//Set action method that will be called when the button is clicked
			btnLogin.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {//implements the method that will be called
					//before start process clean previous messages
					loginMessage.setText("");
					//call method login on controller and check if login is true or false
					if(controller.login(username.getText(), password.getText())){
						System.out.println("Login sucessfull!");
					}else{
						//Login false, set message on text component
						loginMessage.setText("Invalid email/password.");
						//Set message color to red
						loginMessage.setFill(Color.RED);
					}
				}
			});
			
			//Create button box
			HBox hbBtn = new HBox(10);
			//Set box alignment on the grid
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			//Add login button to the button box
			hbBtn.getChildren().add(btnLogin);
			//Add button box to the grid(root) element on the column/line index
			grid.add(hbBtn, 1, 4);
			
			
			//Always use the lines below at the very end of start method
			//Add scene to the stage
			stage.setScene(scene);
			//Show stage
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
