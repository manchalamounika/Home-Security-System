package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SaveClickListener implements ActionListener {
	private JComboBox jc;
	private JCheckBox tempSensor;
	private JCheckBox motionSensor;
	private JPanel mainFrame;
	private ArrayList<Component> components;
	
	public SaveClickListener(JPanel twoColPanel, JComboBox jComponent, JCheckBox tempSensor, JCheckBox motionSensor) {
		this.jc =jComponent;
		this.tempSensor = tempSensor;
		this.motionSensor = motionSensor;
		this.mainFrame = twoColPanel;
		components = new ArrayList<Component>();
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Properties sensor_state = new Properties();
		FileOutputStream output = null;
		FileInputStream in =null;
		try {
			in = new FileInputStream("res/sensor_states.properties");
			sensor_state.load(in);
			in.close();
			output = new FileOutputStream("res/sensor_states.properties");
			String command = e.getActionCommand();
			if(command.equals("save")) {
				System.out.println(jc.getSelectedItem().toString());
//				System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+tempSensor.isSelected());
//				System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"+motionSensor.isSelected());
				System.out.println();
				//TODO to a json file 
				Component c[] = mainFrame.getComponents();
				listAllComponentsIn(this.mainFrame);
				
				if(tempSensor.isSelected()) {
					String tempsensorInfo = tempSensor.isSelected()?"temperature_sensor_"+jc.getSelectedItem().toString():"";
					JLabel a = findComponent(tempsensorInfo);
					a.setBackground(Color.GREEN);
					sensor_state.setProperty("temperature_sensor_"+jc.getSelectedItem().toString(), String.valueOf(true));
				
				}
				else if(!tempSensor.isSelected()) {
					String tempsensorInfo = !tempSensor.isSelected()?"temperature_sensor_"+jc.getSelectedItem().toString():"";
					JLabel a = findComponent(tempsensorInfo);
					//System.out.println("===========================================TempSensor Uncheckx"+ a.getBackground());
					Color sensorTypeColor = a.getBackground();
					if(sensorTypeColor.equals(Color.GREEN)) {
						a.setBackground(Color.GRAY);
						sensor_state.setProperty("temperature_sensor_"+jc.getSelectedItem().toString(), String.valueOf(false));
					}
				}
				
				if(motionSensor.isSelected()) {
					String motionsensorInfo = motionSensor.isSelected()?"motion_sensor_"+jc.getSelectedItem().toString():"";
					JLabel b = findComponent(motionsensorInfo);
					b.setBackground(Color.GREEN);
					sensor_state.setProperty("motion_sensor_"+jc.getSelectedItem().toString(), String.valueOf(true));
				}
				else if(!motionSensor.isSelected()) {
					String tempsensorInfo = !motionSensor.isSelected()?"motion_sensor_"+jc.getSelectedItem().toString():"";
					JLabel a = findComponent(tempsensorInfo);
					System.out.println("===========================================MotionSensor Uncheckx"+ a.getBackground());
					Color sensorTypeColor = a.getBackground();
					if(sensorTypeColor.equals(Color.GREEN)) {
						sensor_state.setProperty("motion_sensor_"+jc.getSelectedItem().toString(), String.valueOf(false));
						a.setBackground(Color.GRAY);
						
					}
				}
				System.out.println(this.mainFrame.getComponent(0));
				
			}
		}
		catch(IOException ex) {
			
		}
		finally {
			
			try {
				sensor_state.store(output, null);
				output.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
}
