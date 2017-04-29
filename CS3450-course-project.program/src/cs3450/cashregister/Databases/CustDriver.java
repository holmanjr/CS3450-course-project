package cs3450.cashregister.Databases;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class CustDriver {
	private final String DBURL = "jdbc:mysql://localhost:3306/";
	private String USER;
	private String PASS;
	private String DBNAME;
	private String TNAME = "customer";
	private final String WARNSUP = "?useSSL=false";
	private Connection conn = null;
	private Statement stmt = null;
	
	public CustDriver(){
		try{
			Properties appSettings = new Properties();
			FileInputStream fis = new FileInputStream("database\\config.properties");
			appSettings.load(fis);
			
			DBNAME = (String)appSettings.get("database");
			USER = (String)appSettings.get("dbuser");
			PASS = (String)appSettings.get("dbpassword");
			fis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		
		try{
			conn = DriverManager.getConnection(DBURL + WARNSUP, USER, PASS);
			createDB();
			conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
			createTable();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
	public void createDB(){
		if(!checkDBExists(DBNAME)){
			System.out.println("Creating database...");
			try {
				stmt = conn.createStatement();
				String sql = "CREATE DATABASE " + DBNAME;
				stmt.executeUpdate(sql);
				System.out.println("Database created successfully...");	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void createTable() throws SQLException{
		ResultSet tables = conn.getMetaData().getTables(null, null, TNAME, null);
		if (tables.next()) {
		  // Table exists
		} 	
		else {
		  // Table does not exist
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS " + TNAME +
					"(custid INTEGER not NULL AUTO_INCREMENT," + 
					"name VARCHAR(30) not NULL," +
					"isValCust BOOLEAN not null," +
					"PRIMARY KEY ( custid ))";
			
			stmt.executeUpdate(sql);
			
			System.out.println("Created table in given database...");
		}
	}
	
	public boolean checkDBExists(String dbName){

	    try{
	        ResultSet resultSet = conn.getMetaData().getCatalogs();

	        while (resultSet.next()) {

	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(dbName)){
	                return true;
	            }
	        }
	        resultSet.close();

	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }

	    return false;
	}
}
