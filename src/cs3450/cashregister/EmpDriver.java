package cs3450.cashregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class EmpDriver {
	
	private final String DBURL = "jdbc:mysql://localhost:3306/";
	private final String USER = "root";
	private final String PASS = "root";
	private final String DBNAME = "register";
	private String TNAME = "employees";
	private final String WARNSUP = "?useSSL=false";
	private Connection conn = null;
	private Statement stmt = null;
	private int colCount;
	private Vector<String> headers;
	private Vector<Vector<Object>> tableData;
	
	public EmpDriver(){
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
					"(empid INTEGER not NULL AUTO_INCREMENT," + 
					"username VARCHAR(30) not NULL," +
					"code VARCHAR(30) not null," + 
					"imageURL VARCHAR(100) not null," + 
					"status BOOLEAN not null," +
					"PRIMARY KEY ( empid ))";
			
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
	
	public void addEmployee(String username, String code, String imageURL, String status) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		stmt = conn.createStatement();
		boolean position = false;
		if(status.equals("Manager") || status.equals("manager")){
			position = true;
		}
		String sql = "INSERT INTO " + TNAME + 
				"(username, code, imageURL, status)" +
				"VALUES ('" + username + "', '" + code + "', '" + imageURL + "', " + position + ");";
		stmt.executeUpdate(sql);
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
	
	public Vector<Vector<Object>> getTableData() throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		stmt = conn.createStatement();
		tableData = new Vector<Vector<Object>>();
		String sql = "SELECT * FROM " + TNAME;
		ResultSet rs = stmt.executeQuery(sql);
		colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
			Vector<Object> record = new Vector<Object>();
			for(int i = 1; i <= colCount; i++){
				record.add(rs.getObject(i));
			}
			tableData.add(record);
		}
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
		return tableData;
	}
	
	public Vector<String> getColNames() throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		stmt = conn.createStatement();
		String sql = "SELECT * FROM " + TNAME;
		ResultSet rs = stmt.executeQuery(sql);
		colCount = rs.getMetaData().getColumnCount();
		headers = new Vector<String>();
		for(int h = 1; h <= colCount; h++){
			headers.addElement(rs.getMetaData().getColumnName(h));
		}
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
		return headers;
	}

}
