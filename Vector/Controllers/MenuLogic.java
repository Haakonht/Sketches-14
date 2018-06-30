package Controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import CustomUIElements.ToolBox;
import Dialogs.LayerManager;
import Dialogs.OutlineManager;
import Dialogs.RotationManager;
import Dialogs.SettingsManager;
import Dialogs.ShapeManager;
import Dialogs.TabNamer;
import GUI.DrawingComponent;
import GUI.MainWindow;
import GUI.TabComponent;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Libraries.UserInterfaceSettings;
import Operations.Image;
import Operations.Project;
import Operations.Settings;
import Shapes.Shapes;
import Shapes.ShapesLibrary;

public class MenuLogic implements ActionListener {

	private UserInterfaceSettings uis;
	private DrawingComponent dc;
	private TabComponent tc;
	private LayerLibrary ll;
	private TabLibrary tl;
	private DataLibrary dl;
	private MainWindow mw;
	private ToolBox tb;
	
	public MenuLogic(DrawingComponent dc, LayerLibrary ll, DataLibrary dl, UserInterfaceSettings uis, MainWindow mw, ToolBox tb) {
		this.mw = mw;
		this.dc = dc;
		this.ll = ll;
		this.dl = dl;
		this.uis = uis;
		this.tb = tb;
	}
	
