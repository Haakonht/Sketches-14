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
import GUI.TabComponent;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class LayerManager extends JDialog {
	
	private LayerLibrary ll;
	private DrawingComponent dc;
	private TabComponent tc;
	private DataLibrary dl;
	
	private JList<String> list;
	private Boolean solo = false;
	private Boolean tabs = false;
	
	public LayerManager(DataLibrary dl, LayerLibrary ll, DrawingComponent dc) {
		this.dl = dl;
		this.ll = ll;
		this.dc = dc;
		setSize(195,300);
		setResizable(false);
		setJMenuBar(menuBar());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("  Layer manager");
		generateList();
		setVisible(true);
	}

	public LayerManager(DataLibrary dl, TabLibrary tl, TabComponent tc) {
		this.dl = dl;
		this.ll = tl.getTabs().get(tc.getSelectedIndex());
		this.tc = tc;
		tabs = true;
		setSize(195,300);
		setResizable(false);
		setJMenuBar(menuBar());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("  Layer manager");
		generateList();
		setVisible(true);
	}

	private void generateList() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		ArrayList<ShapesLibrary> layers = new ArrayList<ShapesLibrary>();
		layers = ll.getLayers();
		for (ShapesLibrary s: layers) {
			model.addElement("Layer "+layers.indexOf(s));
		}
		list = new JList<String>(model);
	    add(new JScrollPane(list));
	    
	    ListSelectionListener listSelectionListener = new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent listSelectionEvent) {
	    		if (listSelectionEvent.getValueIsAdjusting()) {
	    			dl.setLayer(list.getSelectedIndex());
	    		}
	        }
	      };
	      list.addListSelectionListener(listSelectionListener);
	}
	
	private void updateJList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    ArrayList<ShapesLibrary> layers = new ArrayList<ShapesLibrary>();
		layers = ll.getLayers();
		for (ShapesLibrary s: layers) {
			model.addElement("Layer "+layers.indexOf(s));
		}  
	    list.setModel(model);     
	}

	private JMenuBar menuBar() {
		JMenuBar functions = new JMenuBar();
		
		JButton addLayer = new JButton("Add");
		addLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if (arg.equals("Add")) {
					ll.addLayer();
					dl.setLayer(dl.getLayer()+1);
					updateJList();
					if (tabs) {
						tc.repaint();
					}
					else {
						dc.repaint();
					}
				}
			}
		});
		
		JButton removeLayer = new JButton("Remove");
		removeLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if (arg.equals("Remove")) {
					ll.removeLayer(dl.getLayer());
					if (dl.getLayer() != 0) {
						dl.setLayer(dl.getLayer()-1);
					}
					updateJList();
					if (tabs) {
						tc.repaint();
					}
					else {
						dc.repaint();
					}
				}
			}
		});
		
		JButton soloButton = new JButton("Solo");
		soloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if (arg.equals("Solo")) {
					ArrayList<ShapesLibrary> shapesArray = ll.getLayers();
					if (!solo) {
						for (ShapesLibrary s : shapesArray) {
							if (shapesArray.indexOf(s) != dl.getLayer()) {
								s.setVisible(false);
							}
							solo = true;
							if (tabs) {
								tc.repaint();
							}
							else {
								dc.repaint();
							}
						}
					}
					else {
						for (ShapesLibrary s : shapesArray) {
							s.setVisible(true);
						}
						solo = false;
						if (tabs) {
							tc.repaint();
						}
						else {
							dc.repaint();
						}
					}
				}
			}
		});
		
		functions.add(addLayer);
		functions.add(removeLayer);
		functions.add(soloButton);
		
		return functions;
	}
	
}
