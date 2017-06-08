package scu.coen275.sosafe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JPanel;

public class Billing {
	private FileWriter fw ;
	private BufferedWriter bw;
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	public Billing() {		
		
		
	}

	public void fireDetected(String msg) {
		File file =new File("res/triggeredFireInfo.txt");
		try {
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			 LocalDateTime now = LocalDateTime.now();
		        System.out.println(dtf.format(now));
			fw.write(msg+" "+dtf.format(now)+"\n");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}			
		finally {
			try {
				fw.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void intrusionDetected(String msg) {
		File file =new File("res/triggeredIntrusionInfo.txt");
		try {
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			 LocalDateTime now = LocalDateTime.now();
		        System.out.println(dtf.format(now));
			fw.write(msg+" "+dtf.format(now)+"\n");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}			
		finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
