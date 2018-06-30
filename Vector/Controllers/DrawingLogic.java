package Controllers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.DrawingComponent;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Operations.Image;
import Shapes.DrawLine;
import Shapes.DrawOval;
import Shapes.DrawPencil;
import Shapes.DrawRectangle;
import Shapes.DrawText;
import Shapes.DrawTriangle;
import Shapes.Shapes;
import Shapes.ShapesLibrary;

public class DrawingLogic implements MouseListener, MouseMotionListener {

	//INITIALIZE RESPONSIBILITIES
	private LayerLibrary ll;
	private DrawingComponent dc;
	private DataLibrary dl;
	
	//INITIALIZE VARIABLES
	private Point start = null, shadow = null;
	
	public DrawingLogic(DrawingComponent dc, LayerLibrary ll, DataLibrary dl) {
		this.dc = dc;
		this.ll = ll;
		this.dl = dl;
	}
	
	//GETTERS SHADOW
	public Point getStart() { return start; }
	public Point getShadow() { return shadow; }
	
	//METHODS
	private void selectShape(MouseEvent e) {
		if (dl.getSelector() == 4) {
			ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
			ArrayList<Shapes> shapes = sl.getShapes();
			ArrayList<Shapes> selected = new ArrayList<Shapes>();
			Rectangle2D f = new Rectangle(e.getX(), e.getY(), 5, 5);
			dl.setSelected(false);
			for (Shapes s : shapes) {
				s.setSelected(false);
				if (s.getShape().intersects(f)) {
					selected.add(s);
					dl.setSelected(true);
				}
			}
			if (selected.size() > 0) {
				Shapes r = selected.get(selected.size()-1);
				r.setSelected(true);
			}
		}
	}
	private void areaSelect(MouseEvent e) {
		if (dl.getSelector() == 6) {
			ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
			Shape r = new Rectangle(start.x, start.y, Math.abs(start.x - e.getX()), Math.abs(start.y - e.getY()));
			ArrayList<Shapes> shapes = sl.getShapes();
			for (Shapes s : shapes) {
				if (r.intersects(s.getShape().getBounds())) {
					s.setSelected(true);
				}
				else {
					s.setSelected(false);
				}
			}
		}
		else if (dl.getSelector() == 10) {
			Rectangle rect = DrawRectangle.drawRect(start, e.getPoint()).getBounds();
			shadow = null;
		    dc.repaint();
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter( "PNG image files (.PNG)", "PNG");
		    chooser.setFileFilter(filter);
		    chooser.setApproveButtonText("Save");
		    chooser.setDialogTitle("Save image");
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	String filePath = chooser.getSelectedFile().getAbsolutePath().toString();
		    	dl.setPath(filePath);
		    	new Image().saveSelection(dl, dc, rect);
		    }
		}
	}
	private void addShape(MouseEvent e) {
		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
		if (dl.getSelector() == 1) {
			sl.addShapes(new DrawLine(dl.getStroke(), dl.getThickness(), start, e.getPoint()));
			dl.setSaved(false);
		}
		else if (dl.getSelector() == 2) {
			sl.addShapes(new DrawRectangle(dl.getStroke(), dl.getFill(), dl.getThickness(), start, e.getPoint()));
			dl.setSaved(false);
		}
		else if (dl.getSelector() == 3) {
			sl.addShapes(new DrawOval(dl.getStroke(), dl.getFill(), dl.getThickness(), start, e.getPoint()));
			dl.setSaved(false);
		}
		else if (dl.getSelector() == 5) {
			drawText(e, start);
		}
		else if (dl.getSelector() == 9) {
			sl.addShapes(new DrawTriangle(dl.getStroke(), dl.getFill(), dl.getThickness(), start, e.getPoint()));
			dl.setSaved(false);
		}
	}
	private void shapeTransforms(MouseEvent e) {
		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
		if (dl.getSelector() == 4 && dl.getSelected() && e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {	
			shadow = null;
			dc.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			Shapes s = sl.getSelectedShapes();
			s.setStart(e.getPoint());
		}
		if (dl.getSelector() == 4 && dl.getSelected() && e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {	
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
			dc.setCursor(blankCursor);
			shadow = null;
			Shapes s = sl.getSelectedShapes();
			if (s.getDescription().equals("Line")) {
				s.setEnd(e.getPoint());
			}
			else if (s.getDescription().equals("Triangle")) {
				int width = s.getShape().getBounds().width;
				s.setStart(e.getPoint());
				s.setEnd(new Point(e.getX()+width, e.getY()));
			}
			else {
				int height = s.getShape().getBounds().height;
				int width = s.getShape().getBounds().width;
				s.setStart(e.getPoint());
				s.setEnd(new Point(e.getX()+width, e.getY()+height));
			}
		}
	}
	
	private void drawText(MouseEvent e, Point start){
		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
		JTextField jt = new JTextField();
		Rectangle r = new Rectangle(Math.min(start.x, e.getX()), Math.min(start.y, e.getY()), Math.abs(start.x - e.getX()), Math.abs(start.y - e.getY()));
		jt.setBounds(r);
		jt.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		jt.setOpaque(false);
		jt.setForeground(dl.getStroke());
		Font labelFont = jt.getFont();
		String labelText = jt.getText();
		int stringWidth = jt.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = jt.getWidth();
		double widthRatio = (double)componentWidth / (double)stringWidth;
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		double componentHeightDouble = (jt.getHeight() * 0.8);
		int componentHeight = (int)componentHeightDouble;
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		Font f = new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse);
		jt.setFont(f);
		jt.setVisible(true);
		dc.add(jt);
		jt.requestFocus();
		jt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (!jt.getText().trim().equals("")) {
            		sl.addShapes(new DrawText(dl.getStroke(), jt.getText(), new Point(start.x, (int) (start.y+(jt.getHeight()*0.8))) , f, r));
            		dl.setSaved(false);
            	}
            	dc.remove(jt);
            	dc.repaint();
            }});
	}
	private void drawPencil(MouseEvent e) {
		ShapesLibrary sl = ll.getLayers().get(dl.getLayer());
		if (dl.getSelector() == 7) {
			sl.addPencil(new DrawPencil(dl.getStroke(), dl.getThickness(), e.getPoint()));
			dl.setSaved(false);
		}
		else if (dl.getSelector() == 8) {
			Rectangle2D f = new Rectangle(e.getX(), e.getY(), 5, 5);
			ArrayList<Shapes> pencil = sl.getPencil();
			for (int i=0; i<pencil.size(); i++) {
				Shapes s = pencil.get(i);
				if (s.getShape().intersects(f)) {
					sl.erasePencil(s);
					dl.setSaved(false);
				}
			}
		}
	}
	//LISTENERS
	@Override
	public void mousePressed(MouseEvent e) {
		start = e.getPoint();
		drawPencil(e);
		selectShape(e);
		dc.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		addShape(e);
		areaSelect(e);
		start = null;
		shadow = null;
		dc.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		shadow = e.getPoint();
		drawPencil(e);
		shapeTransforms(e);
		dc.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		dc.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
