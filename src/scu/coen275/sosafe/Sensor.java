package scu.coen275.sosafe;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JLabel;

/**
 * @author mounika
 *
 */
public class Sensor extends JLabel {
	String sensorName;
	protected Boolean sensor_activated;

	public String getSensorName() {
		return "sensor";
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String findFromPropertiesFile(String propertyNm) {
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
