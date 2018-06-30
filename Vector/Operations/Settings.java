package Operations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Libraries.UserInterfaceSettings;

public class Settings {
	
	public void serializeSettings(UserInterfaceSettings uis) {
		try{
			FileOutputStream fos = null;
			fos = new FileOutputStream("Settings.ini");
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(uis);
			oos.close();
			fos.close();
		}
		catch(IOException ioe){
	        ioe.printStackTrace();
	    }
	}

	public UserInterfaceSettings deserializeSettings() {
		UserInterfaceSettings nuis = new UserInterfaceSettings();
		try{
			FileInputStream fis = null;
			fis = new FileInputStream("Settings.ini");
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			nuis = (UserInterfaceSettings) ois.readObject();	
        }
		catch (IOException | ClassNotFoundException ioe){
			System.out.println("File not found");
        }
		return nuis;
	}	
	
}
