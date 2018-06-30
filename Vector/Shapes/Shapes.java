package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Shapes implements Serializable {

	public abstract void setStart(Point start);
	public abstract void setEnd(Point end);
	public abstract void setShape(Shape shape);
	public abstract void setSelected(Boolean selected);
	public abstract void setOutline(Color outline);
	public abstract void setFill(Color fill);
	public abstract void setThickness(int thickness);
	public abstract void setRotation(int rotation);
	
	public abstract Color getOutline();
	public abstract Color getFill();
	public abstract int getThickness();
	public abstract Shape getShape();
	public abstract Boolean getSelected();
	public abstract Point getStart();
	public abstract String getDescription();
	public abstract Point getEnd();
	public abstract int getRotation();
	
	public abstract Graphics2D drawShape(Graphics g);
}
