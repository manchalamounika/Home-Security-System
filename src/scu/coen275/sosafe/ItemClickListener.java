package scu.coen275.sosafe;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JCheckBox;

/**
 * 
 */

/**
 * @author mounika
 *
 */
public class ItemClickListener implements ItemListener {
	private JCheckBox tempSensorChkBox,motionSensorChkBox;
	public ItemClickListener(JCheckBox tempSensorChkBox,JCheckBox motionSensorChkBox) {
		this.tempSensorChkBox = tempSensorChkBox;
		this.motionSensorChkBox = motionSensorChkBox;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
	          Object item = e.getItem();
	          System.out.println("caused by me" +e.getItem().toString());
	          String tempSensorCheck =findFromPropertiesFile("temperature_sensor_"+e.getItem().toString());
	          String motionSensorCheck =findFromPropertiesFile("motion_sensor_"+e.getItem().toString());
	          //System.out.println("tempSensorCheck on change :::"+tempSensorCheck);
	          //System.out.println("Boolean.parseBoolean(tempSensorCheck):::"+Boolean.parseBoolean(tempSensorCheck));
	          this.tempSensorChkBox.setSelected(Boolean.parseBoolean(tempSensorCheck));
	          this.motionSensorChkBox.setSelected(Boolean.parseBoolean(motionSensorCheck));
	          //String tempSensorCheck =findFromPropertiesFile();
	       }
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
		
		return pflag;
		
	}

}
