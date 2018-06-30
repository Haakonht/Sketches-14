package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {
	
	public SplashScreen() {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    int w = 767;
	    int h = 493;
	    int x = (int) ((d.getWidth() - w) / 2.0);
	    int y = (int) ((d.getHeight() - h) / 2.0);
		splashImage();
		setBounds(x, y, w, h);
		setVisible(true);		
	}
	
	private void splashImage() {
		ImageIcon gif = new ImageIcon(getClass().getResource("/splash.gif"));
		JLabel splash = new JLabel(gif);
		add(splash);
	}	
	
}
