package scu.coen275.sosafe;
import java.awt.Color;

import javax.swing.JLabel;

/**
 * @author mounika
 *
 */
public class TemperatureSensor extends Sensor {
	private String tempSensorName;
	private JLabel tempSensor;
	
	
	public TemperatureSensor(String name) {
		tempSensor = new JLabel("TempSensor");
		this.setTempSensorName(name);
		tempSensor.setName(this.getTempSensorName());
		tempSensor.setOpaque(true);
		super.sensor_activated = Boolean.parseBoolean(super.findFromPropertiesFile(this.getTempSensorName()));
		if(super.sensor_activated) {
			tempSensor.setBackground(Color.GREEN);
		}
		else 
			tempSensor.setBackground(Color.gray);
	}

	/**
	 * @return the tempSensorId
	 */
	public String getTempSensorName() {
		return tempSensorName;
	}

	/*
	 * @param tempSensorId the tempSensorId to set
	 */
	public void setTempSensorName(String name) {
		this.tempSensorName = "temperature_"+super.getSensorName()+"_"+name;
		System.out.println("tempSensorId::::"+ tempSensorName);
	}
	public JLabel getTempSensor() {
		tempSensor.setVisible(super.sensor_activated);
		return tempSensor;
	}
	
}
