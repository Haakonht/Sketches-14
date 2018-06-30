package CustomUIElements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.DrawingComponent;
import GUI.TabComponent;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Libraries.UserInterfaceSettings;
import Shapes.Shapes;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class ToolBox extends JPanel implements ActionListener {

	private Boolean tabs = false;
    private Boolean stroke = true;
	
    private UserInterfaceSettings uis;
	private DataLibrary dl;
	private DrawingComponent dc;
	private TabComponent tc;
	private LayerLibrary ll;
	private TabLibrary tl;

	public ToolBox(LayerLibrary ll, DrawingComponent dc, DataLibrary dl, UserInterfaceSettings uis) {
		this.ll = ll;
		this.dl = dl;
		this.dc = dc;
		this.uis = uis;
		setSize(new Dimension(100,dc.getHeight()));
		setFocusable(false);
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
		shapeSelection();
		setVisible(uis.getToolBox());
	}
	
	public ToolBox(TabLibrary tl, TabComponent tc, DataLibrary dl, UserInterfaceSettings uis) {
		tabs = true;
		this.tl = tl;
		this.dl = dl;
		this.tc = tc;
		this.uis = uis;
		setSize(new Dimension(100,tc.getHeight()));
		setFocusable(false);
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
		shapeSelection();
		setVisible(uis.getToolBox());
	}

	public void toolBoxToggle() {
		setVisible(uis.getToolBox());
	}
	
	private void shapeSelection() {
		Dimension d = new Dimension(WIDTH, 35);
		
		JPanel toolBox = new JPanel();
		toolBox.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#EEEEEE")));
		
		JPanel shapeTools = new JPanel();
		shapeTools.setLayout(new BoxLayout(shapeTools,BoxLayout.Y_AXIS));
		
		JPanel shapePanel = new JPanel();
		shapePanel.setLayout(new GridLayout(0,2));
		
		JButton pencil = new JButton("Pencil");
		pencil.setPreferredSize(d);
		pencil.addActionListener(this);
		
		JButton eraser = new JButton("Eraser");
		eraser.addActionListener(this);
		
		JButton line = new JButton("Line");
		line.addActionListener(this);
		
		JButton recangle = new JButton("Rectangle");
		recangle.addActionListener(this);
		
		JButton oval = new JButton("Oval");
		oval.addActionListener(this);
		
		JButton triangle = new JButton("Triangle");
		triangle.addActionListener(this);
		
		JPanel selectorPanel = new JPanel();
		selectorPanel.setLayout(new GridLayout(0,1));
		
		JButton selector = new JButton("Selector");
		selector.setPreferredSize(d);
		selector.addActionListener(this);

		JButton areaSelector = new JButton("Area selector");
		areaSelector.addActionListener(this);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(0,1));
		textPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, Color.decode("#EEEEEE")));
		
		JButton text = new JButton("Text");
		text.setPreferredSize(d);
		text.addActionListener(this);
		
		@SuppressWarnings("unused")
		JLabel sliderInfo = new JLabel("Bordtykkelse:", SwingConstants.CENTER);
		
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY));
		JSlider slider = new JSlider(1, 20, dl.getThickness());
		slider.setBorder(BorderFactory.createMatteBorder(10, 4, 5, 4, Color.decode("#EEEEEE")));
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
	    sliderPanel.add(slider);
        slider.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent event) {
        		ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			s.setThickness(slider.getValue());
            		}
            	}
        		dl.setThickness(slider.getValue());
        		if (tabs) {
        			tc.repaint();
        		}
        		else {
        			dc.repaint();
        		}
            }
        });
	
        JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.LIGHT_GRAY));
		
		JPanel modifier = new JPanel();
		modifier.setLayout(new BorderLayout());
		modifier.setBorder(BorderFactory.createMatteBorder(10, 30, 5, 30, Color.decode("#EEEEEE")));
		
		JButton outline = new JButton("    Stroke    ");
		outline.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	stroke = true;
	        }
		});
		
		JButton fill = new JButton("    Fill    ");
		fill.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	stroke = false;
	        }
		});
		
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(Color.WHITE);
		colorPanel.setLayout(new GridLayout(0,3,2,2));
		colorPanel.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, Color.decode("#EEEEEE")));
		
		Dimension v = new Dimension(20,40);
		
		ColorButton black = new ColorButton(Color.BLACK);
		black.setPreferredSize(v);
		black.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		black.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.BLACK, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.BLACK, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.BLACK, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.BLACK, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton dark_gray = new ColorButton(Color.DARK_GRAY);
		dark_gray.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		dark_gray.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.DARK_GRAY, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.DARK_GRAY, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.DARK_GRAY, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.DARK_GRAY, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton gray = new ColorButton(Color.GRAY);
		gray.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		gray.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.GRAY, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.GRAY, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
            	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.GRAY, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.GRAY, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton light_gray = new ColorButton(Color.LIGHT_GRAY);
		light_gray.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		light_gray.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.LIGHT_GRAY, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.LIGHT_GRAY, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
            	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.LIGHT_GRAY, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.LIGHT_GRAY, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton white = new ColorButton(Color.WHITE);
		white.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		white.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.WHITE, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.WHITE, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.WHITE, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.WHITE, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton blue = new ColorButton(Color.BLUE);
		blue.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		blue.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.BLUE, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.BLUE, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.BLUE, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.BLUE, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton cyan = new ColorButton(Color.CYAN);
		cyan.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		cyan.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.CYAN, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.CYAN, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.CYAN, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.CYAN, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton green = new ColorButton(Color.GREEN);
		green.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		green.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.GREEN, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.GREEN, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.GREEN, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.GREEN, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton magenta = new ColorButton(Color.MAGENTA);
		magenta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		magenta.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.MAGENTA, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.MAGENTA, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.MAGENTA, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.MAGENTA, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton orange = new ColorButton(Color.ORANGE);
		orange.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		orange.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.ORANGE, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.ORANGE, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.ORANGE, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.ORANGE, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton yellow = new ColorButton(Color.YELLOW);
		yellow.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#333333")));
		yellow.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.YELLOW, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.YELLOW, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.YELLOW, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.YELLOW, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton red = new ColorButton(Color.RED);
		red.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,  Color.decode("#333333")));
		red.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.RED, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.RED, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.RED, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.RED, dl.getAlpha()));
	        	}
	        }
		});
		ColorButton pink = new ColorButton(Color.PINK);
		pink.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,  Color.decode("#333333")));
		pink.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(Color.PINK, dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(Color.PINK, dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
	        	if (stroke) {
	        		dl.setStroke(adjustAlpha(Color.PINK, dl.getAlpha()));
	        	}
	        	else {
	        		dl.setFill(adjustAlpha(Color.PINK, dl.getAlpha()));
	        	}
	        }
		});
			
		JPanel opaquePanel = new JPanel();
		opaquePanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
		JSlider opaqueSlider = new JSlider(1, 100, (int) (dl.getAlpha()*100));
		slider.setBorder(BorderFactory.createMatteBorder(10, 4, 5, 4, Color.decode("#EEEEEE")));
		int minorTicks = 5;
		opaqueSlider.setMinorTickSpacing(minorTicks);
		opaqueSlider.setPaintTicks(true);
		Hashtable<Integer, JLabel> opaqueTable = new Hashtable<Integer, JLabel>();
		opaqueTable.put(1, new JLabel("Opaque"));
		opaqueTable.put(100, new JLabel("Solid"));
		opaqueSlider.setLabelTable(opaqueTable);
		opaqueSlider.setPaintLabels(true);
	    opaquePanel.add(opaqueSlider);
	    opaqueSlider.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent event) {
            	dl.setAlpha(opaqueSlider.getValue()/100f);
            	ShapesLibrary sl;
        		if (tabs) {
        			sl = tl.getTabs().get(tc.getSelectedIndex()).getLayers().get(dl.getLayer());
        		}
        		else {
        			sl = ll.getLayers().get(dl.getLayer());
        		}
            	ArrayList<Shapes> shapes = sl.getShapes();
            	for (Shapes s : shapes) {
            		if (s.getSelected()) {
            			if (stroke) {
            				s.setOutline(adjustAlpha(s.getOutline(), dl.getAlpha()));
            			}
            			else {
            				s.setFill(adjustAlpha(s.getFill(), dl.getAlpha()));
            			}
            		}
            	}
            	if (tabs) {
            		tc.repaint();
            	}
            	else {
            		dc.repaint();
            	}
            	if (stroke) {
            		dl.setStroke(adjustAlpha(dl.getStroke(), dl.getAlpha()));
            	}
            	else {
            		dl.setFill(adjustAlpha(dl.getFill(), dl.getAlpha()));
            	}
            }
        });
		
		add(toolBox);
		
		toolBox.add(shapeTools);
		
		shapeTools.add(selectorPanel);
		shapeTools.add(shapePanel);
		shapeTools.add(textPanel);
		shapeTools.add(sliderPanel);
		shapeTools.add(panel);
		shapeTools.add(opaquePanel);
		
		selectorPanel.add(selector);
		selectorPanel.add(areaSelector);
		textPanel.add(text);
		
		shapePanel.add(pencil);
		shapePanel.add(eraser);
		shapePanel.add(line);
		shapePanel.add(recangle);
		shapePanel.add(oval);
		shapePanel.add(triangle);
			
		panel.add(modifier);
		panel.add(colorPanel);
		
		colorPanel.add(white);
		colorPanel.add(light_gray);
		colorPanel.add(gray);
		colorPanel.add(dark_gray);
		colorPanel.add(black);
		colorPanel.add(red);
		colorPanel.add(yellow);
		colorPanel.add(green);
		colorPanel.add(cyan);
		colorPanel.add(blue);
		colorPanel.add(pink);
		colorPanel.add(magenta);
		
		modifier.add(outline, BorderLayout.WEST);
		modifier.add(fill, BorderLayout.EAST);
	}

	private Color adjustAlpha(Color color, float factor) {
	    int alpha = (int) (dl.getAlpha() * 255);
	    return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		String arg = evt.getActionCommand();
		if (arg.equals("Pencil")) {
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
	}
}

