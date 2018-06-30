package Shapes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class DrawRectangle extends Shapes {

	private Color outlineColor;
	private Color fillColor;
	private int outlineThickness;
	private Shape shape;
	private Boolean selected;
	private Point start, end;
	private String description = "Rectangle";
	private int rotation;
	
	public DrawRectangle(Color outline, Color fill, int thickness, Point start, Point end) {
		outlineColor = outline;
		fillColor = fill;
		outlineThickness = thickness;
		this.start = start;
		this.end = end;
		shape = drawRect(start, end);
		selected = false;
		rotation = 0;
	}
	
	//SHAPE
	public static Rectangle2D.Float drawRect(Point start, Point end){
		int x = Math.min(start.x, end.x);
		int y = Math.min(start.y, end.y);
		int width = Math.abs(start.x - end.x);
		int height = Math.abs(start.y - end.y);
		return new Rectangle2D.Float(x, y, width, height);
	}
	private void updateShape(Point start, Point end) {
		shape = drawRect(start, end);
	}	

	//SETTERS
	public void setOutline(Color outline) { outlineColor = outline; }
	public void setFill(Color fill) { fillColor = fill; }
	public void setThickness(int thickness) { outlineThickness = thickness; }
	public void setShape(Shape shape) { this.shape = shape; }
	public void setSelected(Boolean selected) { this.selected = selected; }
	public void setStart(Point start) { this.start = start; }
	public void setEnd(Point end) { this.end = end; }
	public void setRotation(int rotation) { this.rotation = rotation; }
	
	//GETTERS
	public Color getOutline() { return outlineColor; }
	public Color getFill() { return fillColor; }
	public int getThickness() { return outlineThickness; }
	public Shape getShape() { return shape; }
	public Boolean getSelected() { return selected; }
	public Point getStart() { return start; }
	public String getDescription() { return description; }
	public Point getEnd() { return end; }
	public int getRotation() { return rotation; }
	
	//GRAPHICS
	public Graphics2D drawShape(Graphics g) {
		Graphics2D graphSettings = (Graphics2D)g;
		AffineTransform oldXForm = graphSettings.getTransform();
		graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		graphSettings.setStroke(new BasicStroke(outlineThickness));
		graphSettings.rotate(Math.toRadians(rotation), start.getX() + (shape.getBounds2D().getWidth() / 2), start.getY() + (shape.getBounds2D().getHeight() / 2));
		graphSettings.setColor(fillColor);
		graphSettings.fill(drawRect(start, end));	
		graphSettings.setColor(outlineColor);
		graphSettings.draw(drawRect(start, end));
		updateShape(start, end);
		graphSettings.setTransform(oldXForm);
		return graphSettings;
	}

}
