package Dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.DrawingComponent;
import Libraries.DataLibrary;
import Shapes.Shapes;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class ShapeManager extends JDialog {
	
	private ShapesLibrary sl;
	private DrawingComponent dc;
	private DataLibrary dl;
	
	private JList<String> list;
	
	public ShapeManager(DataLibrary dl, ShapesLibrary sl, DrawingComponent dc) {
		this.dl = dl;
		this.sl = sl;
		this.dc = dc;
		setSize(175,300);
		setResizable(false);
		setJMenuBar(menuBar());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Shapes - Layer "+dl.getLayer());
		generateList();
		setVisible(true);
	}

	private void generateList() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		ArrayList<Shapes> shapes = new ArrayList<Shapes>();
		shapes = sl.getShapes();
		int i = 1;
		for (Shapes s: shapes) {
			model.addElement(s.getDescription()+" @ Z-Index: "+i);
			i++;
		}
		list = new JList<String>(model);
	    add(new JScrollPane(list));
	    
	    ListSelectionListener listSelectionListener = new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent listSelectionEvent) {
	        	if (listSelectionEvent.getValueIsAdjusting()) {
		        	ArrayList<Shapes> shapes = new ArrayList<Shapes>();
		    		shapes = sl.getShapes();
		        	for (Shapes s : shapes) {
		        		s.setSelected(false);
		        		Shapes selected = shapes.get(list.getSelectedIndex());
		        		selected.setSelected(true);
		        	}
		        	dl.setSelector(4);
		        	dc.repaint();
	        	}
	        }
	    };
	      list.addListSelectionListener(listSelectionListener);
	}
	
	private void updateJList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    ArrayList<Shapes> shapes = new ArrayList<Shapes>();
		shapes = sl.getShapes();
		int i = 1;
		for (Shapes s: shapes) {
			model.addElement(s.getDescription()+" @ Z-Index: "+i);
			i++;
		}   
	    list.setModel(model);     
	}

	private JMenuBar menuBar() {
		JMenuBar functions = new JMenuBar();
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if (arg.equals("Delete")) {
					sl.removeSelected();
					updateJList();
					dc.repaint();
				}
			}
		});
		
		JButton zPlus = new JButton("Z +");
		zPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if (arg.equals("Z +")) {
					if (sl.getShapes().indexOf(sl.getSelectedShapes()) < sl.getShapes().size()) {
						int operation = +1;
						sl.setZindex(sl.getSelectedShapes(), operation);
						dc.repaint();
						updateJList();
						list.setSelectedIndex(list.getSelectedIndex()+1);
					}
				}
			}
		});
		
		JButton zMinus = new JButton("Z -");
		zMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if (arg.equals("Z -")) {
					if (sl.getShapes().indexOf(sl.getSelectedShapes()) < sl.getShapes().size()) {
						int operation = -1;
						sl.setZindex(sl.getSelectedShapes(), operation);
						dc.repaint();
						updateJList();
						list.setSelectedIndex(list.getSelectedIndex());
					}
				}
			}
		});
		
		functions.add(delete);
		functions.add(zPlus);
		functions.add(zMinus);
		
		return functions;
	}
	
}
