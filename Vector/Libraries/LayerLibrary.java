package Libraries;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class LayerLibrary implements Serializable {
	
	private String name = "Untitled";
	
	private Color background = Color.WHITE;
	
	private ArrayList<ShapesLibrary> layers = new ArrayList<ShapesLibrary>();
	
	public LayerLibrary(ShapesLibrary sl) {
		layers.add(sl);
	}
	
	public LayerLibrary(String name, ShapesLibrary sl) {
		layers.add(sl);
		this.name = name;
	}
	
	//ADDER(SETTERS)
	public void addLayer() { this.layers.add(new ShapesLibrary()); }
	public void setLayer(ShapesLibrary sl) { this.layers.add(sl); }
	public void setBackground(Color background) { this.background = background; }
	
	//GETTERS
	public ArrayList<ShapesLibrary> getLayers() { return layers; }
	public Color getBackground() { return background; }
	public String getName() { return name; }
	
	public void removeLayer(int i) {
		if (layers.size() > 1) {
			layers.remove(i);
		}
	}
	public void removeAll() {
		layers.removeAll(layers);
		layers.add(new ShapesLibrary());
	}

	public void wipe() {
		layers.removeAll(layers);
	}
	
	
}
