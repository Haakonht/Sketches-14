package Shapes;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ShapesLibrary implements Serializable {

	private Boolean visible = true;
	private ArrayList<Shapes> shapes = new ArrayList<Shapes>();
	private ArrayList<Shapes> pencil = new ArrayList<Shapes>();
	
	//ADD(SETTER)
	public void addPencil(Shapes shape) { pencil.add(shape); }
	public void addShapes(Shapes shape) { shapes.add(shape); }
	public void setVisible(Boolean visible) { this.visible = visible; }
	
	//GETTER
	public ArrayList<Shapes> getPencil() { return pencil; }
	public ArrayList<Shapes> getShapes() { return shapes; }
	public Boolean getVisible() { return visible; }
	public Shapes getSelectedShapes() {
		Shapes r = null;
		for (Shapes s : shapes) {
			if (s.getSelected()) {
				r = s;
			}
		}
		return r;
	}

	//OPERATIONS
	public Shapes removeLast() {
		Shapes redo = shapes.get(shapes.size()-1);
		shapes.remove(redo);
		return redo;
	}
	public void removeAll() {
		if (shapes.size() > 0) {
			shapes.removeAll(shapes);
		}
	}	
	public Shapes removeSelected() {
		Shapes redo = null;
		for (int i=shapes.size()-1; i>-1; i--) {
			Shapes s = shapes.get(i);
			if (s.getSelected()) {
				redo = shapes.get(i);
				shapes.remove(redo);
			}
		}
		return redo;
	}
	public void setZindex(Shapes selected, int operation) {
		Shapes zindexShape = selected;
		int index = shapes.indexOf(selected);
		shapes.remove(selected);
		try {
			shapes.add(index+operation, zindexShape);
		}
		catch (IndexOutOfBoundsException e) {
			shapes.add(index, zindexShape);
		}
	}
	public void erasePencil(Shapes s) {
		pencil.remove(s);
	}
	
}
