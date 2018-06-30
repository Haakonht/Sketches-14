package Operations;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GUI.DrawingComponent;
import Libraries.DataLibrary;

public class Image {

	public void saveImage(DataLibrary dl, DrawingComponent dc) {
		BufferedImage im = new BufferedImage(dc.getWidth(), dc.getHeight(), BufferedImage.TYPE_INT_ARGB);
		dc.paint(im.getGraphics());
		try {
			ImageIO.write(im, "PNG", new File(dl.getPath()));
		} catch (IOException e) {
			System.out.println("Couldn't write to file");
		}
	}
	
	public void saveSelection(DataLibrary dl, DrawingComponent dc, Rectangle rect) {
	    try {
	    	BufferedImage im = new BufferedImage(dc.getWidth(), dc.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    	dc.paint(im.getGraphics());
	    	BufferedImage subIm = im.getSubimage(rect.x, rect.y, rect.width, rect.height); 
	    	ImageIO.write(subIm, "PNG", new File(dl.getPath()));
	    } 
	    catch (IOException e) {
	    	System.out.println("Couldn't write to file");
	    }
	  }
}
