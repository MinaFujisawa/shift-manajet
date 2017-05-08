package controller;

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
	
}
