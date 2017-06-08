package scu.coen275.sosafe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author mounika
 *
 */
public class FireInListener implements ActionListener {
	JComboBox simBox;
	JPanel twoColPanel,pp;
	private ArrayList<Component> components1;
	private Timer timer;

	public FireInListener(JPanel twoColPanel, JComboBox simBox) {
		this.simBox =simBox;
		this.twoColPanel = twoColPanel;
		components1 = new ArrayList<Component>();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Simulation-------------------->" +simBox.getSelectedItem().toString());
		listAllComponentsIn(this.twoColPanel);
		JPanel roomPanelA = findComponent(simBox.getSelectedItem().toString());
		System.out.println("roomPanelA:::::::"+roomPanelA);
		Icon myImgIcon = new ImageIcon("res/fire_sprinkler.gif");
		Icon myImgIcon1 = new ImageIcon("res/flames.gif");
		JLabel imageLbl = new JLabel(myImgIcon);
		Boolean sensor_enabled = Boolean.parseBoolean(findFromPropertiesFile("temperature_sensor_"+simBox.getSelectedItem().toString()));
		if(sensor_enabled) {

			Billing b= new Billing();
			b.fireDetected("A fire has occurred on");
			roomPanelA.add(imageLbl, BorderLayout.CENTER);
			roomPanelA.revalidate();
			roomPanelA.repaint();
			
			JOptionPane.showMessageDialog(twoColPanel,"A call is being placed to Police department");
			//http://makeagif.com/gif/fire-sprinkler-test-with-water-_AI68u
			Timer SimpleTimer = new Timer(5500, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					imageLbl.setVisible(false);
				}
			});
			SimpleTimer.start();

		}
		else
		{
			imageLbl.setIcon(myImgIcon1);

			roomPanelA.add(imageLbl, BorderLayout.CENTER);
			roomPanelA.revalidate();
			roomPanelA.repaint();
		}


	}

	public void listAllComponentsIn(Container parent)
	{
		for (Component c : parent.getComponents())
		{
			if(c!=null && c.getName()!=null && c instanceof JPanel) {
				components1.add(c);	
			}
			if (c instanceof Container)
				listAllComponentsIn((Container)c);
		}
	}

	public JPanel findComponent(String panelName)
	{
		JPanel temp = null;
		for(int i =0; i<components1.size(); i++) {
			if(components1.get(i).getName().equals(panelName)){
				temp = (JPanel) components1.get(i);
			}
		}
		return temp;

	}


	private  String findFromPropertiesFile(String propertyNm) {
		FileInputStream in =null;
		String pflag =null;
		Properties s_state = new Properties();
		try {
			in = new FileInputStream("res/sensor_states.properties");
			s_state.load(in);
			in.close();
			if(s_state.getProperty(propertyNm) != null)
				pflag = s_state.getProperty(propertyNm);
		}
		catch(IOException e){
		}
		System.out.println("FireInListenerrrrrrr"+pflag);
		return pflag;
		
	}
}