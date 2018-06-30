package Libraries;

import java.io.Serializable;
import java.util.ArrayList;

import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class TabLibrary implements Serializable {

	private ArrayList<LayerLibrary> layerLibraries;

	public TabLibrary() {
		layerLibraries = new ArrayList<LayerLibrary>();
		layerLibraries.add(new LayerLibrary(new ShapesLibrary()));
	}

	//SETTERS
	public void newTab() { layerLibraries.add(new LayerLibrary(new ShapesLibrary())); }
	public void addTab(LayerLibrary ll) { layerLibraries.add(ll); }
	
	//GETTERS
	public ArrayList<LayerLibrary> getTabs() { return layerLibraries; }
	
	public void wipe() {
		layerLibraries.removeAll(layerLibraries);
	}
	
}
