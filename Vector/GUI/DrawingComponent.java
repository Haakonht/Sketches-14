package GUI;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JComponent;

import Controllers.DrawingLogic;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Shapes.DrawOval;
import Shapes.DrawRectangle;
import Shapes.DrawTriangle;
import Shapes.Shapes;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class DrawingComponent extends JComponent {

	private Graphics2D graphSettings;
	private LayerLibrary ll;
	private DrawingLogic dwl;
	private DataLibrary dl;
	
	public DrawingComponent(LayerLibrary ll, DataLibrary dl) {
		DrawingLogic dwl = new DrawingLogic(this, ll, dl);
		this.ll = ll;
		this.dwl = dwl;
		this.dl = dl;
		addMouseListener(dwl);
		addMouseMotionListener(dwl);
	}
	
	//GET HIGHLIGHT COLOR
	private Color getContrastColor(Color color) {
		double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
		return y >= 128 ? Color.black : Color.white;
	}
	
	//CUSTOMIZED PAINTCOMPONENT
	public void paintComponent(Graphics g) {
		graphSettings = (Graphics2D)g;
		graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
		//RENDER BACKGROUND
		graphSettings.setColor(ll.getBackground());
		graphSettings.fillRect(0, 0, getWidth(), getHeight());
		
		ArrayList<ShapesLibrary> layersArray = ll.getLayers();
		
		for (ShapesLibrary sl : layersArray) {
			if (sl.getVisible()) {
				ArrayList<Shapes> shapesArray = sl.getShapes();
				for (Shapes s : shapesArray) {
					//DRAWS ALL SHAPES IN ARRAY
					s.drawShape(g);
					//HIGHLIGHTS SELECTED SHAPE
					if (s.getSelected()) {
						Shape highlight = s.getShape();
						AffineTransform oldXForm = graphSettings.getTransform();
						Color c = getContrastColor(s.getOutline());
						graphSettings.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
						graphSettings.setColor(c);
						graphSettings.rotate(Math.toRadians(s.getRotation()), s.getStart().getX() + (highlight.getBounds2D().getWidth() / 2), s.getStart().getY() + (highlight.getBounds2D().getHeight() / 2));
						graphSettings.draw(highlight);
						graphSettings.setTransform(oldXForm);
					}
				}
				ArrayList<Shapes> pencilArray = sl.getPencil();
				for (Shapes s : pencilArray) {
					s.drawShape(g);
				}
			}
		}
		
		//DRAWING SHADOW
		if (dwl.getStart() != null && dwl.getShadow() != null) {	
			graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.60f));
			graphSettings.setPaint(Color.BLACK);
			graphSettings.setStroke(new BasicStroke(dl.getThickness()));
			if (dl.getSelector() == 1){
				graphSettings.drawLine(dwl.getStart().x, dwl.getStart().y, dwl.getShadow().x, dwl.getShadow().y);
			} else if (dl.getSelector() == 2){
				graphSettings.draw(DrawRectangle.drawRect(dwl.getStart(), dwl.getShadow()));
			} else if (dl.getSelector() == 3){
				graphSettings.draw(DrawOval.drawOval(dwl.getStart(), dwl.getShadow()));
			} else if (dl.getSelector() == 5) {
				graphSettings.setStroke(new BasicStroke(2));
				graphSettings.draw(DrawRectangle.drawRect(dwl.getStart(), dwl.getShadow()));
			} else if (dl.getSelector() == 6) {
				areaSelection();
			} else if (dl.getSelector() == 9) {
				graphSettings.draw(DrawTriangle.drawTriangle(dwl.getStart(), dwl.getShadow()));
			} else if (dl.getSelector() == 10) {
				areaSelection();
			}
		}
	}

	private void areaSelection() {
		graphSettings.setStroke(new BasicStroke(3));
		graphSettings.draw(DrawRectangle.drawRect(dwl.getStart(), dwl.getShadow()));
		graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f));
		graphSettings.setPaint(Color.DARK_GRAY);
		graphSettings.fill(DrawRectangle.drawRect(dwl.getStart(), dwl.getShadow()));
	}
}
