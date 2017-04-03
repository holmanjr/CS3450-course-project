package cs3450.cashregister.Databases;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public Employee(ResultSet rs) throws NumberFormatException, SQLException{
		this.id = rs.getInt("empid");
		this.username = rs.getString("username");
		this.code = rs.getString("code");
		this.imageURL = rs.getString("imageURL");
		this.status = rs.getBoolean("status");
		
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
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

}
