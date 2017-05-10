package controller;

import java.util.ArrayList;

import model.Manager;
import model.Position;
import model.User;

public class Controller {
	
	private static User loggedUser;

	public static User getLoggedUser() {
		User user = new User();
		user.login("gui", "123");
		return user;
		//TODO: delete above and uncomment below
//		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		if(loggedUser == null){
			Controller.loggedUser = loggedUser;
		}
	}
	
	public Manager getLoggedUserAsManager(){
		return new Manager(getLoggedUser());
	}	
	
	 public ArrayList<Position> getAllPositions(){
		 return new Position().getAllPositions();
	 }
	
}
