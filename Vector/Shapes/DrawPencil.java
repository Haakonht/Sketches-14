package Shapes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class DrawPencil extends Shapes {

	private Color outline;
	private int thickness;
	private Point start;
	private Shape shape;
	private Boolean selected;
	private String description = "Pencil";
	private int rotation;
	
	public DrawPencil(Color outline, int thickness, Point start) {
		this.outline = outline;
		this.thickness = thickness;
		this.start = start;
		shape = drawPencil(start, thickness, thickness);
		selected = false;
		rotation = 0;
	}
	
	//SHAPE
	public Ellipse2D.Float drawPencil(Point start, int pencilStrokeWidth, int pencilStrokeHeight){
		return new Ellipse2D.Float(start.x, start.y, pencilStrokeWidth, pencilStrokeHeight);
	}
	private void updateShape(Point start) {
		shape = drawPencil(start, thickness, thickness);
	}
	
	//SETTERS
	public void setStart(Point start) { this.start = start; }
	public void setEnd(Point end) { this.start = end; }
	public void setShape(Shape shape) { this.shape = shape;}
	public void setSelected(Boolean selected) { this.selected = selected; }
	public void setOutline(Color outline) { this.outline = outline; }
	public void setFill(Color fill) { this.outline = fill; }
	public void setThickness(int thickness) { this.thickness = thickness; }
	public void setRotation(int rotation) { this.rotation = rotation; }

	//GETTERS
	public Color getOutline() { return outline; }
	public Color getFill() { return outline; }
	public int getThickness() { return thickness; }
	public Shape getShape() { return shape;}
	public Boolean getSelected() { return selected; }
	public Point getStart() { return start; }
	public String getDescription() { return description; }
	public Point getEnd() { return start; }
	public int getRotation() { return rotation; }

	@Override
	public Graphics2D drawShape(Graphics g) {
		Graphics2D graphSettings = (Graphics2D)g;
		graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		graphSettings.setColor(outline);
		graphSettings.fill(drawPencil(start, thickness, thickness));
		updateShape(start);
		return graphSettings;
	}

}
