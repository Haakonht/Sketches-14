package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import CustomUIElements.ColorPanel;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements MouseMotionListener, ActionListener {
	
	public StatusBar(DataLibrary dl, LayerLibrary ll, TabLibrary tl) {
		setMinimumSize(new Dimension(HEIGHT, 100));
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
		setLayout(new BorderLayout());
		
		JPanel StatusPanel = new JPanel();
		StatusPanel.setLayout(new GridLayout(0,5));
		StatusPanel.setBackground(Color.decode("#EEEEEE"));
		StatusPanel.setBorder(BorderFactory.createMatteBorder(4, 15, 5, 15, Color.decode("#EEEEEE")));
		add(StatusPanel);
		
		JLabel outlineSpacer = new JLabel("         ");
		JLabel fillSpacer    = new JLabel("         ");
		
		JLabel activeTool = new JLabel();
		activeTool.setText("Active tool: ");
		StatusPanel.add(activeTool);
		
		JLabel coordinates = new JLabel();
		coordinates.setText("Coordinates:");
		StatusPanel.add(coordinates);
		
		JLabel outline = new JLabel();
		outline.setText("Outline:  ");
		
		ColorPanel outlineColor = new ColorPanel(dl.getStroke());
		outlineColor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		
		JLabel outlineContainer = new JLabel();
		outlineContainer.setLayout(new BorderLayout());
		outlineContainer.add(outline, BorderLayout.WEST);
		outlineContainer.add(outlineColor, BorderLayout.CENTER);
		outlineContainer.add(outlineSpacer, BorderLayout.EAST);
		StatusPanel.add(outlineContainer);
		
		JLabel fill = new JLabel();
		fill.setText("Fill:    ");
		
		ColorPanel fillColor = new ColorPanel(dl.getFill());
		fillColor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		
		JLabel fillContainer = new JLabel();
		fillContainer.setLayout(new BorderLayout());
		fillContainer.add(fill, BorderLayout.WEST);
		fillContainer.add(fillColor, BorderLayout.CENTER);
		fillContainer.add(fillSpacer, BorderLayout.EAST);
		StatusPanel.add(fillContainer);
		
		JLabel thickness = new JLabel();
		thickness.setLayout(new BorderLayout());
		StatusPanel.add(thickness);
		
		JLabel fileSize = new JLabel();
		fileSize.setForeground(Color.GRAY);
		thickness.add(fileSize, BorderLayout.EAST);
		
		//TIMER FOR TOOL UPDATES
		ActionListener checkTools = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (tl == null) {
					ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
					fileSize.setText(sl.getShapes().size()+" element(s)");
				}
				else {
					LayerLibrary ll = tl.getTabs().get(dl.getActiveTab());
					ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
					fileSize.setText(sl.getShapes().size()+" element(s)");
				}
				switch (dl.getSelector()) {
					case 1:  activeTool.setText("Active tool: Line");
			                 break;
			        case 2:  activeTool.setText("Active tool: Rectangle");
			                 break;
			        case 3:  activeTool.setText("Active tool: Oval");
			                 break;
			        case 4:  activeTool.setText("Active tool: Selector");
			                 break;
			        case 5:  activeTool.setText("Active tool: Text");
			                 break;
			        case 6:  activeTool.setText("Active tool: Area selector");
	                 		 break; 
			        case 7:  activeTool.setText("Active tool: Pencil");
			        		 break; 
			        case 8:  activeTool.setText("Active tool: Eraser");
	        		 		 break; 
			        case 9:  activeTool.setText("Active tool: Triangle");
   		 		 			 break;
			        case 99: activeTool.setText("Active tool: test tool");
			        		 break;
				}
				thickness.setText("Thickness: "+ dl.getThickness());
				outlineColor.setBackground(dl.getStroke());
				fillColor.setBackground(dl.getFill());
			}
		};
	  	new Timer(500, checkTools).start();
	  
	  	//TIMER FOR COORDINATE UPDATES
	  	ActionListener checkCoordinates = new ActionListener() {
		  	public void actionPerformed(ActionEvent evt) {
			  	String xy = MouseInfo.getPointerInfo().getLocation().toString();
			  	coordinates.setText("Coordinates: "+xy.substring(14));
		  	}
	  	};
	  	new Timer(100, checkCoordinates).start();
	}
	
	public StatusBar(DataLibrary dl, TabLibrary tl) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e){	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}		
		
}
