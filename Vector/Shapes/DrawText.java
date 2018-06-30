package Shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

@SuppressWarnings("serial")
public class DrawText extends Shapes {

	private Color outline;
	private String text;
	private Point start;
	private Font font;
	private Shape shape;
	private Boolean selected = false;
	private String description = "Text";
	private int rotation;
	
	public DrawText(Color outline, String text, Point start, Font font, Shape shape) {
		this.outline = outline;
		this.text = text;
		this.start = start;
		this.font = font;
		this.shape = shape;
		this.rotation = 0;
	}
	
	//SETTERS
	public void setStart(Point start) { this.start = start; }
	public void setEnd(Point end) { /* TODO Auto-generated method stub */ }
	public void setShape(Shape shape) { /* TODO Auto-generated method stub */ }
	public void setSelected(Boolean selected) { this.selected = selected; }
	public void setOutline(Color outline) { this.outline = outline; }
	public void setFill(Color fill) { this.outline = fill; }
	public void setThickness(int thickness) { /* TODO Auto-generated method stub */ }
	public void setRotation(int rotation) { this.rotation = rotation; }
	
	//GETTERS
	public Color getOutline() { return outline; }
	public Color getFill() { return outline; }
	public int getThickness() { return 0; }
	public Shape getShape() { return shape; }
	public Boolean getSelected() { return selected; }
	public Point getStart() { return start; }
	public String getDescription() { return description; }
	public Point getEnd() { return start; }
	public int getRotation() { return rotation; }

	//GRAPHICS
	public Graphics2D drawShape(Graphics g) {
		Graphics2D graphSettings = (Graphics2D)g;
		graphSettings.setFont(font);
		graphSettings.setColor(outline);
		graphSettings.rotate(Math.toRadians(rotation), start.getX() + (shape.getBounds2D().getWidth() / 2), start.getY() + (shape.getBounds2D().getHeight() / 2));
		graphSettings.drawString(text, start.x, start.y);
		return graphSettings;
	}

}
