package Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class OutlineManager extends JDialog {
	
	public OutlineManager(DataLibrary dl, DrawingComponent dc, LayerLibrary ll) {
		setSize(250, 130);
		setTitle(" Set stroke");
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel sliderValue = new JLabel();
		sliderValue.setText(""+dl.getThickness());
		
		JSlider slider = new JSlider(1, 20, dl.getThickness());
		slider.setPreferredSize(new Dimension(80, 40));
		int minorTick = 1;
		slider.setMinorTickSpacing(minorTick);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
	    table.put(1, new JLabel("1"));
	    table.put(5, new JLabel("5"));
	    table.put(10, new JLabel("10"));
	    table.put(15, new JLabel("15"));
	    table.put(20, new JLabel("20"));
	    slider.setLabelTable(table);
	    slider.setPaintLabels(true);
        //SLIDER STROKE THICKNESS
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
            	if (dl.getSelected()) {
            		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
            		Shapes s = sl.getSelectedShapes();
            		s.setThickness(slider.getValue());
            		sliderValue.setText(""+slider.getValue());
            		dc.repaint();
            	}
            	else {
            		dl.setThickness(slider.getValue());
            		sliderValue.setText(""+slider.getValue());
            	}
            }});
        add(slider, BorderLayout.CENTER);
        setVisible(true);
		}

	public OutlineManager(DataLibrary dl, TabComponent tc, TabLibrary tl) {
		setSize(250, 130);
		setTitle(" Set stroke");
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel sliderValue = new JLabel();
		sliderValue.setText(""+dl.getThickness());
		
		JSlider slider = new JSlider(1, 20, dl.getThickness());
		slider.setPreferredSize(new Dimension(80, 40));
		int minorTick = 1;
		slider.setMinorTickSpacing(minorTick);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
	    table.put(1, new JLabel("1"));
	    table.put(5, new JLabel("5"));
	    table.put(10, new JLabel("10"));
	    table.put(15, new JLabel("15"));
	    table.put(20, new JLabel("20"));
	    slider.setLabelTable(table);
	    slider.setPaintLabels(true);
        //SLIDER STROKE THICKNESS
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
            	if (dl.getSelected()) {
            		LayerLibrary ll = tl.getTabs().get(tc.getSelectedIndex());
            		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
            		Shapes s = sl.getSelectedShapes();
            		s.setThickness(slider.getValue());
            		sliderValue.setText(""+slider.getValue());
            		tc.repaint();
            	}
            	else {
            		dl.setThickness(slider.getValue());
            		sliderValue.setText(""+slider.getValue());
            	}
            }});
        add(slider, BorderLayout.CENTER);
        setVisible(true);
		}	
	
}

