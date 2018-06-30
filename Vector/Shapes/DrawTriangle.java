package Shapes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class DrawTriangle extends Shapes {

	private Color outlineColor;
	private Color fillColor;
	private int outlineThickness;
	private Shape shape;
	private Boolean selected;
	private Point start, end;
	private String description = "Triangle";
	private int rotation;
	
	public DrawTriangle(Color outline, Color fill, int thickness, Point start, Point end) {
		outlineColor = outline;
		fillColor = fill;
		outlineThickness = thickness;
		this.start = start;
		this.end = end;
		shape = drawTriangle(start, end);
		selected = false;
		rotation = 0;
	}
	
	//SHAPE
	public static Polygon drawTriangle(Point start, Point end){
		int x0 = (int)start.getX();
        int y0 = (int)start.getY();
        int x1 = (int)end.getX();
        int y1 = (int)end.getY();
		
		Point2D point2b = computeTipPoint(start, end);
	    int x2b = (int)point2b.getX();
	    int y2b = (int)point2b.getY();

        int xCoordb[] = {x0, x1, x2b};
        int yCoordb[] = {y0, y1, y2b};
		return new Polygon(xCoordb, yCoordb, 3);
	}
	private static Point2D computeTipPoint(Point2D p0, Point2D p1) {
	    double dx = p1.getX() - p0.getX();
	    double dy = p1.getY() - p0.getY();
	    double length = Math.sqrt(dx*dx+dy*dy);
	    double dirX = dx / length;
	    double dirY = dy / length;
	    double height = Math.sqrt(3)/2 * length;
	    double cx = p0.getX() + dx * 0.5;
	    double cy = p0.getY() + dy * 0.5;
	    double pDirX = -dirY;
	    double pDirY = dirX;
	    double rx = 0;
	    double ry = 0;
	    rx = cx - height * pDirX;
	    ry = cy - height * pDirY;
	    return new Point2D.Double(rx, ry);
	}
	private void updateShape(Point start, Point end) {
		shape = drawTriangle(start, end);
	}
   
	//SETTERS
	public void setStart(Point start) { this.start = start; }
	public void setEnd(Point end) { this.end = end; }
	public void setShape(Shape shape) { this.shape = shape;}
	public void setSelected(Boolean selected) { this.selected = selected; }
	public void setOutline(Color outline) { this.outlineColor = outline; }
	public void setFill(Color fill) { this.fillColor = fill; }
	public void setThickness(int thickness) { this.outlineThickness = thickness; }
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
		graphSettings.fill(drawTriangle(start, end));	
		graphSettings.setColor(outlineColor);
		graphSettings.draw(drawTriangle(start, end));
		updateShape(start, end);
		graphSettings.setTransform(oldXForm);
		return graphSettings;
	}

}
