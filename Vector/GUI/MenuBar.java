package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Controllers.MenuLogic;
import CustomUIElements.ToolBox;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Libraries.UserInterfaceSettings;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	public MenuBar(DrawingComponent dc, LayerLibrary ll, DataLibrary dl, UserInterfaceSettings uis, MainWindow mw, ToolBox tb) {
		MenuLogic listener = new MenuLogic(dc, ll, dl, uis, mw, tb);
		
		//MENU HEADERS
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu tools = new JMenu("Tools");
		JMenu color = new JMenu("Color");
		JMenu view = new JMenu("View");
		JMenu help = new JMenu("Help");
		
		add(file);
		add(edit);
		add(tools);
		add(color);
		add(view);
		add(help);
		
		//FILE
		JMenuItem newProject = new JMenuItem("New");
		newProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK ));
		newProject.addActionListener(listener);
		
		JMenu project = new JMenu("Project");
		
		JMenuItem openProject = new JMenuItem("Open project");
		openProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK ));
		openProject.addActionListener(listener);
		
		JMenuItem saveProject = new JMenuItem("Save project");
		saveProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK ));
		saveProject.addActionListener(listener);
		
		JMenu image = new JMenu("Image");
		
		JMenuItem saveImage = new JMenuItem("Save image");
		saveImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK ));
		saveImage.addActionListener(listener);
		
		JMenuItem saveSelection = new JMenuItem("Save selection");
		saveSelection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
		saveSelection.addActionListener(listener);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK ));
		exit.addActionListener(listener);
		
		file.add(newProject);
		file.addSeparator();
		file.add(project);
		project.add(openProject);
		project.addSeparator();
		project.add(saveProject);
		file.addSeparator();
		file.add(image);
		image.add(saveImage);
		image.addSeparator();
		image.add(saveSelection);
		file.addSeparator();
		file.add(exit);
		
		//EDIT
		JMenu layers = new JMenu("Layer");
		
		JMenuItem addLayer = new JMenuItem("Add layer");
		addLayer.addActionListener(listener);
		
		JMenuItem removeLayer = new JMenuItem("Remove layer");
		removeLayer.addActionListener(listener);
		
		JMenuItem moveLayer = new JMenuItem("Move layer");
		moveLayer.addActionListener(listener);
	
		JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undo.addActionListener(listener);
		
		JMenuItem redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		redo.addActionListener(listener);
		
		JMenu zindex = new JMenu("Z-index");
		
		JMenuItem zindexPlus = new JMenuItem("Z-index +"); 
		zindexPlus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.SHIFT_MASK));
		zindexPlus.addActionListener(listener);
		
		JMenuItem zindexMinus = new JMenuItem("Z-index -");
		zindexMinus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK ));
		zindexMinus.addActionListener(listener);
		
		JMenuItem deleteSelected = new JMenuItem("Delete selected");
		deleteSelected.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK ));
		deleteSelected.addActionListener(listener);
		
		edit.add(layers);
		layers.add(addLayer);
		layers.addSeparator();
		layers.add(removeLayer);
		layers.addSeparator();
		layers.add(moveLayer);
		edit.addSeparator();
		edit.add(undo);
		edit.addSeparator();
		edit.add(redo);
		edit.addSeparator();
		edit.add(zindex);
		zindex.add(zindexPlus);
		zindex.addSeparator();
		zindex.add(zindexMinus);
		edit.addSeparator();
		edit.add(deleteSelected);
		
		//TOOLS
		JMenu pencilTools = new JMenu("Pencil");
		
		JMenuItem pencil = new JMenuItem("Pencil");
		pencil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		pencil.addActionListener(listener);
		
		JMenuItem eraser = new JMenuItem("Eraser");
		eraser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		eraser.addActionListener(listener);
		
		JMenuItem line = new JMenuItem("Line");
		line.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		line.addActionListener(listener);
		
		JMenu shapes = new JMenu("Shapes");
		
		JMenuItem rectangle = new JMenuItem("Rectangle");
		rectangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		rectangle.addActionListener(listener);
		
		JMenuItem ellipse = new JMenuItem("Oval");
		ellipse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK));
		ellipse.addActionListener(listener);
		
		JMenuItem triangle = new JMenuItem("Triangle");
		triangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.CTRL_MASK));
		triangle.addActionListener(listener);
		
		JMenuItem text = new JMenuItem("Text");
		text.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		text.addActionListener(listener);
		
		JMenu selectors = new JMenu("Selectors  ");
		
		JMenuItem singleSelector = new JMenuItem("Selector");
		singleSelector.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		singleSelector.addActionListener(listener);
		
		JMenuItem areaSelector = new JMenuItem("Area selector");
		areaSelector.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		areaSelector.addActionListener(listener);
		
		JMenuItem strokeMenu = new JMenuItem("Thickness");
		strokeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		strokeMenu.addActionListener(listener);
		
		JMenuItem rotation = new JMenuItem("Rotation");
		rotation.addActionListener(listener);
		
        tools.add(pencilTools);
        pencilTools.add(pencil);
        pencilTools.addSeparator();
        pencilTools.add(eraser);
        tools.addSeparator();
		tools.add(line);
		tools.addSeparator();
		tools.add(shapes);
		shapes.add(rectangle);
		shapes.addSeparator();
		shapes.add(ellipse);
		shapes.addSeparator();
		shapes.add(triangle);
		tools.addSeparator();
		tools.add(text);
		tools.addSeparator();
		tools.add(selectors);
		selectors.add(singleSelector);
		selectors.addSeparator();
		selectors.add(areaSelector);
		tools.addSeparator();
		tools.add(strokeMenu);
		tools.addSeparator();
		tools.add(rotation);
		
		//COLOR
		JMenuItem stroke = new JMenuItem("Stroke");
		stroke.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		stroke.addActionListener(listener);
		
		JMenuItem fill = new JMenuItem("Fill");
		fill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
		fill.addActionListener(listener);
		
		JMenuItem background = new JMenuItem("Background");
		background.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
		background.addActionListener(listener);
		
		color.add(stroke);
		color.addSeparator();
		color.add(fill);
		color.addSeparator();
		color.add(background);
		
		//VIEW
		JMenuItem toolbox = new JMenuItem("Toggle toolbox");
		toolbox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
		toolbox.addActionListener(listener);
		
		JMenuItem layerManager = new JMenuItem("Layer manager");
		layerManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		layerManager.addActionListener(listener);
		
		JMenuItem shapeManager = new JMenuItem("Shape manager");
		shapeManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		shapeManager.addActionListener(listener);
		
		JMenuItem settings = new JMenuItem("Preferences");
		settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		settings.addActionListener(listener);
		
		view.add(toolbox);
		view.addSeparator();
		view.add(layerManager);
		view.addSeparator();
		view.add(shapeManager);
		view.addSeparator();
		view.add(settings);
		
		//HELP
		JMenuItem about = new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
		about.addActionListener(listener);
		
		help.add(about);
	}

	public MenuBar(TabComponent tc, TabLibrary tl, DataLibrary dl, UserInterfaceSettings uis, MainWindow mw, ToolBox tb) {
		MenuLogic listener = new MenuLogic(tc, tl, dl, uis, mw, tb);
		
		//MENU HEADERS
				JMenu file = new JMenu("File");
				JMenu edit = new JMenu("Edit");
				JMenu tools = new JMenu("Tools");
				JMenu color = new JMenu("Color");
				JMenu view = new JMenu("View");
				JMenu help = new JMenu("Help");
				
				add(file);
				add(edit);
				add(tools);
				add(color);
				add(view);
				add(help);
				
				//FILE
				JMenu newMenu = new JMenu("New");
				
				JMenuItem newProject = new JMenuItem("New project");
				newProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK ));
				newProject.addActionListener(listener);
				
				JMenuItem newSketch = new JMenuItem("New sketch");
				newSketch.addActionListener(listener);
				
				JMenu tabMenu = new JMenu("Tab menu");
				
				JMenuItem newTab = new JMenuItem("New tab");
				newTab.addActionListener(listener);
				
				JMenuItem closeTab = new JMenuItem("Close tab");
				closeTab.addActionListener(listener);
				
				JMenu project = new JMenu("Project");
				
				JMenuItem openProject = new JMenuItem("Open project");
				openProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK ));
				openProject.addActionListener(listener);
				
				JMenuItem saveProject = new JMenuItem("Save project");
				saveProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK ));
				saveProject.addActionListener(listener);
				
				JMenu image = new JMenu("Image");
				
				JMenuItem saveImage = new JMenuItem("Save image");
				saveImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK ));
				saveImage.addActionListener(listener);
				
				JMenuItem saveSelection = new JMenuItem("Save selection");
				saveSelection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
				saveSelection.addActionListener(listener);
				
				JMenuItem exit = new JMenuItem("Exit");
				exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK ));
				exit.addActionListener(listener);
				
				file.add(newMenu);
				newMenu.add(newProject);
				newMenu.addSeparator();
				newMenu.add(newSketch);
				file.addSeparator();
				file.add(tabMenu);
				tabMenu.add(newTab);
				tabMenu.addSeparator();
				tabMenu.add(closeTab);
				file.addSeparator();
				file.add(project);
				project.add(openProject);
				project.addSeparator();
				project.add(saveProject);
				file.addSeparator();
				file.add(image);
				image.add(saveImage);
				image.addSeparator();
				image.add(saveSelection);
				file.addSeparator();
				file.add(exit);
				
				//EDIT
				JMenu layers = new JMenu("Layer");
				
				JMenuItem addLayer = new JMenuItem("Add layer");
				addLayer.addActionListener(listener);
				
				JMenuItem removeLayer = new JMenuItem("Remove layer");
				removeLayer.addActionListener(listener);
				
				JMenuItem moveLayer = new JMenuItem("Move layer");
				moveLayer.addActionListener(listener);
			
				JMenuItem undo = new JMenuItem("Undo");
				undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
				undo.addActionListener(listener);
				
				JMenuItem redo = new JMenuItem("Redo");
				redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
				redo.addActionListener(listener);
				
				JMenu zindex = new JMenu("Z-index");
				
				JMenuItem zindexPlus = new JMenuItem("Z-index +"); 
				zindexPlus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.SHIFT_MASK));
				zindexPlus.addActionListener(listener);
				
				JMenuItem zindexMinus = new JMenuItem("Z-index -");
				zindexMinus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK ));
				zindexMinus.addActionListener(listener);
				
				JMenuItem deleteSelected = new JMenuItem("Delete selected");
				deleteSelected.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK ));
				deleteSelected.addActionListener(listener);
				
				edit.add(layers);
				layers.add(addLayer);
				layers.addSeparator();
				layers.add(removeLayer);
				layers.addSeparator();
				layers.add(moveLayer);
				edit.addSeparator();
				edit.add(undo);
				edit.addSeparator();
				edit.add(redo);
				edit.addSeparator();
				edit.add(zindex);
				zindex.add(zindexPlus);
				zindex.addSeparator();
				zindex.add(zindexMinus);
				edit.addSeparator();
				edit.add(deleteSelected);
				
				//TOOLS
				JMenu pencilTools = new JMenu("Pencil");
				
				JMenuItem pencil = new JMenuItem("Pencil");
				pencil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
				pencil.addActionListener(listener);
				
				JMenuItem eraser = new JMenuItem("Eraser");
				eraser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
				eraser.addActionListener(listener);
				
				JMenuItem line = new JMenuItem("Line");
				line.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
				line.addActionListener(listener);
				
				JMenu shapes = new JMenu("Shapes");
				
				JMenuItem rectangle = new JMenuItem("Rectangle");
				rectangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
				rectangle.addActionListener(listener);
				
				JMenuItem ellipse = new JMenuItem("Oval");
				ellipse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.CTRL_MASK));
				ellipse.addActionListener(listener);
				
				JMenuItem triangle = new JMenuItem("Triangle");
				triangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.CTRL_MASK));
				triangle.addActionListener(listener);
				
				JMenuItem text = new JMenuItem("Text");
				text.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
				text.addActionListener(listener);
				
				JMenu selectors = new JMenu("Selectors  ");
				
				JMenuItem singleSelector = new JMenuItem("Selector");
				singleSelector.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
				singleSelector.addActionListener(listener);
				
				JMenuItem areaSelector = new JMenuItem("Area selector");
				areaSelector.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
				areaSelector.addActionListener(listener);
				
				JMenuItem strokeMenu = new JMenuItem("Thickness");
				strokeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
				strokeMenu.addActionListener(listener);
				
				JMenuItem rotation = new JMenuItem("Rotation");
				rotation.addActionListener(listener);
				
		        tools.add(pencilTools);
		        pencilTools.add(pencil);
		        pencilTools.addSeparator();
		        pencilTools.add(eraser);
		        tools.addSeparator();
				tools.add(line);
				tools.addSeparator();
				tools.add(shapes);
				shapes.add(rectangle);
				shapes.addSeparator();
				shapes.add(ellipse);
				shapes.addSeparator();
				shapes.add(triangle);
				tools.addSeparator();
				tools.add(text);
				tools.addSeparator();
				tools.add(selectors);
				selectors.add(singleSelector);
				selectors.addSeparator();
				selectors.add(areaSelector);
				tools.addSeparator();
				tools.add(strokeMenu);
				tools.addSeparator();
				tools.add(rotation);
				
				//COLOR
				JMenuItem stroke = new JMenuItem("Stroke");
				stroke.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
				stroke.addActionListener(listener);
				
				JMenuItem fill = new JMenuItem("Fill");
				fill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
				fill.addActionListener(listener);
				
				JMenuItem background = new JMenuItem("Background");
				background.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
				background.addActionListener(listener);
				
				color.add(stroke);
				color.addSeparator();
				color.add(fill);
				color.addSeparator();
				color.add(background);
				
				//VIEW
				JMenuItem toolbox = new JMenuItem("Toggle toolbox");
				toolbox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
				toolbox.addActionListener(listener);
				
				JMenuItem layerManager = new JMenuItem("Layer manager");
				layerManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
				layerManager.addActionListener(listener);
				
				JMenuItem shapeManager = new JMenuItem("Shape manager");
				shapeManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
				shapeManager.addActionListener(listener);
				
				JMenuItem settings = new JMenuItem("Preferences");
				settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
				settings.addActionListener(listener);
				
				view.add(toolbox);
				view.addSeparator();
				view.add(layerManager);
				view.addSeparator();
				view.add(shapeManager);
				view.addSeparator();
				view.add(settings);
				
				//HELP
				JMenuItem about = new JMenuItem("About");
				about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
				about.addActionListener(listener);
				
				help.add(about);
	}

}
