
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
	JLabel pinLabel, headLabel, lineLabel;
	final JTextField  text;
	private JButton submitButton;

	public Login()
	{

		Container cn = getContentPane();
		cn.setLayout(new FlowLayout());
        cn.setBackground(Color.black);
        cn.add(Box.createVerticalStrut(650));

		
		panel=new JPanel(new FlowLayout());
		ImageIcon image = new ImageIcon("img/security.jpg");
		JLabel picLabel = new JLabel(image);
		
		
		headLabel = new JLabel("SoSafe Security System");
		headLabel.setFont(new Font("Cambria", Font.BOLD, 25));
		headLabel.setForeground(new Color(0, 12, 155));
		
		lineLabel = new JLabel("Enter the Pin to login into System");
		lineLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		lineLabel.setForeground(Color.DARK_GRAY);
		
		pinLabel = new JLabel("PIN:");
		pinLabel.setFont(new Font("Serif", Font.BOLD, 18));
		pinLabel.setForeground(new Color(0, 12, 155));
	
		text = new JPasswordField(10);
		text.setPreferredSize(new Dimension(80, 30));

		submitButton=new JButton("SUBMIT");
		submitButton.setBackground(Color.red);
		submitButton.setFont(new Font("Serif", Font.BOLD, 15));
		submitButton.setForeground(Color.WHITE);

		panel.add(headLabel);
		panel.add(picLabel);
		
	    panel.setPreferredSize(new Dimension(300, 400));
	    panel.add(lineLabel);
	    panel.add(new JLabel("                  "));
		panel.add(pinLabel);
		panel.add(text);
		panel.add(new JLabel("   "));
		panel.add(submitButton);
		add(panel,BorderLayout.CENTER);
		submitButton.addActionListener(this);
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
			//tabbedPane.addTab("Customer Profile",profile_icon,new JPanel());
			tabbedPane.addTab("Schedule",profile_icon,new GenScheduleWindow(m.getTwoColPanel(), m.getRoomsArray()).createProfileWindow());
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