package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import Controllers.WindowClosing;
import CustomUIElements.ToolBox;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Libraries.UserInterfaceSettings;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	public MainWindow(UserInterfaceSettings uis, DataLibrary dl, LayerLibrary ll, TabLibrary tl) {
		setTitle(dl.getTitle());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(950, 800));
		if (uis.getToolBox()) {
			updateWidth(255);
		}
		setLocationRelativeTo(null);
		addWindowListener(new WindowClosing(uis, dl));
		
		if (uis.useTabs()) {
			TabComponent tc = new TabComponent(tl, dl);
			ToolBox tb = new ToolBox(tl, tc, dl, uis);
			add(tb, BorderLayout.WEST);
			add(new MenuBar(tc, tl, dl, uis, this, tb), BorderLayout.NORTH);
			add(tc, BorderLayout.CENTER);
		}
		else {
			DrawingComponent dc = new DrawingComponent(ll, dl);
			ToolBox tb = new ToolBox(ll, dc, dl, uis);
			add(tb, BorderLayout.WEST);
			add(new MenuBar(dc, ll, dl, uis, this, tb), BorderLayout.NORTH);
			add(dc, BorderLayout.CENTER);
		}
		if (uis.getStatus()) {
			add(new StatusBar(dl, ll, tl), BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void updateTitle(String title) {
		setTitle(title);
	}
	public void updateWidth(int width) {
		if (this.getExtendedState() != MAXIMIZED_BOTH) {
			setSize(this.getWidth()+width, this.getHeight());
		}
	}
	
}
