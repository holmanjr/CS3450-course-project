package cs3450.cashregister.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ProdDriver {
	private final String DBURL = "jdbc:mysql://localhost:3306/";
	private final String USER = "root";
	private final String PASS = "root";
	private final String DBNAME = "register222";
	private String TNAME;
	private final String WARNSUP = "?useSSL=false";
	private Connection conn = null;
	private Statement stmt = null;
	private int colCount;
	private Vector<String> headers;
	private Vector<Vector<Object>> tableData;
	
	public ProdDriver(String tName){
		TNAME = tName;
		try{
			conn = DriverManager.getConnection(DBURL + WARNSUP, USER, PASS);
			createDB(DBNAME);
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
	
	public void createTable() throws SQLException{
		
		ResultSet tables = conn.getMetaData().getTables(null, null, TNAME, null);
		
		if (tables.next()) {
		  // Table exists
		} 	
		else {
		  // Table does not exist
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			
			String sql = "CREATE TABLE " + TNAME +
					"(prodid INTEGER not NULL AUTO_INCREMENT," + 
					"name VARCHAR(30) not NULL," +
					"price DECIMAL(10, 2) not null," + 
					"qty INTEGER not null," + 
					"supplier VARCHAR(60) not null," +
					"contactInfo VARCHAR(60) not null," +
					"PRIMARY KEY ( prodid ))";
			
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
		}
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
	
	public ResultSet selectRow(String id) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		stmt = conn.createStatement();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM " + TNAME +
				" WHERE prodid = ?"); 
		pst.setString(1, id);
		ResultSet rs = pst.executeQuery();
		colCount = rs.getMetaData().getColumnCount();
		if(rs.next()){
			Vector<Object> row = new Vector<Object>();
			for(int i = 1; i <= colCount; i++){
				row.add(rs.getObject(i));
			}
			data.add(row);
		}
		return rs;
	}
	
	public ResultSet searchByName(String name) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		stmt = conn.createStatement();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM " + TNAME +
				" WHERE name = ?"); 
		pst.setString(1, name);
		ResultSet rs = pst.executeQuery();
		colCount = rs.getMetaData().getColumnCount();
		if(rs.next()){
			Vector<Object> row = new Vector<Object>();
			for(int i = 1; i <= colCount; i++){
				row.add(rs.getObject(i));
			}
			data.add(row);
		}
		return rs;
	}
	
	public void addRow(String name, String price, String qty, String supplier, String contactInfo) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		stmt = conn.createStatement();
		String sql = "INSERT INTO " + TNAME + 
				"(name, price, qty, supplier, contactInfo)" +
				"VALUES ('" + name + "', " + price + ", " + qty + ", '" + supplier + "', '" + contactInfo + "');";
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
			System.out.println("adding row to table failed...");
			se.printStackTrace();
		}
	}
	
	public void updateRow(Integer id, String name, Double price, Integer qty, String supplier, String contactInfo) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		PreparedStatement pst = conn.prepareStatement
				("UPDATE " + TNAME + " SET name=?, price=?, qty=?, supplier=?, contactInfo=? WHERE prodid=?");
		pst.setString(1, name);
		pst.setDouble(2, price);	
		pst.setInt(3, qty);
		pst.setString(4, supplier);
		pst.setString(5, contactInfo);
		pst.setInt(6, id);
		
		pst.executeUpdate();
	}
	
	public void deleteProduct(Integer id) throws SQLException{
		conn = DriverManager.getConnection(DBURL + DBNAME + WARNSUP, USER, PASS);
		PreparedStatement pst = conn.prepareStatement
				("DELETE FROM " + TNAME + " WHERE prodid=?");
		pst.setInt(1, id);
		
		pst.executeUpdate();
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