/**
 * 
 */
package scu.coen275.client;
import javax.swing.*;

import scu.coen275.sosafe.Login;


public class MainWindow {
	public static void main(String[] args) {
		System.out.println("I m triggered to take necessary");
		Login login = new Login();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(1200, 1200);
		login.setVisible(true);
	}

}