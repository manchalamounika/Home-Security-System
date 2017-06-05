package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class BuildingView {
	private JFrame mainFrame;
	private JPanel twoColPanel, roomsPanel, formPanel,formElePanel;
	public BuildingView(JFrame mainFrame) {
		this.mainFrame =mainFrame;
		//this.createWindow();
	}

	public JPanel createWindow(boolean flag) {
		//JSONObject jo = new JSONObject()
		twoColPanel = new JPanel(new GridLayout(1,2));
		twoColPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		roomsPanel = new JPanel(new GridLayout(5,1));
		formPanel = new JPanel(new FlowLayout());
		formElePanel = new JPanel(new GridLayout(1,1));
		
		roomsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      
		//roomOuterPanel.add(roomPanel);
		ArrayList<Room> roomsArr = new ArrayList<Room>();
		roomsArr.add(new Room(1));
		roomsArr.add(new Room(2));
		roomsArr.add(new Room(3));
		roomsArr.add(new Room(4));
		roomsArr.add(new Room(5));
		roomsArr.add(new Room(6));

		for (int i = 0; i < roomsArr.size(); i++) {
			roomsPanel.add((roomsArr.get(i)).createRoom());
		}
		
		//adding combobox to form
		final JCheckBox tempSensorChkBox = new JCheckBox("Temperature");
	    final JCheckBox motionSensorChkBox = new JCheckBox("Motion");
	    ComboBoxGen jc = new  ComboBoxGen();
	    JComboBox jBox = jc.createCustomCombo(roomsArr);
		formPanel.add(jBox);
		
		formPanel.add(tempSensorChkBox);
		formPanel.add(motionSensorChkBox);
		JButton bSave = new JButton("Save");
		bSave.setActionCommand("save");
		bSave.addActionListener(new SaveClickListener(twoColPanel, jBox, tempSensorChkBox, motionSensorChkBox));
		formPanel.add(bSave);
		
		//roomsPanel.add((new Room(2)).createRoom());
		
	
		twoColPanel.add(roomsPanel);
		twoColPanel.add(formPanel);
		if(flag) {
			formPanel.setVisible(false);
		}
	
		
		//https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
		return twoColPanel;
	}

}
