package Controllers;

import GUI.MainWindow;
import GUI.SplashScreen;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Libraries.UserInterfaceSettings;
import Operations.Settings;
import Shapes.ShapesLibrary;

public class MainController {
	
	private TabLibrary tl;
	private LayerLibrary ll;
	
	public MainController() {
		DataLibrary dl = new DataLibrary();
		UserInterfaceSettings uis = new Settings().deserializeSettings();
		if (uis.useTabs()) {
			TabLibrary tl = new TabLibrary();
			this.tl = tl;
		}
		else {
			LayerLibrary ll = new LayerLibrary(new ShapesLibrary());
			this.ll = ll;
		}
		if (uis.getSplash()) {
			SplashScreen ss = new SplashScreen();
			try {
				Thread.sleep(uis.getSplashTime());
			}
			catch (InterruptedException e) {
				System.out.println(e);
			}
			new MainWindow(uis, dl, ll, tl);
			ss.dispose();
		}
		else {
			new MainWindow(uis, dl, ll, tl);
		}
	}
	
}
