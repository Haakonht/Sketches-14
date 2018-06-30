package Controllers;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import Libraries.DataLibrary;
import Libraries.UserInterfaceSettings;
import Operations.Settings;

public class WindowClosing implements WindowListener {

	private UserInterfaceSettings uis;
	private DataLibrary dl;
	
	public WindowClosing(UserInterfaceSettings uis, DataLibrary dl) {
		this.uis = uis;
		this.dl = dl;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		if (dl.getSaved()) {
			Settings s = new Settings();
			s.serializeSettings(uis);
			System.exit(0);
		}
		else if (dl.getPath() == null) {
			int returnVal = JOptionPane.showConfirmDialog(null, "This project has not been saved, exit anyway?", " Unsaved project", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (returnVal == JOptionPane.OK_OPTION) {
				Settings s = new Settings();
				s.serializeSettings(uis);
				System.exit(0);
			}
		}
		else {
			int returnVal = JOptionPane.showConfirmDialog(null, "Recent changes have not been saved, exit anyway?", " Unsaved changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (returnVal == JOptionPane.OK_OPTION) {
				Settings s = new Settings();
				s.serializeSettings(uis);
				System.exit(0);
			}
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}

}
