package view;

import controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

public class LoginView extends Application{
	
	private LoginController controller = new LoginController();
	
	@Override
	public void start(Stage stage) {
		try {
//			BorderPane root = new BorderPane();
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			Scene scene = new Scene(grid,300,275);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			
			
			TextField username = new TextField();
			grid.add(username, 1, 1);
			Label labelUsername = new Label();
			labelUsername.setText("User:");
			labelUsername.setLabelFor(username);
			grid.add(labelUsername, 0, 1);					
			
		
			PasswordField password = new PasswordField();
			grid.add(password, 1, 2);
			Label labelPassword = new Label();
			labelPassword.setText("Password:");
			labelPassword.setLabelFor(password);
			grid.add(labelPassword, 0, 2);
			
			
			Text loginMessage = new Text();
			grid.add(loginMessage, 0, 5);
			GridPane.setColumnSpan(loginMessage, 2);
			
			Button btnLogin = new Button("Login");
			btnLogin.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					if(controller.login(username.getText(), password.getText())){
						System.out.println("Login sucessfull!");
					}else{
						loginMessage.setText("Invalid email/password.");
						loginMessage.setFill(Color.RED);
					}
				}
			});
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btnLogin);
			grid.add(hbBtn, 1, 4);

			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
