package controller;

import model.User;

public class LoginController extends Controller{
	public boolean login(String email, String password){
		User user = new User();
		user.login(email, password);
		if(user.getId() != null){
			setLoggedUser(user);
			return true;
		}
		return false;
	}
}
