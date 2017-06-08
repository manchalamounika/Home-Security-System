/**
 * 
 */
package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * @author mounika
 *
 */
public class ScheduleSetUpListener implements ActionListener {
	private JPanel profilePanel;
	private JComboBox jBox;
	private JCheckBox tempSensorChkBox;
	private JCheckBox motionSensorChkBox;
	private JComboBox hourBox;
	private JComboBox minBox; 
	private JComboBox ehourBox; 
	private JComboBox eminBox;
	private JPanel twoColPanel;
	private TableLView tv;
	private DefaultTableModel dt;
	

	public ScheduleSetUpListener(JPanel twoColPanel,JPanel profilePanel, JComboBox jBox, JCheckBox tempSensorChkBox,
			JCheckBox motionSensorChkBox, JComboBox hourBox, JComboBox minBox, JComboBox ehourBox, JComboBox eminBox,TableLView tv, DefaultTableModel dt) {
		this.profilePanel = profilePanel;
		this.jBox = jBox;
		this.tempSensorChkBox = tempSensorChkBox;
		this.motionSensorChkBox =motionSensorChkBox;
		this.hourBox = hourBox;
		this.minBox = minBox;
		this.ehourBox = ehourBox;
		this.eminBox = eminBox;
		this.twoColPanel = twoColPanel;
		this.tv =tv;
		this.dt =dt;
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Properties sensor_schedule = new Properties();
		FileOutputStream output = null;
		FileInputStream in =null;
		String tSnsrSchdStrt, tSnsrSchdEnd, mSnsrSchdStrt,mSnsrSchdEnd;
		String tempsensorInfo = null, motionsensorInfo=null;
		try {
			in = new FileInputStream("res/sensor_schedule.properties");
			sensor_schedule.load(in);
			in.close();
			output = new FileOutputStream("res/sensor_schedule.properties");
			String command = e.getActionCommand();
			//System.out.println("UniqueId From Properties"+sensor_schedule.getProperty("motion_sensor_Room_1_st"));
			if(command.equals("save")) {
				System.out.println(jBox.getSelectedItem().toString());

				//String name = getUniqueNameForSchedule(tempSensorChkBox.getName(),jBox.getSelectedItem().toString(),"st");
				if(tempSensorChkBox.isSelected()) {
					tempsensorInfo = "temperature_sensor_"+jBox.getSelectedItem().toString();
					//sensor_schedule.setProperty("temperature_sensor_"+jBox.getSelectedItem().toString(), String.valueOf(true));
					tSnsrSchdStrt = tempsensorInfo+"_st";
					String c= hourBox.getSelectedItem().toString()+":"+minBox.getSelectedItem().toString();
					sensor_schedule.setProperty(tSnsrSchdStrt,c);
					tSnsrSchdEnd = tempsensorInfo+"_ed";
					sensor_schedule.setProperty(tSnsrSchdEnd, ehourBox.getSelectedItem().toString()+":"+eminBox.getSelectedItem().toString());
					//GenericTimer gt = new GenericTimer(this.twoColPanel,sensor_schedule);
					createSchedule(sensor_schedule,tempsensorInfo,"_st");
					createSchedule(sensor_schedule,tempsensorInfo,"_ed");
				
					
				}
				if(motionSensorChkBox.isSelected()) {
					motionsensorInfo = "motion_sensor_"+jBox.getSelectedItem().toString();
					//sensor_schedule.setProperty("temperature_sensor_"+jBox.getSelectedItem().toString(), String.valueOf(true));
					mSnsrSchdStrt = motionsensorInfo+"_st";
					sensor_schedule.setProperty(mSnsrSchdStrt, hourBox.getSelectedItem().toString()+":"+minBox.getSelectedItem().toString());
					mSnsrSchdEnd = motionsensorInfo+"_ed";
					sensor_schedule.setProperty(mSnsrSchdEnd, ehourBox.getSelectedItem().toString()+":"+eminBox.getSelectedItem().toString());
					createSchedule(sensor_schedule,motionsensorInfo,"_st");
					createSchedule(sensor_schedule,motionsensorInfo, "_ed");
				}

			}
			//tv.updateTableWithChanges(dt);
			System.out.println("Data Written to file for scheduling");
		}
		catch(IOException ex) {

		}
		finally {

			try {
				sensor_schedule.store(output, null);
				output.close();
				profilePanel.revalidate();
				profilePanel.repaint();
				tv.updateTableWithChanges(dt);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	private String getUniqueNameForSchedule(String sensorTyp, String roomName, String startEnd) {
		return null;
	}
	
	private void createSchedule(Properties sensor_schedule,String roomSnsrName, String startEnd) {
		
		TimerTask startTask =  new GenericTimer(this.twoColPanel,sensor_schedule,roomSnsrName, startEnd);
		Timer timer = new Timer();
		 SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 SimpleDateFormat currDFormat = new SimpleDateFormat("yyyy/MM/dd");
			try {
				String curDate = currDFormat.format(new Date());
				Date date1 = format.parse(curDate+" "+sensor_schedule.getProperty(roomSnsrName+startEnd)+":00");
				timer.scheduleAtFixedRate(startTask, date1,24*60*60*1000);      

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
