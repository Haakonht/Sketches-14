package Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.DrawingComponent;
import GUI.TabComponent;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Shapes.Shapes;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class RotationManager extends JDialog {
	
	private DataLibrary dl;
	private TabLibrary tl;
	private TabComponent tc;
	private LayerLibrary ll;
	private Shapes selectedShape;
	
	public RotationManager(DataLibrary dl, DrawingComponent dc, LayerLibrary ll) {
		setSize(350, 130);
		setTitle(" Set rotation");
		setLocationRelativeTo(null);
		setResizable(false);
		
		this.ll = ll;
		this.dl = dl;
		
		JLabel sliderValue = new JLabel();
		sliderValue.setText("" + getRotation());
		
		JSlider slider = new JSlider(-180, 180, getRotation());
		slider.setPreferredSize(new Dimension(160, 40));
		int minorTick = 5;
		slider.setMinorTickSpacing(minorTick);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(false);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put(-180, new JLabel("-180"));
	    table.put(-135, new JLabel("-135"));
	    table.put(-90, new JLabel("-90"));
	    table.put(-45, new JLabel("-45"));
	    table.put(0, new JLabel("0"));
	    table.put(45, new JLabel("45"));
	    table.put(90, new JLabel("90"));
	    table.put(135, new JLabel("135"));
	    table.put(180, new JLabel("180"));
	    slider.setLabelTable(table);
	    slider.setPaintLabels(true);
        //SLIDER STROKE THICKNESS
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
            	if (dl.getSelected()) {
            		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
            		Shapes s = sl.getSelectedShapes();
            		s.setRotation(slider.getValue());
            		sliderValue.setText(""+slider.getValue());
            		dc.repaint();
            	}
            	else {
            		sliderValue.setText(""+slider.getValue());
            	}
            }});
        add(slider, BorderLayout.CENTER);
        add(sliderValue, BorderLayout.EAST);
        setVisible(true);
	}

	public RotationManager(DataLibrary dl, TabComponent tc, TabLibrary tl) {
		this.dl = dl;
		this.tl = tl;
		this.tc = tc;
		
		setSize(350, 130);
		setTitle(" Set rotation");
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel sliderValue = new JLabel();
		sliderValue.setText("" + getTabbedRotation());
		
		JSlider slider = new JSlider(-180, 180, getTabbedRotation());
		slider.setPreferredSize(new Dimension(160, 40));
		int minorTick = 5;
		slider.setMinorTickSpacing(minorTick);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(false);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
	    table.put(-180, new JLabel("-180"));
	    table.put(-135, new JLabel("-135"));
	    table.put(-90, new JLabel("-90"));
	    table.put(-45, new JLabel("-45"));
	    table.put(0, new JLabel("0"));
	    table.put(45, new JLabel("45"));
	    table.put(90, new JLabel("90"));
	    table.put(135, new JLabel("135"));
	    table.put(180, new JLabel("180"));
	    slider.setLabelTable(table);
	    slider.setPaintLabels(true);
        //SLIDER STROKE THICKNESS
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
            	if (dl.getSelected()) {
            		LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
            		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
            		Shapes s = sl.getSelectedShapes();
            		s.setRotation(slider.getValue());
            		sliderValue.setText(""+slider.getValue());
            		tc.repaint();
            	}
            	else {
            		sliderValue.setText(""+slider.getValue());
            	}
            }});
        add(slider, BorderLayout.CENTER);
        add(sliderValue, BorderLayout.EAST);
        setVisible(true);
	}

	private int getRotation() {
		ArrayList<Shapes> shapeLayer = ll.getLayers().get(dl.getLayer()).getShapes();
		for (Shapes s : shapeLayer) {
			if (s.getSelected()) {
				selectedShape = s;
			}
		}
		if (selectedShape != null) {
			return selectedShape.getRotation();
		}
		else {
			return 0;
		}
	}
	
	private int getTabbedRotation() {
		LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
		ArrayList<Shapes> shapeLayer = ll.getLayers().get(dl.getLayer()).getShapes();
		for (Shapes s : shapeLayer) {
			if (s.getSelected()) {
				selectedShape = s;
			}
		}
		if (selectedShape != null) {
			return selectedShape.getRotation();
		}
		else {
			return 0;
		}
	}
	
}
