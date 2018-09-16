package view;

public enum Actions {
	DOBACKUP("doBackup"),
	DOFIX("doFix"),
	DORESTORE("dorestore"),
	MSCPATH("mscPath"),
	BACKUPPATH("backupPath");
	
	
	Actions(String action){
		this._action = action;
	}
	
	String _action;
	
	public String getAction() {
		return _action;
	}
}
