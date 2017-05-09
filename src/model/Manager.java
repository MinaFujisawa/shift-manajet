package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;

public class Manager extends User{
	
	public Manager(){
		
	}
	
	public Manager(Long managerId){
		try {
			ResultSet rs = DatabaseConnection.executeQuery("SELECT * from user WHERE id = "+managerId);
			if(rs.next()){
				populateUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Manager(User user){
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setType(user.getType());
	}
	
	
}
