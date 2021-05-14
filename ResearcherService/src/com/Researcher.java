package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Researcher {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pafproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertResearcher(String name, String des, String date)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into research(`rid`,`rname`,`rDesp`,`rdate`)"
					 + " values (?, ?, ?, ?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, des);
			 preparedStmt.setString(4, date);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newResearcher = readResearcher(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Researcher.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	
	public String readResearcher()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Researcher Name</th><th>Payment Date</th><th>Amount</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from research";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String rid = Integer.toString(rs.getInt("rid"));
				 String rname = rs.getString("rname");
				 String rDesp = rs.getString("rDesp");
				 String rdate = rs.getString("rdate");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidResearchIDUpdate\' name=\'hidResearchIDUpdate\' type=\'hidden\' value=\'" + rid + "'>" 
							+ rname + "</td>"; 
				output += "<td>" + rDesp + "</td>";
				output += "<td>" + rdate + "</td>";

				  
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-researcherid='" + rid + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Researcher.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	public String updateResearcher(String ID, String name, String des, String date)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE research SET rname=?,rDesp=?,rdate=?" 
					   + "WHERE rid=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, name);
			 preparedStmt.setString(2, des);
			 preparedStmt.setString(3, date);
			 preparedStmt.setInt(4, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newResearcher = readResearcher();    
			output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Researcher.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteResearcher(String rid)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "delete from research where rid=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(rid)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newResearcher = readResearcher();    
			output = "{\"status\":\"success\", \"data\": \"" +  newResearcher + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Researcher.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
