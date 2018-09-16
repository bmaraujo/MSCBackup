package business;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;

import org.apache.commons.io.FileUtils;

import util.MSCProperties;
import view.MSCBackup;

public class MSCBackupManager {
	
	private static MSCBackupManager instance;
	
	private  MSCBackupManager() {
	}
	
	public static MSCBackupManager getInstance() {
		if(instance == null) {
			instance = new MSCBackupManager();
		}
		
		return instance;
	}
	
	public void executeBackup(JButton jButton) {
		MSCBackup.log("Creating Backup...");
				
		try {
			Properties props = MSCProperties.getProp();
						
			copyFiles(props.getProperty("mscPath"), props.getProperty("backupPath"));
			
			MSCBackup.log("Backup finished.");
			
		} catch (IOException e) {
			MSCBackup.log("Failed to load properties files - " + e.getMessage());
		}
		
	}
	
	public void fixCarBody() {
		MSCBackup.log("fix car body");
	}
	
	public void restoreBackup() {
		MSCBackup.log("Restoring backup");
		
		try {
			Properties props = MSCProperties.getProp();
						
			copyFiles(props.getProperty("backupPath"), props.getProperty("mscPath"));
			
			MSCBackup.log("Backup restored.");
			
		} catch (IOException e) {
			MSCBackup.log("Failed to load properties files - " + e.getMessage());
		}
	}
	
	private void copyFiles(String source, String dest) {
		MSCBackup.log("Copying files from: " + source);
		MSCBackup.log("to: " + dest);
		
		try {
			FileUtils.copyDirectory(new File(source),new File(dest) );
		} catch (IOException e) {
			MSCBackup.log(e.getMessage());
		}
	}

}
