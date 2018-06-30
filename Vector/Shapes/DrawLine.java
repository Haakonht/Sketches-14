package Shapes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

@SuppressWarnings("serial")
public class DrawLine extends Shapes {

	private Color outlineColor;
	private int outlineThickness;
	private Shape shape;
	private Boolean selected;
	private Point start, end;
	private String description = "Line";
	
	public DrawLine(Color outline, int thickness, Point start, Point end) {
		outlineColor = outline;
		outlineThickness = thickness;
		this.start = start;
		this.end = end;
		shape = drawLine(start, end);
		selected = false;
	}
	
	//SHAPE
	public Line2D.Float drawLine(Point start, Point end){
		return new Line2D.Float(start.x, start.y, end.x, end.y);
	}
	private void updateShape(Point start, Point end) {
		shape = drawLine(start, end);
	}

	//SETTERS
	public void setOutline(Color outline) { outlineColor = outline; }
	public void setThickness(int thickness) { outlineThickness = thickness; }
	public void setShape(Shape shape) { this.shape = shape; }
	public void setSelected(Boolean selected) { this.selected = selected; }
	public void setFill(Color fill) { outlineColor = fill; }
	public void setStart(Point start) {	this.start = start; }
	public void setEnd(Point end) { this.end = end; } 
	public void setRotation(int rotation) { /* TODO Auto-generated method stub */ }
	
	//GETTERS
	public Color getOutline() { return outlineColor; }
	public Color getFill() { return outlineColor; }
	public int getThickness() { return outlineThickness; }
	public Shape getShape() { return shape; }
	public Boolean getSelected() { return selected; }
	public Point getStart() { return start; }
	public String getDescription() { return description; }
	public Point getEnd() { return end; }
	public int getRotation() { return 0; }
	
	//GRAPHICS
	public Graphics2D drawShape(Graphics g) {
		Graphics2D graphSettings = (Graphics2D)g;
		graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		graphSettings.setStroke(new BasicStroke(outlineThickness));
		graphSettings.setColor(outlineColor);
		graphSettings.draw(drawLine(start, end));
		updateShape(start, end);
		return graphSettings;
	}
	
}
