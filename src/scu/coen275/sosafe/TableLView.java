package scu.coen275.sosafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class TableLView extends JPanel {
	ArrayList<SensorSchedule> sSchedule, stemp;
	File f;
	public TableLView() {
		sSchedule = new ArrayList<SensorSchedule>();
		f = new File("res/sensor_schedule.properties");
		
		
	}

	
	public Object[][] genTable() {
		 stemp = this.readFile(f);
		
		Object[][] san = new Object[stemp.size()][4];
		int i=0;
		for (int j = 0; j < stemp.size(); j++) {
			san[i][0] = stemp.get(j).getSensorType();
			san[i][1] = stemp.get(j).getRoomNm();
			san[i][2] =stemp.get(j).getStartTime();
			san[i][3] =stemp.get(j).getEndTime();
			i=i+1;
		}
		return san;
	}
	
	public void updateDisplay(){
		repaint();
	}
	
	public void updateTableWithChanges(DefaultTableModel dt) {
		//Object[]a ={"2017-09","234"};
		System.out.println(dt.getRowCount());
		while (dt.getRowCount() > 0) {
			System.out.println("Removing Rowwww"+dt.getRowCount());
		       dt.removeRow(0);
		}
		Object[][] c= genTable();
		for(int i=0; i<c.length;i++) {
			System.out.println("Adding Rowwww"+c[i][0]+""+c[i][1]);
			Object[] tmpD ={c[i][0],c[i][1], c[i][2],c[i][3]};
			dt.addRow(tmpD);
		}
		
//		for (int i = dt.getRowCount(); i>-1; i--) {
//  		  System.out.println("Removing!!!!!");
//			dt.removeRow(i-1);
//		}
		//dt.addRow(a);
		
	}

	public ArrayList<SensorSchedule> readFile(File fileName) {

		try {
			FileReader freader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(freader);  
			SensorSchedule ss;
			String s;  
			br.readLine();
			while((s = br.readLine()) != null) {  
				String[] a= s.split("=");
				System.out.println("schedule_names-------------->"+a[0]);
				String[] schedule_names =a[0].split("_");
				System.out.println("schedule_names-------------->"+schedule_names[0]+" "+schedule_names[1]);
				//System.out.println("schedule_names-------------->"+schedule_names[0]+" "+schedule_names[1]);
				String sensor_name = schedule_names[0].equals("temperature")?"Temperature":"Motion";
				System.out.println("sensor_name------------------------------>"+sensor_name);
				String  roomName = schedule_names[2] +" " +schedule_names[3];
				String startEndTime = schedule_names[4].equals("st")?"start":"end";
				int index = this.findBySensorName(a[0].substring(0, a[0].length()-3));
				if(index>=0) {
					System.out.println("Entered because a row exists already");
					ss= this.sSchedule.get(index);
					if(startEndTime.equals("start")){
						ss.setStartTime(a[1]);
					}
					else {
						ss.setEndTime(a[1]);
					}

				}
				else {
					System.out.println("Entered as a new Row");
					ss = new SensorSchedule();
					ss.setIdentifier(a[0]);
					ss.setRoomNm(roomName);
					ss.setSensorType(sensor_name);
					if(startEndTime.equals("start")){
						ss.setStartTime(a[1]);
					}
					else {
						ss.setEndTime(a[1]);
					}
					sSchedule.add(ss);
				}

				System.out.println("file read"+s);
			}  
			freader.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.sSchedule;  		
	}
	
	public int findBySensorName(String name) {
		System.out.println("findBySensorName----------->>>>"+name+"====== size ===" +this.sSchedule.size());
		int flag=-1;
		for(int i=0; i<this.sSchedule.size(); i++) {
			String nameFilter = this.sSchedule.get(i).getIdentifier().substring(0, this.sSchedule.get(i).getIdentifier().length()-3);
			if(nameFilter.equals(name)){
				flag = i;
				System.out.println("findBySensorName----------->>>> came inside"+i);
			}
		}
		return flag;
		
		
	}

}
