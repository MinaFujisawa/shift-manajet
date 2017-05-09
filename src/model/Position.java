package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;

public class Position {

    private Long id;
    private String name;

    public Position(){
    	
    }
    
    public Position(Long positionId) {
    	try {
    		ResultSet rs = DatabaseConnection.executeQuery("SELECT * from position WHERE id = "+positionId);
    		if(rs.next()){
				this.id = rs.getLong("id");
				this.name = rs.getString("name");
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
