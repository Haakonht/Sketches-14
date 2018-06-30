package Dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.TabComponent;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class TabNamer extends JDialog {

	public TabNamer(TabLibrary tl, TabComponent tc) {
		setSize(250, 130);
		setTitle(" Set tab title");
		setLocationRelativeTo(null);
		setResizable(false);
        
		JLabel info = new JLabel("Tab title: ");
		JTextField jtf = new JTextField();
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tl.getTabs().add(new LayerLibrary(jtf.getText(), new ShapesLibrary()));
				tc.refresh();
				dispose();
			}
		});
		
		add(info, BorderLayout.WEST);
		add(jtf, BorderLayout.CENTER);
		add(confirm, BorderLayout.EAST);
		
        setVisible(true);
		}
	
}
