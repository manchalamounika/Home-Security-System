/**
 * 
 */
package scu.coen275.client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import scu.coen275.sosafe.*;

/**
 * @author mounika
 *
 */
public class MainWindow {
	public JFrame mainFrame;
	public MainWindow(){
		
		mainFrame = new JFrame("Room Layout");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1000, 1000);	
	}

	public static void main(String[] args) {
		System.out.println("I m triggered to take necessary");
		MainWindow mw = new MainWindow();
		JTabbedPane tabbedPane = new JTabbedPane();
		BuildingView m = new BuildingView(mw.mainFrame);
		BuildingView simulation = new BuildingView(mw.mainFrame);
		
		ImageIcon profile_icon = new ImageIcon("res/user_profile.png");
		tabbedPane.addTab("BuildingLayout",profile_icon,m.createWindow(false));
		tabbedPane.addTab("Customer Profile",profile_icon,new JPanel());
		tabbedPane.addTab("BillingInfo",profile_icon,new BillGenerator());
		tabbedPane.addTab("Simulation",profile_icon,simulation.createWindow(true));
		mw.mainFrame.add(tabbedPane);
		mw.mainFrame.setVisible(true);
		
		
	}

}