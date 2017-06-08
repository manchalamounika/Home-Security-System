package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class BuildingView {	
	private JPanel twoColPanel, roomsPanel, formPanel,formElePanel, simulationPanel;
	private JFrame mainFrame;
	private ArrayList<Room> roomsArr;
	public BuildingView(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		//this.createWindow();
	}
	public BuildingView() {
		//this.createWindow();
	}

	public ArrayList<Room>  getRoomsArray() {
		return this.roomsArr;
	}
	public JPanel createWindow(boolean flag) {
		//JSONObject jo = new JSONObject()
		twoColPanel = new JPanel(new GridLayout(1,2));
		twoColPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		roomsPanel = new JPanel(new GridLayout(3,1));
		formPanel = new JPanel(new FlowLayout());
		formElePanel = new JPanel(new GridLayout(2,1));
		simulationPanel = new JPanel();
		BoxLayout simulationLayout = new BoxLayout(simulationPanel, BoxLayout.X_AXIS);
		simulationPanel.setLayout(new FlowLayout());
		roomsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		simulationPanel.setBorder(BorderFactory.createTitledBorder("Simulation For Demo"));
      
		//roomOuterPanel.add(roomPanel);
		roomsArr = new ArrayList<Room>();
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
		jBox.addItemListener(new ItemClickListener(tempSensorChkBox,motionSensorChkBox));
		formPanel.add(bSave);	
		formElePanel.add(formPanel);
		twoColPanel.add(roomsPanel);
		
		if(flag) {
			formPanel.setVisible(false);
		}
		//simulationPanel = new JPanel(new GridLayout(1,1));
		JButton bFireIn = new JButton("FireIn");
		JButton bBreakIn = new JButton("BreakIn");
		ComboBoxGen simBox = new  ComboBoxGen();
		JComboBox sBox = simBox.createCustomCombo(roomsArr);
		bFireIn.setActionCommand("fire");
		bFireIn.addActionListener(new FireInListener(twoColPanel,sBox));
		
		bBreakIn.setActionCommand("breakIn");
		bBreakIn.addActionListener(new BreakInListener(twoColPanel,sBox));
		
		simulationPanel.add(sBox);
		simulationPanel.add(bFireIn);
		simulationPanel.add(bBreakIn);
		formElePanel.add(simulationPanel);
		twoColPanel.add(formElePanel);
		//https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
		return twoColPanel;
	}
	public JPanel getTwoColPanel() {
		return this.twoColPanel;
	}
	
}