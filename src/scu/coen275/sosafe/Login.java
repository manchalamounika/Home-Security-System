
package scu.coen275.sosafe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener
{
	private JFrame loginFrame;
	JButton SUBMIT;
	JPanel panel, infoPanel;
	JLabel label;
	final JTextField  text;

	public Login()
	{

		Container cn = getContentPane();
		cn.setLayout(new FlowLayout());

		label = new JLabel();
		label.setText("PIN:");
		text = new JPasswordField(5);

		SUBMIT=new JButton("SUBMIT");

		panel=new JPanel(new GridLayout(3,1));
		panel.add(label);
		panel.add(text);
		panel.add(SUBMIT);
		add(panel,BorderLayout.CENTER);
		SUBMIT.addActionListener(this);
		setTitle("LOGIN SCREEN");
	}

	public void actionPerformed(ActionEvent ae)
	{
		String value2=text.getText();
		if (value2.equals("12345")) {


			JFrame mainFrame = new JFrame("Room Layout");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setSize(1200, 1200);
			JTabbedPane tabbedPane = new JTabbedPane();
			BuildingView m = new BuildingView(mainFrame);

			BillGenerator b = new BillGenerator(mainFrame);

			ImageIcon profile_icon = new ImageIcon("res/user_profile");
			tabbedPane.addTab("BuildingLayout",profile_icon,m.createWindow(false));
			tabbedPane.addTab("Customer Profile",profile_icon,new JPanel());

			JPanel pp =b.createBillWindow();
			tabbedPane.addTab("BillingInfo",profile_icon,pp);
			ChangeListener changeListener = new ChangeListener() {
			      public void stateChanged(ChangeEvent changeEvent) {
			        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
			        int index = sourceTabbedPane.getSelectedIndex();
			        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
			        pp.removeAll();
			        pp.revalidate();
			        pp.repaint();
			        BillGenerator b1 = new BillGenerator(mainFrame);
			        pp.add(b1.createBillWindow());
			        pp.revalidate();
			        pp.repaint();
			      }
			    };
			tabbedPane.addChangeListener(changeListener);  
			mainFrame.add(tabbedPane);
	
			mainFrame.setVisible(true);
		}
		else{
			System.out.println("enter the valid Pin");
			JOptionPane.showMessageDialog(this,"Incorrect Pin",
					"Error",JOptionPane.ERROR_MESSAGE);
		}

	}


}