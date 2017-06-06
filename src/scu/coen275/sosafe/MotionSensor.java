/**
 * @author mounika
 *
 */
package scu.coen275.sosafe;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * 
 */

/**
 * @author mounika
 *
 */
public class MotionSensor extends Sensor {
	private String motionSensorName;
	private JLabel motionSensor;
	
	public MotionSensor(String name) {
		motionSensor = new JLabel("MotionSensor");
		this.setMotionSensorName(name);
		motionSensor.setName(this.getMotionSensorName());
		motionSensor.setOpaque(true);
		motionSensor.setBackground(Color.gray);
	}

	/**
	 * @return the motionSensorId
	 */
	public String getMotionSensorName() {
		return motionSensorName;
	}

	/*
	 * @param motionSensorId the motionSensorId to set
	 */
	public void setMotionSensorName(String name) {
		this.motionSensorName = "motion_"+super.getSensorName()+"_"+name;
		System.out.println("motionSensorId::::"+ motionSensorName);
	}
	public JLabel getMotionSensor() {
		return motionSensor;
	}

}

