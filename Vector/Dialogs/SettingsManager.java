package Dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import GUI.MainWindow;
import Libraries.DataLibrary;
import Libraries.LayerLibrary;
import Libraries.TabLibrary;
import Libraries.UserInterfaceSettings;
import Shapes.ShapesLibrary;

@SuppressWarnings("serial")
public class SettingsManager extends JDialog {
	
	private Boolean tabsActive;
	private JCheckBox tabs;
	private JCheckBox splash;
	private JTextField splashTimer;
	private JCheckBox autoSave;
	private JTextField autoSaveTimer;
	private JCheckBox statusBar;
	private JTextField toolTimer;
	private JTextField coordTimer;
	
	
	public SettingsManager(UserInterfaceSettings uis, MainWindow mw, LayerLibrary ll, TabLibrary tl, DataLibrary dl) {
		setSize(270, 230);
		setLocationRelativeTo(null);
		setTitle("Settings");
		setResizable(false);
		tabsActive = uis.useTabs();
		JTabbedPane jtb = new JTabbedPane();
		jtb.addTab("General", general(uis, mw, dl, ll, tl));
		jtb.addTab("Status bar", statusBar(uis, mw, dl, ll, tl));
		jtb.addTab("Auto save", autoSave(uis, mw, dl, ll, tl));
		add(jtb);
		setVisible(true);
	}

	private JPanel general(UserInterfaceSettings uis, MainWindow mw, DataLibrary dl, LayerLibrary ll, TabLibrary tl) {
		JPanel panel = new JPanel();
		
		JPanel tabsPanel = new JPanel();
		JLabel tabsLabel = new JLabel("BETA - Use tabs? (Will restart program)");
		tabs = new JCheckBox();
		tabs.setSelected(uis.useTabs());
		
		JPanel splashPanel = new JPanel();
		JLabel splashLabel = new JLabel("Splash screen ");
		splash = new JCheckBox();
		splash.setSelected(uis.getSplash());
		
		JPanel splashTimePanel = new JPanel();
		JLabel splashTimeLabel = new JLabel("Splash time ");
		splashTimer = new JTextField(""+uis.getSplashTime());
		JLabel milliseconds = new JLabel("ms");
		
		tabsPanel.add(tabsLabel);
		tabsPanel.add(tabs);
		
		splashPanel.add(splashLabel);
		splashPanel.add(splash);
		
		splashTimePanel.add(splashTimeLabel);
		splashTimePanel.add(splashTimer);
		splashTimePanel.add(milliseconds);
		
		panel.add(tabsPanel);
		panel.add(splashPanel);
		panel.add(splashTimePanel);
		panel.add(buttons(uis, mw, dl, ll, tl));
		
		return panel;
	}

	private JPanel statusBar(UserInterfaceSettings uis, MainWindow mw, DataLibrary dl, LayerLibrary ll, TabLibrary tl) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		JPanel statusBarPanel = new JPanel();
		JLabel statusBarLabel = new JLabel("Status bar ");
		statusBar = new JCheckBox();
		statusBar.setSelected(uis.getStatus());
		
		JPanel toolTimerPanel = new JPanel();
		JLabel toolTimerLabel = new JLabel("Autosave timer ");
		toolTimer = new JTextField(""+uis.getToolTimer());
		JLabel toolMilliseconds = new JLabel("ms");
		
		JPanel coordTimerPanel = new JPanel();
		JLabel coordTimerLabel = new JLabel("Autosave timer ");
		coordTimer = new JTextField(""+uis.getCoordTimer());
		JLabel milliseconds = new JLabel("ms");
		
		statusBarPanel.add(statusBarLabel);
		statusBarPanel.add(statusBar);
		
		toolTimerPanel.add(toolTimerLabel);
		toolTimerPanel.add(toolTimer);
		toolTimerPanel.add(toolMilliseconds);
		
		coordTimerPanel.add(coordTimerLabel);
		coordTimerPanel.add(coordTimer);
		coordTimerPanel.add(milliseconds);
		
		panel.add(statusBarPanel);
		panel.add(toolTimerPanel);
		panel.add(coordTimerPanel);
		panel.add(buttons(uis, mw, dl, ll, tl));
		
		return panel;
	}

	private JPanel autoSave(UserInterfaceSettings uis, MainWindow mw, DataLibrary dl, LayerLibrary ll, TabLibrary tl) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		JPanel autoSavePanel = new JPanel();
		JLabel autoSaveLabel = new JLabel("Autosave ");
		autoSave = new JCheckBox();
		autoSave.setSelected(uis.getAutoSave());
		
		JPanel autoSaveTimerPanel = new JPanel();
		JLabel autoSaveTimerLabel = new JLabel("Autosave timer ");
		autoSaveTimer = new JTextField(""+uis.getAutoSaveTimer()/1000);
		JLabel seconds = new JLabel("s");
		
		autoSavePanel.add(autoSaveLabel);
		autoSavePanel.add(autoSave);
		
		autoSaveTimerPanel.add(autoSaveTimerLabel);
		autoSaveTimerPanel.add(autoSaveTimer);
		autoSaveTimerPanel.add(seconds);
		
		panel.add(autoSavePanel);
		panel.add(autoSaveTimerPanel);
		panel.add(buttons(uis, mw, dl, ll, tl));
		return panel;
	}
	
	private JPanel buttons(UserInterfaceSettings uis, MainWindow mw, DataLibrary dl, LayerLibrary ll, TabLibrary tl) {
		JPanel buttonPanel = new JPanel();
		
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uis.setTabSettings(tabs.isSelected());
				uis.setStatus(statusBar.isSelected());
				uis.setToolTimer(Integer.parseInt(toolTimer.getText()));
				uis.setCoordTimer(Integer.parseInt(coordTimer.getText()));
				uis.setAutoSave(autoSave.isSelected());
				int ast = Integer.parseInt(autoSaveTimer.getText());
				uis.setAutoSaveTimer(ast*1000);
				uis.setSplash(splash.isSelected());
				uis.setSplashTime(Integer.parseInt(splashTimer.getText()));
				dispose();
				mw.dispose();
				if (tabsActive) {
					if (tabs.isSelected()) {
						new MainWindow(uis, dl, null, tl);
					}
					else {
						new MainWindow(uis, dl, new LayerLibrary(new ShapesLibrary()), null);
					}
				}
				else {
					if (tabs.isSelected()) {
						new MainWindow(uis, dl, null, new TabLibrary());
					}
					else {
						new MainWindow(uis, dl, ll, null);
					}
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
		
		return buttonPanel;
	}

}
