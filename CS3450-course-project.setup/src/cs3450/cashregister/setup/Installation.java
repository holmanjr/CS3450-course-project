/**
 * 
 */
package cs3450.cashregister.setup;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class Installation {
	private String installationLocation;
	private String dbHostname;
	private String dbPassword;
	private StringBuilder installationStatus;
	private boolean installedSuccessfully;
	
	public Installation(){
		installationLocation = "C:\\CashRegister";
		dbHostname = "";
		dbPassword = "";
		installationStatus = new StringBuilder();
		installedSuccessfully = false;
	}

	public void setInstallationLocation(String path) {
		installationLocation = path;
	}

	public String getInstallationLocation() {
		return installationLocation;
	}

	public void setConfigVariables(String hostname, String password) {
		dbHostname = hostname;
		dbPassword = password;
	}

	public void install() {
		// TODO Auto-generated method stub
		
	}

	public String getInstallationSetting() {
		StringBuilder sb = new StringBuilder();
		sb.append("Installation location:\n");
		sb.append(installationLocation + "\n");
		sb.append('\n');
		sb.append("Hostname: " + dbHostname + '\n');
		sb.append("Password: *******\n");
		return sb.toString();
	}

}
