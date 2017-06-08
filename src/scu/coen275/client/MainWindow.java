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
		login.setSize(1500, 1000);
		login.setVisible(true);
	}

}