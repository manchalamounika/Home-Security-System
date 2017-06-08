/**
 * 
 */
package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author mounika
 *
 */
public class GenericTimer extends TimerTask{

	private JPanel twoColPanel;
	private Properties sensor_schedule;
	private String sensorRoomName;
	private ArrayList<Component> components;
	private String startEnd;
	//

	public GenericTimer(JPanel twoColPanel, Properties sensor_schedule, String sensorRoomName, String startEnd) {
		this.twoColPanel = twoColPanel;
		this.sensor_schedule = sensor_schedule;
		this.sensorRoomName =sensorRoomName; //.substring(0, sensorRoomName.length() - 3);
		this.startEnd = startEnd;
		components = new ArrayList<Component>();
		listAllComponentsIn(this.twoColPanel);
	}

	@Override
	public void run() {
		System.out.println("Timer Running!!!!!!!");
		JLabel a = findComponent(this.sensorRoomName);
		if(this.startEnd.equals("_st")){
			a.setVisible(true);
			a.setBackground(Color.GREEN);
			System.out.println("Timerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:: "+this.sensorRoomName);
			findAndUpdatePropertiesFile(this.sensorRoomName,"true");
			System.out.println("After   Timerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:: "+this.sensorRoomName);
		}
		if((this.startEnd.equals("_ed"))){
			a.setVisible(true);
			a.setBackground(Color.GRAY);
			System.out.println("Timerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:: "+this.sensorRoomName);
			findAndUpdatePropertiesFile(this.sensorRoomName,"false");
			System.out.println("After   Timerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:: "+this.sensorRoomName);
		}
		
		
		
	}
	
	public void listAllComponentsIn(Container parent)
	{
	    for (Component c : parent.getComponents())
	    {
	        //System.out.println(c.toString());
	    	if(c!=null && c.getName()!=null) {
	    		components.add(c);	
	    	}
	        if (c instanceof Container)
	            listAllComponentsIn((Container)c);
	    }
	}
	
	public JLabel findComponent(String tempsensorInfo)
	{
	JLabel temp = null;
	   for(int i =0; i<components.size(); i++) {
		   //System.out.println(components.get(i).getName());
		   if(components.get(i).getName().equals(tempsensorInfo)){
			  temp = (JLabel) components.get(i);
			  //components.get(i).setBackground(Color.BLUE);;
			  //System.out.println("My Label Triggered");
		   }
	   }
	return temp;
	    
	}
	
	private  void findAndUpdatePropertiesFile(String propertyNm, String flag) {
		FileInputStream in =null;
		FileOutputStream output = null;
		String pflag =null;
		Properties s_state = new Properties();
		try {
			in = new FileInputStream("res/sensor_states.properties");
			s_state.load(in);
			in.close();
			output = new FileOutputStream("res/sensor_states.properties");
			System.out.println("............."+s_state.getProperty(propertyNm));
			System.out.println("ppppppppp..."+propertyNm);
			if(s_state.getProperty(propertyNm) != null)
				pflag = s_state.getProperty(propertyNm);
			s_state.setProperty(propertyNm, flag);
		}
		catch(IOException e){
		}
		finally {

			try {
				s_state.store(output, null);
				output.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
