package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author mounika
 *
 */
public class CustomerProfile extends JPanel{
	
	private JPanel profilePanel;
	private ArrayList<Room> rL;

	public CustomerProfile(ArrayList<Room> rL) {
		this.rL = rL;
		//this.createProfileWindow();
	}

	public JPanel createProfileWindow() {
		profilePanel = new JPanel(new FlowLayout());
		profilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		final JCheckBox tempSensorChkBox = new JCheckBox("Temperature");
	    final JCheckBox motionSensorChkBox = new JCheckBox("Motion");
	    ComboBoxGen jc = new  ComboBoxGen();
	    JComboBox jBox = jc.createCustomCombo(rL);
	    profilePanel.add(jBox);
		
		
	    profilePanel.add(tempSensorChkBox);
	    profilePanel.add(motionSensorChkBox);
		JButton bSave = new JButton("Save");
		bSave.setActionCommand("save");
		//bSave.addActionListener(new SaveClickListener(twoColPanel, jBox, tempSensorChkBox, motionSensorChkBox));
		jBox.addItemListener(new ItemClickListener(tempSensorChkBox,motionSensorChkBox));
		profilePanel.add(bSave);	
		profilePanel.setVisible(true);
		return profilePanel;
		
	}
}
