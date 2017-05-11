package controller;

import java.util.ArrayList;

import model.Manager;
import model.Position;
import model.User;

public class Controller {
	
	private static User loggedUser;

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		if(getLoggedUser() == null){
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
