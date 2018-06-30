package Operations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import GUI.DrawingComponent;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.UserInterfaceSettings;

public class Autosave {

	public Autosave(DataLibrary dl, DrawingComponent dc, LayerLibrary ll, UserInterfaceSettings uis) {
		if (dl.getAutoSave()) {
			Timer t = new Timer(uis.getAutoSaveTimer(), new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					new Project().saveProject(ll, dl);
				}
			});
		}
	}
	
}
