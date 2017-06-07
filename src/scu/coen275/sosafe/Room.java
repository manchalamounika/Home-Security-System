package scu.coen275.sosafe;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Room extends JPanel{
	

	private JPanel roomOuterPanel, roomPanel;
	//private JLabel tempSensor;
	private MotionSensor motionSensor;
	private BoxLayout roomLayout;
	private int roomId;
	private TemperatureSensor tempSensor;
	

	

	public Room(int roomId) {
		this.roomId = roomId;
		roomOuterPanel = new JPanel(new GridLayout(1,1));
		roomPanel = new JPanel();
		roomLayout = new BoxLayout(roomPanel, BoxLayout.PAGE_AXIS);
		
		tempSensor = new TemperatureSensor(this.getRoomName());
		motionSensor = new MotionSensor(this.getRoomName());
		
		
//		tempSensor = new JLabel("TempSensor");
//		tempSensor.setName("temp_"+this.getRoomName());
//		tempSensor.setOpaque(true);
//		tempSensor.setBackground(Color.gray);
		
//		motionSensor = new JLabel("MotionSensor");
//		motionSensor.setName("motion_"+this.getRoomName());
//		motionSensor.setOpaque(true);
//		motionSensor.setBackground(Color.gray);
		
		
		
	}

	public JPanel createRoom() {
		
		roomPanel.setLayout(roomLayout);
		roomPanel.add(tempSensor.getTempSensor());
		roomPanel.add(motionSensor.getMotionSensor());

		String roomName =this.getRoomName(); 
		roomPanel.setName(roomName);
		roomOuterPanel.setBorder(BorderFactory.createTitledBorder(roomName));
		roomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		roomOuterPanel.add(roomPanel);
//			Icon myImgIcon = new ImageIcon("res/flames.gif");
//			JLabel imageLbl = new JLabel(myImgIcon);
//			roomPanel.add(imageLbl, BorderLayout.CENTER);
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