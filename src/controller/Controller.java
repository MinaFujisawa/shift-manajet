package controller;

import model.User;

public class Controller {
	
	private static User loggedUser;

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		if(loggedUser == null){
			Controller.loggedUser = loggedUser;
		}
	}
	
}
