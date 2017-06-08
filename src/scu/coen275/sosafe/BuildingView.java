package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
		formElePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formElePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		formElePanel.setBorder(BorderFactory.createTitledBorder("<html><font color = #bbfaaf>Installing and activating the sensors</font></html>"));
		simulationPanel = new JPanel();
		BoxLayout simulationLayout = new BoxLayout(simulationPanel, BoxLayout.X_AXIS);
		simulationPanel.setLayout(new FlowLayout());
		roomsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		formPanel.setBackground(new Color(48, 90, 113));
		ImageIcon image = new ImageIcon("img/building.png");
		JLabel picLabel = new JLabel(image);
		formPanel.add(picLabel);
		
		simulationPanel.setBorder(BorderFactory.createTitledBorder("<html><font color = #ffffff>Simulation For Demo</font></html>"));
      
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
		jBox.setPreferredSize(new Dimension(90,45));
		jBox.setForeground(Color.red);
		jBox.setBackground(Color.WHITE);
		
		formPanel.add(tempSensorChkBox);
		tempSensorChkBox.setPreferredSize(new Dimension(120, 45));
		tempSensorChkBox.setFont(new Font("Serif", Font.BOLD, 16));
		tempSensorChkBox.setBackground(Color.white);
		tempSensorChkBox.setForeground(Color.DARK_GRAY);
		
		
		formPanel.add(motionSensorChkBox);
		motionSensorChkBox.setPreferredSize(new Dimension(120, 45));
		motionSensorChkBox.setFont(new Font("Serif", Font.BOLD, 16));
		motionSensorChkBox.setBackground(Color.white);
		motionSensorChkBox.setForeground(Color.DARK_GRAY);
		
		JButton bSave = new JButton("Save");
		bSave.setForeground(Color.white);
		bSave.setFont(new Font("Serif", Font.BOLD, 18));
		bSave.setPreferredSize(new Dimension(90,45));
		bSave.setBackground(Color.red);
		
		bSave.setActionCommand("save");
		bSave.addActionListener(new SaveClickListener(twoColPanel, jBox, tempSensorChkBox, motionSensorChkBox));
		jBox.addItemListener(new ItemClickListener(tempSensorChkBox,motionSensorChkBox));
		formPanel.add(bSave);	
		formElePanel.add(formPanel);
		twoColPanel.add(roomsPanel);
		
		if(flag) {
			formPanel.setVisible(false);
		}
		ImageIcon img = new ImageIcon("img/fire.png");
		JButton bFireIn = new JButton("FireIn",img);
		ImageIcon img1 = new ImageIcon("img/emergency.png");
		JButton bBreakIn = new JButton("BreakIn",img1);
		
		ComboBoxGen simBox = new  ComboBoxGen();
		JComboBox sBox = simBox.createCustomCombo(roomsArr);
		sBox.setPreferredSize(new Dimension(90,45));
		sBox.setForeground(Color.red);
		sBox.setBackground(Color.WHITE);
		
		bFireIn.setActionCommand("fire");
		bFireIn.addActionListener(new FireInListener(twoColPanel,sBox));
		bFireIn.setForeground(Color.DARK_GRAY);
		bFireIn.setBackground(Color.white);
		bFireIn.setPreferredSize(new Dimension(140, 45));
		bFireIn.setFont(new Font("Serif", Font.BOLD, 16));
		
		bBreakIn.setActionCommand("breakIn");
		bBreakIn.addActionListener(new BreakInListener(twoColPanel,sBox));
		bBreakIn.setForeground(Color.DARK_GRAY);
		bBreakIn.setBackground(Color.white);
		bBreakIn.setFont(new Font("Serif", Font.BOLD, 16));
		
		simulationPanel.setBackground(new Color(48, 90, 113));
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