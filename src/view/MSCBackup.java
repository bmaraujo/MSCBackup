package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.MSCProperties;

public class MSCBackup {
	
	static Properties props;
	
	static final Integer MAX_LOG_LINES = 10;
	
	static JTextArea logTxt = new JTextArea(MAX_LOG_LINES,20);
	
	static Integer logLines =0;
		
	public static void main(String[] args) {
				
		JFrame frame = new JFrame("My Summer Car Backup");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = frame.getContentPane();
		
		JPanel logPanel = createLogPanel();
		
		container.add(logPanel, BorderLayout.SOUTH);
		
		try {
			props = MSCProperties.getProp();
		} catch (IOException e) {
			log("Failed to load properties file");
			log(e.getMessage());
		}
		
		JPanel textFieldPanel = createTextFieldPanel();
		
		container.add(textFieldPanel, BorderLayout.NORTH);
		
		JPanel panel2 = createButtonPanel();
		
		container.add(panel2, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(logTxt);
		
		container.add(scrollPane,BorderLayout.SOUTH);
		
		
//		JFileChooser fileChooser = new JFileChooser();
//		
//		container.add(fileChooser, BorderLayout.NORTH);
		
		frame.pack();
		
		frame.setVisible(true);

	}

	private static JPanel createLogPanel() {
		JPanel logPanel = new JPanel();
		
		Dimension logPanelSize = new Dimension(400, 100);
		
		logTxt.setPreferredSize(logPanelSize);
		logTxt.setEnabled(false);
		logTxt.setEditable(false);
		
		logPanel.add(logTxt);
		return logPanel;
	}

	private static JPanel createButtonPanel() {
		JPanel panel = new JPanel();
				
		JButton btnBackup = new JButton("Backup Files"); 
		btnBackup.setName(Actions.DOBACKUP.getAction());
		btnBackup.addActionListener(new Listener());
		JButton btnFixBody = new JButton("Fix Car Body");
		btnFixBody.setName(Actions.DOFIX.getAction());
		btnFixBody.addActionListener(new Listener());
		
		JButton btnRestore = new JButton("Restore Backup");
		btnRestore.setName(Actions.DORESTORE.getAction());
		btnRestore.addActionListener(new Listener());
		
		panel.add(btnBackup);
		panel.add(btnRestore);
		panel.add(btnFixBody);
		
		return panel;
	}

	private static JPanel createTextFieldPanel() {
		JPanel textFieldPanel = new JPanel(new GridLayout(2, 1));
		
		//MSC Path
		JPanel mscPanel = new JPanel();
		mscPanel.setSize(400, 100);
		JLabel lblPath = new JLabel("MSC Path:");
		
		JTextField txtSize = new JTextField();
		
		Dimension txtMscSize = new Dimension(200, 20);
		
		txtSize.setMinimumSize(txtMscSize);
		txtSize.setPreferredSize(txtMscSize);
		
		JButton btnMSCPath = new JButton("...");
		btnMSCPath.setName(Actions.MSCPATH.getAction());
		btnMSCPath.addActionListener(new Listener());
				
		mscPanel.add(lblPath);
		mscPanel.add(txtSize);
		mscPanel.add(btnMSCPath);
		
		textFieldPanel.add(mscPanel);
		
		//Backup Path
		JPanel backupPanel = new JPanel();
		JLabel lblBackup = new JLabel("Backup Location:");
		
		JTextField txtBackupPath = new JTextField();
		txtBackupPath.setPreferredSize(txtMscSize);
		
		JButton btnBackup = new JButton("...");
		btnBackup.setName(Actions.BACKUPPATH.getAction());
		btnBackup.addActionListener(new Listener());
		
		backupPanel.add(lblBackup);
		backupPanel.add(txtBackupPath);
		backupPanel.add(btnBackup);
		
		textFieldPanel.add(backupPanel);
		
		return textFieldPanel;
	}

	public static void log(String message) {
		if(logLines == MAX_LOG_LINES) {
			//remove the first line
			logTxt.setText(logTxt.getText().substring(logTxt.getText().indexOf("\n")+1));
			logLines--;
		}
		logTxt.setText(logTxt.getText().concat(message).concat("\n"));
		logLines++;
	}
}
