package GUI;

import javax.swing.JTabbedPane;

import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;

@SuppressWarnings("serial")
public class TabComponent extends JTabbedPane {

	private DataLibrary dl;
	private TabLibrary tl;
	
	public TabComponent(TabLibrary tl, DataLibrary dl) {
		this.dl = dl;
		this.tl = tl;
		for (int i = 0; i < tl.getTabs().size(); i++) {
			LayerLibrary ll = tl.getTabs().get(i);
			addTab(ll.getName(), new DrawingComponent(ll, dl));
		}
		dl.setActiveTab(getSelectedIndex());
	}
	
	public void refresh() {
		removeAll();
		for (int i = 0; i < tl.getTabs().size(); i++) {
			LayerLibrary ll = tl.getTabs().get(i);
			addTab(ll.getName(), new DrawingComponent(ll, dl));
		}
		dl.setActiveTab(getSelectedIndex());
	}
	
}
