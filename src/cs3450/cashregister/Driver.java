package cs3450.cashregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Driver {
	static final String DBURL = "jdbc:mysql://localhost:3306/";
	static final String USER = "root";
	static final String PASS = "root";
	static final String DBNAME = "register";
	static final String TNAME = "products";
	static Connection conn = null;
	static Statement stmt = null;
	private int colCount;
	private Vector<String> headers;
	private Vector<String[]> tableData;
	
	public Driver(){
		try{
			conn = DriverManager.getConnection(DBURL, USER, PASS);
			createDB(DBNAME);
			conn = DriverManager.getConnection(DBURL + DBNAME, USER, PASS);
			createTable(TNAME);
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
	
	public void createDB(String dbName){
		if(!checkDBExists(dbName)){
			System.out.println("Creating database...");
			try {
				stmt = conn.createStatement();
				String sql = "CREATE DATABASE " + dbName;
				stmt.executeUpdate(sql);
				System.out.println("Database created successfully...");	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void createTable(String tName) throws SQLException{
		ResultSet tables = conn.getMetaData().getTables(null, null, tName, null);
		if (tables.next()) {
		  // Table exists
		} 	
		else {
		  // Table does not exist
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			
			String sql = "CREATE TABLE " + tName +
					"(prodid INTEGER not NULL," + 
					"name VARCHAR(30) not NULL," +
					"price DECIMAL(10, 2) not null," + 
					"qty INTEGER not null," + 
					"PRIMARY KEY ( prodid ))";
			
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
		}
	}
	
	public Vector<String> getColNames(String tName) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME, USER, PASS);
		stmt = conn.createStatement();
		String sql = "SELECT * FROM " + tName;
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
	
	public Vector<String[]> getTableData(String tName) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME, USER, PASS);
		stmt = conn.createStatement();
		tableData = new Vector<String[]>();
		String sql = "SELECT * FROM " + tName;
		ResultSet rs = stmt.executeQuery(sql);
		colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
			String[] record = new String[colCount];
			for(int i = 0; i < colCount; i++){
				record[i] = rs.getString(i+1);
			}
			tableData.addElement(record);
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