	public MenuLogic(TabComponent tc, TabLibrary tl, DataLibrary dl, UserInterfaceSettings uis, MainWindow mw, ToolBox tb) {
		this.mw = mw;
		this.tc = tc;
		this.tl = tl;
		this.dl = dl;
		this.uis = uis;
		this.tb = tb;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String arg = evt.getActionCommand();
		//FILE
		if (arg.equals("New") || arg.equals("New sketch")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(dl.getActiveTab());
				dl.setLayer(ll.getLayers().size()-1);
				ll.removeAll();
				mw.repaint();
			}
			else {
				dl.setLayer(ll.getLayers().size()-1);
				ll.removeAll();
				dc.repaint();
				dl.setTitle("Sketches-14 RC2");
				mw.updateTitle(dl.getTitle());
			}
		}
		else if (arg.equals("New project")) {
			tl = new TabLibrary();
			tc.refresh();
			mw.repaint();
		}
		else if (arg.equals("New tab")) {
			new TabNamer(tl, tc);
		}
		else if (arg.equals("Close tab")) {
			tc.remove(tc.getSelectedIndex());
		}
		else if (arg.equals("Open project")) {
			if (uis.useTabs()) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Tabbed project files (.tpf)", "tpf");
			    chooser.setFileFilter(filter);
			    chooser.setDialogTitle("Open tabbed project");
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
			    	dl.setPath(filePath);
			    	new Project().openTabbedProject(tl, dl);
			    	dl.setTitle("Sketches-14 RC2: "+chooser.getSelectedFile().getName());
			    	dl.setSaved(true);
			    	mw.updateTitle(dl.getTitle());
			    	tc.refresh();
			    	tc.repaint();
			    }
			}
			else {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Project files (.pro)", "pro");
			    chooser.setFileFilter(filter);
			    chooser.setDialogTitle("Open project");
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
			    	dl.setPath(filePath);
			    	new Project().openProject(ll, dl);
			    	dl.setTitle("Sketches-14 RC2: "+chooser.getSelectedFile().getName());
			    	dl.setSaved(true);
			    	mw.updateTitle(dl.getTitle());
			    	dc.repaint();
			    }
			}
		}
		else if (arg.equals("Save project")) {
			if (uis.useTabs()) {
				if (dl.getPath() == null) {
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Tabbed project files (.tpf)", "tpf");
				    chooser.setFileFilter(filter);
				    chooser.setApproveButtonText("Save");
				    chooser.setDialogTitle("Save tabbed project");
				    int returnVal = chooser.showOpenDialog(null);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
				    	dl.setPath(filePath);
				    	new Project().saveTabbedProject(tl, dl);
				    	dl.setTitle("Sketches-14 RC2: "+chooser.getSelectedFile().getName());
				    	dl.setSaved(true);
				    	mw.updateTitle(dl.getTitle());
				    }
				}
				else {
					new Project().saveTabbedProject(tl, dl);
					dl.setSaved(true);
				}
			}
			else {
				if (dl.getPath() == null) {
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Project files (.pro)", "pro");
				    chooser.setFileFilter(filter);
				    chooser.setApproveButtonText("Save");
				    chooser.setDialogTitle("Save project");
				    int returnVal = chooser.showOpenDialog(null);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
				    	dl.setPath(filePath);
				    	new Project().saveProject(ll, dl);
				    	dl.setTitle("Sketches-14 RC2: "+chooser.getSelectedFile().getName());
				    	dl.setSaved(true);
				    	mw.updateTitle(dl.getTitle());
				    }
				}
				else {
					new Project().saveProject(ll, dl);
					dl.setSaved(true);
				}
			}
		}
		else if (arg.equals("Save image")) {
			if (uis.useTabs()) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter( "PNG image files (.PNG)", "PNG");
			    chooser.setFileFilter(filter);
			    chooser.setApproveButtonText("Save");
			    chooser.setDialogTitle("Save image");
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
			    	dl.setPath(filePath);
			    	new Image().saveImage(dl, (DrawingComponent) tc.getComponent(tc.getSelectedIndex()));
			    }
			}
			else {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter( "PNG image files (.PNG)", "PNG");
			    chooser.setFileFilter(filter);
			    chooser.setApproveButtonText("Save");
			    chooser.setDialogTitle("Save image");
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
			    	dl.setPath(filePath);
			    	new Image().saveImage(dl,dc);
			    }
			}
		}
		else if (arg.equals("Save selection")) {
			dl.setSelector(10);
		}
		else if (arg.equals("Exit")) {
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
		//EDIT
		else if (arg.equals("Add layer")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ll.addLayer();
				dl.setLayer(ll.getLayers().size()-1);
			}
			else {
				ll.addLayer();
				dl.setLayer(ll.getLayers().size()-1);
			}
		}
		else if (arg.equals("Remove layer")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ll.removeLayer(dl.getLayer());
				dl.setLayer(dl.getLayer()-1);
				tc.repaint();
			}
			else {
				ll.removeLayer(dl.getLayer());
				dl.setLayer(dl.getLayer()-1);
				dc.repaint();
			}
		}
		else if (arg.equals("Move layer")) {
			dl.setSelector(99);
		}
		else if (arg.equals("Undo")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				if (sl.getShapes().size() > 0) {
					dl.addRedo(sl.removeLast());
					dl.setSaved(false);
					tc.repaint();
				}
			}
			else {
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				if (sl.getShapes().size() > 0) {
					dl.addRedo(sl.removeLast());
					dl.setSaved(false);
					dc.repaint();
				}
			}
		}
		else if (arg.equals("Redo")) {
			if (uis.useTabs()) {
				if (dl.getRedoArray().size() > 0) {
					LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
					ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
					sl.addShapes(dl.getRedo());
					dl.setSaved(false);
					tc.repaint();
				}
			}
			else {
				if (dl.getRedoArray().size() > 0) {
					ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
					sl.addShapes(dl.getRedo());
					dl.setSaved(false);
					dc.repaint();
				}
			}
		}
		else if (arg.equals("Z-index +")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				if (sl.getShapes().indexOf(sl.getSelectedShapes()) < sl.getShapes().size()) {
					int operation = +1;
					sl.setZindex(sl.getSelectedShapes(), operation);
					dl.setSaved(false);
					tc.repaint();
				}
			}
			else {
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				if (sl.getShapes().indexOf(sl.getSelectedShapes()) < sl.getShapes().size()) {
					int operation = +1;
					sl.setZindex(sl.getSelectedShapes(), operation);
					dl.setSaved(false);
					dc.repaint();
				}
			}
		}
		else if (arg.equals("Z-index -")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				if (dl.getSelected()) {
					int operation = -1;
					sl.setZindex(sl.getSelectedShapes(), operation);
					dl.setSaved(false);
					tc.repaint();
				}
			}
			else {
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				if (dl.getSelected()) {
					int operation = -1;
					sl.setZindex(sl.getSelectedShapes(), operation);
					dl.setSaved(false);
					dc.repaint();
				}
			}
		}
		else if (arg.equals("Delete selected")) {
			if (uis.useTabs()) {
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				dl.addRedo(sl.removeSelected());
				dl.setSaved(false);
				tc.repaint();
			}
			else {
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				dl.addRedo(sl.removeSelected());
				dl.setSaved(false);
				dc.repaint();
			}
		}
		//TOOLS
		else if (arg.equals("Pencil")) {
			dl.setSelector(7);
		}
		else if (arg.equals("Eraser")) {
			dl.setSelector(8);
		}
		else if (arg.equals("Line")) {
			dl.setSelector(1);
		}
		else if (arg.equals("Rectangle")) {
			dl.setSelector(2);
		}
		else if (arg.equals("Oval")) {
			dl.setSelector(3);
		}
		else if (arg.equals("Triangle")) {
			dl.setSelector(9);
		}
		else if (arg.equals("Selector")) {
			dl.setSelector(4);
		}
		else if (arg.equals("Area selector")) {
			dl.setSelector(6);
		}
		else if (arg.equals("Text")) {
			dl.setSelector(5);
		}
		else if (arg.equals("Thickness")) {
			if (uis.useTabs()) {
				new OutlineManager(dl, tc, tl);
			}
			else {
				new OutlineManager(dl, dc, ll);
			}
		}
		else if (arg.equals("Rotation")) {
			if (uis.useTabs()) {
				new RotationManager(dl, tc, tl);
			}
			else {
				new RotationManager(dl, dc, ll);
			}
		}
		//COLOR
		else if (arg.equals("Stroke")) {
			if (uis.useTabs()) {
				Color stroke = JColorChooser.showDialog(null,"Select stroke color", Color.WHITE);
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
	        	ArrayList<Shapes> shapes = sl.getShapes();
	        	for (Shapes s : shapes) {
	        		if (s.getSelected()) {
	        			s.setOutline(stroke);
	        		}
	        	}
	        	tc.repaint();
	        	dl.setStroke(stroke);
			}
			else {
				Color stroke = JColorChooser.showDialog(null,"Select stroke color", Color.WHITE);
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
	        	ArrayList<Shapes> shapes = sl.getShapes();
	        	for (Shapes s : shapes) {
	        		if (s.getSelected()) {
	        			s.setOutline(stroke);
	        		}
	        	}
	        	dc.repaint();
	        	dl.setStroke(stroke);
			}
		}
		else if (arg.equals("Fill")) {
			if (uis.useTabs()) {
				Color fill = JColorChooser.showDialog(null,"Select fill color", Color.WHITE);
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
	        	ArrayList<Shapes> shapes = sl.getShapes();
	        	for (Shapes s : shapes) {
	        		if (s.getSelected()) {
	        			s.setFill(fill);
	        		}
	        	}
	        	dc.repaint();
	        	dl.setFill(fill);
			}
			else {
				Color fill = JColorChooser.showDialog(null,"Select fill color", Color.WHITE);
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
	        	ArrayList<Shapes> shapes = sl.getShapes();
	        	for (Shapes s : shapes) {
	        		if (s.getSelected()) {
	        			s.setFill(fill);
	        		}
	        	}
	        	tc.repaint();
	        	dl.setFill(fill);
			}
		}
		else if (arg.equals("Background")) {
			if (uis.useTabs()) {
				Color background = JColorChooser.showDialog(null,"Select background color", Color.WHITE);
				LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
				ll.setBackground(background);
				dl.setSaved(false);
				tc.repaint();
			}
			else {
				Color background = JColorChooser.showDialog(null,"Select background color", Color.WHITE);
				ll.setBackground(background);
				dl.setSaved(false);
				dc.repaint();
			}
		}
		//VIEW
		else if (arg.equals("Toggle toolbox")) {
			if (uis.getToolBox()) {
				uis.setToolBox(false);
				tb.toolBoxToggle();
				mw.updateWidth(-255);
			}
			else {
				uis.setToolBox(true);
				tb.toolBoxToggle();
				mw.updateWidth(255);
			}
		}
		else if (arg.equals("Layer manager")) {
			if (uis.useTabs()) {
				new LayerManager(dl, tl, tc);
			}
			else {
				new LayerManager(dl, ll, dc);
			}
		}
		else if (arg.equals("Shape manager")) {
			if (uis.useTabs()) {
				ShapesLibrary sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
				DrawingComponent dc = (DrawingComponent) tc.getComponent(tc.getSelectedIndex());
				new ShapeManager(dl, sl, dc);
			}
			else {
				ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
				new ShapeManager(dl, sl, dc);
			}
		}
		else if (arg.equals("Preferences")) {
			if (uis.useTabs()) {
				new SettingsManager(uis, mw, null, tl, dl);
			}
			else {
				new SettingsManager(uis, mw, ll, null, dl);
			}
		}
		//HELP
		else if (arg.equals("About")) {
			JOptionPane.showMessageDialog (null, "Developed by: \nHåkon Torgersen \n\nSketches-14 \nVersion: Release candidate 2", "  Sketches-14-RC2", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
