package Libraries;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserInterfaceSettings implements Serializable {

	//TAB SETTINGS
	private Boolean tabs = false;
	
	//SETTER TABSETTINGS
	public void setTabSettings(Boolean tabs) { this.tabs = tabs; }
	//GETTER TABSETTINGS
	public Boolean useTabs() { return tabs; }
	
	//STATUSBAR
	private Boolean statusBar = false;
	private int toolTimer = 500;
	private int coordTimer = 100;
	
	//SETTERS STATUSBAR
	public void setStatus(Boolean status) { this.statusBar = status; }
	public void setToolTimer(int toolTimer) { this.toolTimer = toolTimer; }
	public void setCoordTimer(int coordTimer) { this.coordTimer = coordTimer; }
	//GETTERS STATUSBAR
	public Boolean getStatus() { return statusBar; }
	public int getToolTimer() { return toolTimer; }
	public int getCoordTimer() { return coordTimer; }
	
	//AUTOSAVE
	private Boolean autoSave = false;
	private int autoSaveTimer = 10000;
	
	//SETTERS AUTOSAVE
	public void setAutoSave(Boolean autoSave) { this.autoSave = autoSave; }
	public void setAutoSaveTimer(int autoSaveTimer) { this.autoSaveTimer = autoSaveTimer; }
	//GETTERS AUTOSAVE
	public Boolean getAutoSave() { return autoSave; }
	public int getAutoSaveTimer() { return autoSaveTimer; }
	
	//SPLASH SCREEN
	private Boolean splash = true;
	private int splashTime = 1500;
	
	//SETTERS SPLASH
	public void setSplash(Boolean splash) { this.splash = splash; }
	public void setSplashTime(int splashTime) { this.splashTime = splashTime; }
	//GETTERS SPLASH
	public Boolean getSplash() { return splash; }
	public int getSplashTime() { return splashTime; }
	
	//TOOLBOX 
	private Boolean toolBox = false;
	
	//SETTERS TOOLBOX
	public void setToolBox(Boolean toolBox) { this.toolBox = toolBox; }
	//GETTERS TOOLBOX
	public Boolean getToolBox() { return toolBox; }
}
