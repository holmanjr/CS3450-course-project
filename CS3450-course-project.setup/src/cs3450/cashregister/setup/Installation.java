/**
 * 
 */
package cs3450.cashregister.setup;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class Installation {
	private String installationLocation;
	private String dbName;
	private String dbHostname;
	private String dbPassword;
	private String logName;
	private String logPass;
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

	public void setConfigVariables(String dbname, String hostname, String password) {
		dbName = dbname;
		dbHostname = hostname;
		dbPassword = password;
	}

	public void install() {
		try{
			File installationFolder = new File(installationLocation);
			
			if(!installationFolder.exists()){
				installationFolder.mkdirs();
				installationStatus.append("Installation folder created.\n");
			}
			
			File jarFile = new File("program.jar");
			File installedJarFile = new File(installationLocation + "\\program.jar");
			
			copyFile(jarFile, installedJarFile);
			
			installationStatus.append("Program files copied.\n");
			
			File installedDbFolder = new File(installationLocation + "\\database\\");
			installedDbFolder.mkdirs();
			File dbFile = new File("database\\config.properties");
			File installedDbFile = new File(installedDbFolder.getAbsolutePath() + "\\config.properties");
			
		    PrintWriter writer = new PrintWriter("database\\config.properties", "UTF-8");
		    writer.println("database=" + dbName);
		    writer.println("dbuser=" + dbHostname);
		    writer.println("dbpassword=" + dbPassword);
		    writer.println("loginuser=" + logName);
		    writer.println("loginpass=" + logPass);
		    writer.close();
		    
		    copyFile(dbFile, installedDbFile);

			installationStatus.append("Database setup.\n");
			
			installationStatus.append("Program has been successfully installed!\n");
			installedSuccessfully = true;
		} catch (Exception e){
			installationStatus.append("Installation failed!\n\n");
			installationStatus.append(e);
		}
	}

	private void copyFile(File sourceFile, File destFile) throws IOException {
		Files.copy(sourceFile.toPath(), destFile.toPath());
	}

	public String getInstallationSetting() {
		StringBuilder sb = new StringBuilder();
		sb.append("Installation location:\n");
		sb.append(installationLocation + "\n");
		sb.append('\n');
		sb.append("DB Name: " + dbName + '\n');
		sb.append("Hostname: " + dbHostname + '\n');
		sb.append("Password: ********\n");
		sb.append('\n');
		sb.append("Username: " + logName + '\n');
		sb.append("User Password: ********\n");
		return sb.toString();
	}

	public String getInstallationStatus() {
		return installationStatus.toString();
	}

	public boolean isSuccessful() {
		return installedSuccessfully;
	}

	public void setUserVariables(String username, String password) {
		logName = username;
		logPass = password;
	}

}
