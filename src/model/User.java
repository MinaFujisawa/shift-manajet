package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;
import utils.UserType;

public class User {
	
	private Long id;
	private String email;
	private String password;
	private String name;
	private UserType type;
	
	public User(){
		
	}
	
	public User(Long userId){
		try{
			ResultSet rs = DatabaseConnection.executeQuery("select * from user where id ="+userId);
			if(rs.next()){
				populateUser(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public void login(String email, String password){
		ResultSet rs = DatabaseConnection.executeQuery("select * from user where email = '"+email 
				+"' and password = '" + password + "'");
		try{
			if(rs.next()){
				populateUser(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void populateUser(ResultSet rs) throws SQLException{
		this.id = rs.getLong("id");
		this.email = rs.getString("email");
		this.password = rs.getString("password");
		this.name = rs.getString("name");
		this.type = UserType.getEnum(rs.getString("type"));
	}

}
