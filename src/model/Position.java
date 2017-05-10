package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public void savePostion(){
    	
    	try {
    		ResultSet rs = DatabaseConnection.executeQuery("INSERT INTO position VALUES (NULL,"
    				+ this.getName());
				rs.insertRow();
    		if(rs.next()){
    			this.id = rs.getLong("id");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public ArrayList<Position> getAllPositions(){
    	ArrayList<Position> positionsList = new ArrayList<Position>();
    	try {
    		Position pos;
    		ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM position");
    		while(rs.next()){
    			pos = new Position();
    			pos.setId(rs.getLong("id"));
    			pos.setName(rs.getString("name"));
    			positionsList.add(pos);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return positionsList;
    }
}
