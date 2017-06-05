package scu.coen275.sosafe;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Room extends JPanel{


	private JPanel roomOuterPanel, roomPanel;
	private JLabel tempSensor;
	private JLabel motionSensor;
	private BoxLayout roomLayout;
	private int roomId;




	public Room(int roomId) {
		this.roomId = roomId;
		roomOuterPanel = new JPanel(new GridLayout(1,1));
		roomPanel = new JPanel();
		roomLayout = new BoxLayout(roomPanel, BoxLayout.PAGE_AXIS);

		tempSensor = new JLabel("TempSensor");
		tempSensor.setName("temp_"+this.getRoomName());
		tempSensor.setOpaque(true);
		tempSensor.setBackground(Color.gray);
		motionSensor = new JLabel("MotionSensor");
		motionSensor.setName("motion_"+this.getRoomName());
		motionSensor.setOpaque(true);
		motionSensor.setBackground(Color.gray);



	}

	public JPanel createRoom() {

		roomPanel.setLayout(roomLayout);
		roomPanel.add(tempSensor);
		roomPanel.add(motionSensor);
		String roomName =this.getRoomName(); 
		roomPanel.setName(roomName);
		roomOuterPanel.setBorder(BorderFactory.createTitledBorder(roomName));
		roomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		roomOuterPanel.add(roomPanel);
		return roomOuterPanel;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return  "Room_" + this.getRoomId();
	}
}

