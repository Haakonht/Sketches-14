package Libraries;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import Shapes.Shapes;

public class DataLibrary {

	//MAIN WINDOW TITLE
	private String windowTitle = "Sketches-14 RC2";
	
	//TAB INDEX
	private int activeTab = 0;
	
	//REDO ARRAY
	private ArrayList<Shapes> redo = new ArrayList<Shapes>();
	
	//BOOLEANS
	private Boolean selected = false;
	private Boolean isSaved = false;
	
	//FILE & FILE PATH
	private String filePath;
	private File file;
	
	//TOOLSELECTOR
	private int toolSelector = 1;
	
	//LAYER
	private int selectedLayer = 0;
	
	//STROKE
	private int thickness = 2;
	
	//COLOR SETTINGS
	private Color stroke = Color.BLACK, fill = Color.WHITE;
	private float alpha = 1.0f;
	
	//REDO OPERATIONS
	public void addRedo(Shapes shape) { redo.add(shape); }
	public Shapes getRedo() { Shapes redoShape = redo.get(redo.size()-1); redo.remove(redoShape); return redoShape; }
	public ArrayList<Shapes> getRedoArray() { return redo; }
	
	//AUTOSAVE
	public Boolean autosave;
	
	//SETTERS
	public void setSelector(int selector) { toolSelector = selector; }
	public void setThickness(int thickness) { this.thickness = thickness; }
	public void setStroke(Color stroke) {this.stroke = stroke; }
	public void setFill(Color fill) { this.fill = fill; }
	public void setPath(String filePath) { this.filePath = filePath; }
	public void setSelected(Boolean selected) { this.selected = selected; }
	public void setLayer(int layer) { this.selectedLayer = layer; }
	public void setTitle(String title) { this.windowTitle = title; }
	public void setAlpha(float alpha) { this.alpha = alpha; }
	public void setSaved(Boolean saved) { this.isSaved = saved; }
	public void setFile(File file) { this.file = file; }
	public void setAutoSave(Boolean autosave) { this.autosave = autosave; }
	public void setActiveTab(int activeTab) { this.activeTab = activeTab; } 
	
	//GETTERS
	public int getSelector() { return toolSelector; }
	public int getThickness() { return thickness; }
	public Color getStroke() { return stroke; }
	public Color getFill() { return fill; }
	public String getPath() { return filePath; }
	public Boolean getSelected() { return selected; }
	public int getLayer() { return selectedLayer; }
	public String getTitle() { return windowTitle; }
	public float getAlpha() { return alpha; }
	public Boolean getSaved() { return isSaved; }
	public File getFile() { return file; }
	public Boolean getAutoSave() { return autosave; }
	public int getActiveTab() { return activeTab; }
	
}
