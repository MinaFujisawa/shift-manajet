package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;


public class Main extends Application{
	
	public static void main(String[] args) {
		DatabaseConnection.createConnection();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Shift Manajet");
		LoginView login = new LoginView();
		login.start(primaryStage);
	}
}
