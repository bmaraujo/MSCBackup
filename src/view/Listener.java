package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import business.MSCBackupManager;

public class Listener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			System.out.println(((JButton) e.getSource()).getName());
			if(isAction(e, Actions.DOBACKUP)) {
				MSCBackupManager manager = MSCBackupManager.getInstance();
				
				manager.executeBackup(((JButton) e.getSource()));
			}
			else if(isAction(e, Actions.DOFIX)) {
				
				MSCBackupManager manager = MSCBackupManager.getInstance();
				
				manager.fixCarBody();
				
			}
			else if(isAction(e, Actions.DORESTORE)) {
				MSCBackupManager manager = MSCBackupManager.getInstance();
				
				manager.restoreBackup();
			}
		} 
	}
	
	private boolean isAction(ActionEvent e, Actions action) {
		return ((JButton) e.getSource()).getName().equals(action.getAction());
	}

}
