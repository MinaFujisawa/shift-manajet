package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;

public class User {
	
	private Long id;
	private String email;
	private String password;
	private String name;
	private String type;
	
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


	public String getType() {
		return type;
	}

	public void setType(String type) {
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
	
	private void populateUser(ResultSet rs) throws SQLException{
		this.id = rs.getLong("id");
		this.email = rs.getString("email");
		this.password = rs.getString("password");
		this.name = rs.getString("name");
		this.type = rs.getString("type");
	}

}
