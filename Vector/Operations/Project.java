package Operations;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Shapes.ShapesLibrary;

public class Project {

	public void saveProject(LayerLibrary ll, DataLibrary dl) {
		try{
			FileOutputStream fos = null;
			fos = new FileOutputStream(dl.getPath());
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(ll);
			oos.close();
			fos.close();
		}
		catch(IOException ioe){
            ioe.printStackTrace();
        }
	}
	
	public void saveTabbedProject(TabLibrary tl, DataLibrary dl) {
		try{
			FileOutputStream fos = null;
			fos = new FileOutputStream(dl.getPath());
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(tl);
			oos.close();
			fos.close();
		}
		catch(IOException ioe){
            ioe.printStackTrace();
        }
	}
	
	public void openProject(LayerLibrary ll, DataLibrary dl) {
		LayerLibrary nll = new LayerLibrary(new ShapesLibrary());
		try{
			FileInputStream fis = null;
			fis = new FileInputStream(dl.getPath());
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			nll = (LayerLibrary) ois.readObject();
        
			ll.wipe();
			Color background = nll.getBackground();
			ll.setBackground(background);
			ArrayList<ShapesLibrary> layersArray = nll.getLayers();
			for (ShapesLibrary s : layersArray) {
				ll.setLayer(s);
			}
		
		}
		catch(IOException | ClassNotFoundException ioe){
			System.out.println("File not found");
        }
	}
	
	public void openTabbedProject(TabLibrary tl, DataLibrary dl) {
		TabLibrary ntl = new TabLibrary();
		try{
			FileInputStream fis = null;
			fis = new FileInputStream(dl.getPath());
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			ntl = (TabLibrary) ois.readObject();
        
			tl.wipe();
			ArrayList<LayerLibrary> tabsArray = ntl.getTabs();
			for (LayerLibrary ll : tabsArray) {
				tl.addTab(ll);
			}
		
		}
		catch(IOException | ClassNotFoundException ioe){
			System.out.println("File not found");
        }
	}
	
}
