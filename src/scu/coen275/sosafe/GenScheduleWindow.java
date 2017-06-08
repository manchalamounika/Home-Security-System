/**
 * 
 */
package scu.coen275.sosafe;

/**
 * @author mounika
 *
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

public class GenScheduleWindow extends JPanel{
	
	private JPanel profilePanel, schedulePanel;
	private ArrayList<Room> rL;
	private BoxLayout scheduleLayout;
	private JLabel startHr, startMin, endHr, endMin;
	private JPanel twoColPanel;


	public GenScheduleWindow(JPanel twoColPanel, ArrayList<Room> rL) {
		this.rL = rL;
		this.twoColPanel = twoColPanel;
		//this.createProfileWindow();
	}

	public JPanel createProfileWindow() {
		profilePanel = new JPanel(new FlowLayout());
		profilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		final JCheckBox tempSensorChkBox = new JCheckBox("Temperature");
		tempSensorChkBox.setName("temperature");
	    final JCheckBox motionSensorChkBox = new JCheckBox("Motion");
	    motionSensorChkBox.setName("motion");
	    
	    startHr = new JLabel("Start Hour");
	    startMin = new JLabel("Start Minute");
	    endHr = new JLabel("End Hour");
	    endMin = new JLabel("End Minute");
	    
	    schedulePanel = new JPanel();
	    scheduleLayout = new BoxLayout(schedulePanel, BoxLayout.Y_AXIS);
	    schedulePanel.setLayout(scheduleLayout);
	    
	    ComboBoxGen jc = new  ComboBoxGen();
	    ArrayList<String> hh = new ArrayList<String>();
	    for(int i = 0; i<24; i++) {
	    	String temp = i<=9?"0"+i: ""+i;
	    	hh.add(temp);
	    }
	    ArrayList<String> mm = new ArrayList<String>();
	    for(int j = 0; j<60; j=j+1) {
	    	String temp = j<=9?"0"+j: ""+j;
	    	 //temp =""+j;
	    	mm.add(temp);
	    }
	    JComboBox jBox = jc.createCustomCombo(rL);
	    ComboBoxGen jc1 = new  ComboBoxGen();
	    JComboBox hourBox = jc1.createGenCombo(hh);
	    
	    ComboBoxGen jc2 = new  ComboBoxGen();
	    JComboBox ehourBox = jc2.createGenCombo(hh);
	    
	    ComboBoxGen jc3 = new  ComboBoxGen();
	    JComboBox minBox = jc3.createGenCombo(mm);
	    
	    ComboBoxGen jc4 = new  ComboBoxGen();
	    JComboBox eminBox = jc4.createGenCombo(mm);
	    
	    schedulePanel.add(jBox);
	    schedulePanel.add(startHr);
	    schedulePanel.add(hourBox);
	    schedulePanel.add(startMin);
	    schedulePanel.add(minBox);
	    schedulePanel.add(endHr);
	    schedulePanel.add(ehourBox);
	    schedulePanel.add(endMin);
	    schedulePanel.add(eminBox);
		
		
	    schedulePanel.add(tempSensorChkBox);
	    schedulePanel.add(motionSensorChkBox);
		JButton scheduleSave = new JButton("Save");
		scheduleSave.setActionCommand("save");
		scheduleSave.addActionListener(new ScheduleSetUpListener(this.twoColPanel,profilePanel, jBox, tempSensorChkBox, motionSensorChkBox,hourBox,minBox, ehourBox, eminBox));
		
		//bSave.addActionListener(new SaveClickListener(twoColPanel, jBox, tempSensorChkBox, motionSensorChkBox));
		jBox.addItemListener(new ItemClickListener(tempSensorChkBox,motionSensorChkBox));
		schedulePanel.add(scheduleSave);	
		profilePanel.add(schedulePanel);
		profilePanel.setVisible(true);
		return profilePanel;
		
	}
}
