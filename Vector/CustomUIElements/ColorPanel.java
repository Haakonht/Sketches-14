package CustomUIElements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorPanel extends JPanel {

	private Color color;
	
	public ColorPanel(Color color) {
		this.color = color;
		setOpaque(false);
		setVisible(true);
	}
	
	public void setBackground(Color color) {
		this.color = color;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
}
