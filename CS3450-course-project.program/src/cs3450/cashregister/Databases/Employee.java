package cs3450.cashregister.Databases;

import java.sql.SQLException;
import java.util.Vector;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class Employee {
	private Integer id;
	private String username;
	private String code;
	private String imageURL;
	private boolean status;
	
	public Employee(){
		this.id = 100;
		this.username = "admin";
		this.code = "";
		this.imageURL = "";
		this.status = true;
	}
	
	public Employee(Vector<Object> row) throws NumberFormatException, SQLException{
		this.id = (Integer) row.get(0);
		this.username = (String) row.get(1);
		this.code = (String) row.get(2);
		this.imageURL = (String)row.get(3);
		this.status = (boolean)row.get(4);
		
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @return the status
	 */
	public boolean isManager() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

}
