/**
 * @author mounika
 *
 */
package scu.coen275.sosafe;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import javax.swing.*;

import java.awt.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BillGenerator extends Component {


	private JPanel custInfoPanel, twoPanel,billingInfo;
	private JLabel nameLabel, startLabel, endLabel, addressLabel,intrusionInstallationLabel,fireInstallationLabel;
	private JFrame mainFrame;
	private LineNumberReader lnr;
	private double total_amount= 0;
	private BufferedReader br,br1;



	public BillGenerator(JFrame mainFrame){
		this.mainFrame = mainFrame;

		twoPanel = new JPanel(new GridLayout(1,2));
		twoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		custInfoPanel = new JPanel();
		custInfoPanel.setLayout(new BoxLayout(custInfoPanel, BoxLayout.Y_AXIS));
		custInfoPanel.setPreferredSize(new Dimension(300, 700));
		custInfoPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));

		billingInfo = new JPanel();
		billingInfo.setLayout(new BoxLayout(billingInfo, BoxLayout.Y_AXIS));
		billingInfo.setPreferredSize(new Dimension(300, 700));
		billingInfo.setBorder(BorderFactory.createTitledBorder("Bill Information"));
		
	}

	public JPanel getTwoP()
	{
		return this.twoPanel;
	}


	public JPanel createBillWindow()
	{
		Customer customer = new Customer();
		getCustomerInfo(customer);

		String nameValue = "Name : "+ customer.getName();
		nameLabel = new JLabel(nameValue);
		nameLabel.setFont(new Font("Cambria", Font.BOLD, 23));


		String service_start = "Service start :" + customer.getServiceStart();
		startLabel = new JLabel(service_start);
		startLabel.setFont(new Font("Cambria", Font.BOLD, 23));

		String service_end = "Service start :" + customer.getServiceEnd();
		endLabel = new JLabel(service_end);
		endLabel.setFont(new Font("Cambria", Font.BOLD, 23));


		String address[] =  customer.getAddress().split(",");
		addressLabel = new JLabel("Address :");
		JLabel[] tempLabel= new JLabel[address.length];
		
		addressLabel.setFont(new Font("Cambria", Font.BOLD, 23));

		custInfoPanel.add(nameLabel);
		custInfoPanel.add(startLabel);
		custInfoPanel.add(endLabel);
		custInfoPanel.add(addressLabel);
		for(int i=0;i<address.length;i++) {
			tempLabel[i] = new JLabel();
			tempLabel[i].setText(address[i]);
			custInfoPanel.add(tempLabel[i]);
		}
		
		
		intrusionInstallationLabel = new JLabel("Intrusion Package Installation---->200");
		intrusionInstallationLabel.setVisible(false);
		
		fireInstallationLabel = new JLabel("Fire Detection Package Installation---->300");
		fireInstallationLabel.setVisible(false);
		
		billingInfo.add(intrusionInstallationLabel);
		billingInfo.add(fireInstallationLabel);
	
		generateBill(billingInfo);			
		twoPanel.add(custInfoPanel);
		twoPanel.add(billingInfo);
//		 double val = getDummyVal();
//		 JLabel l = new JLabel();
//		 l.setText(""+val);
//		 twoPanel.add(l);
		return twoPanel;
	}

	private double getDummyVal() {
		int intrusionLength = getSimultedInfo("res/triggeredIntrusionInfo.txt");
		int fireInLength = getSimultedInfo("res/triggeredFireInfo.txt");

		Double final_amount = (double) (intrusionLength*50 + fireInLength*100);
		System.out.println("total amount is "+final_amount);	
		return final_amount;
		
	}

	private void generateBill(JPanel billingInfo) {
		String[] sensorsInfo;
		
 		Properties p = new Properties();
		try{
			JLabel heading = new JLabel("---Sensors installation---");
			billingInfo.add(heading);
			p.load(new FileInputStream("res/sensor_states.properties"));
			Set<Object> keys= p.keySet();
			int intrusionPackage = 0;
			int fireDetecionPackage = 0;
			for(Object k:keys){				
	            String key = (String)k;
	            String[] b = key.split("_");
				System.out.println(b[0]);
				if (Arrays.asList(b).contains("motion")){
					intrusionPackage++;
					intrusionInstallationLabel.setVisible(true);
					total_amount = total_amount + 50.00;
					JLabel jb = new JLabel("Motion Sensor in "+b[2]+" "+b[3]+"-->"+50);
					billingInfo.add(jb);
				}
				else{
					fireInstallationLabel.setVisible(true);
					fireDetecionPackage++;
					total_amount = total_amount + 100.00;
					JLabel j = new JLabel("Temperature Sensor in "+b[2]+" "+b[3]+"-->"+100);
					billingInfo.add(j);
				}				
				System.out.println(b);
			}
			
			JLabel t = new JLabel("-----------------------Intrusion detected Info-------------------------");
			billingInfo.add(t);
			br = new BufferedReader(new FileReader("res/triggeredIntrusionInfo.txt"));
			String line;
			while((line = br.readLine()) != null){
				total_amount += 20;
				JLabel l = new JLabel(line+"------------->20");
				billingInfo.add(l);
			}
			
			br1 = new BufferedReader(new FileReader("res/triggeredFireInfo.txt"));
			JLabel t1 = new JLabel("-----------------------Fire detected Info-------------------------");
			billingInfo.add(t1);
			String line2;
			while((line2 = br1.readLine()) != null){
				total_amount += 50;
				JLabel l1 = new JLabel();
				l1.setText(line2+"------------->50");
				billingInfo.add(l1);
			}
			if (intrusionPackage > 0 && fireDetecionPackage > 0){
				JLabel t3 = new JLabel("----------------Discounts------------");
				billingInfo.add(t3);
				JLabel d = new JLabel("You are having both the packages so we are providing 20% discount on fire detction packge on 300$---->240");
				billingInfo.add(d);
				total_amount = total_amount + 200 +240;
			}
			else if(intrusionPackage > 0){
				total_amount = total_amount + 200;
			}
			else
				total_amount= total_amount + 300;
			JLabel finalamt = new JLabel("--------------------Toatal Amount is--------------");
			billingInfo.add(finalamt);
			JLabel amt = new JLabel("Total amount-------------->");
			amt.setText(""+total_amount);
			billingInfo.add(amt);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				br1.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	


	public void getCustomerInfo(Customer c){
		try{
			FileReader fr = new FileReader("res/customer.json");
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(fr);
			c.setName((String) jsonObject.get("name"));
			System.out.println("The first name is: " + (String) jsonObject.get("name"));
			c.setServiceStart((String)jsonObject.get("service_start"));
			c.setServiceEnd((String) jsonObject.get("service_end"));

			JSONObject structure = (JSONObject) jsonObject.get("address");
			String line1 = (String) structure.get("line_1");
			String line2 = (String) structure.get("line_2");
			String state = (String) structure.get("state");
			String city = (String) structure.get("city");            
			String zip  = (String) structure.get("zip");
			System.out.println(line1+"lllllllllllllllll");

			Address adr = new Address(line1, line2, city, state, zip);
			c.setAddress(adr);

			generateTotalAmount();


		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch(ParseException e){
			e.printStackTrace();
			System.out.println("Parsing exception occured");
		}
		catch(NullPointerException e){
			e.printStackTrace();
			System.out.println("Null pointer exception occured");
		}
	}

	public void generateTotalAmount(){

		int intrusionLength = getSimultedInfo("res/triggeredIntrusionInfo.txt");
		int fireInLength = getSimultedInfo("res/triggeredFireInfo.txt");

		Double final_amount = (double) (intrusionLength*50 + fireInLength*100);
		System.out.println("total amount is "+final_amount);				 

	}

	private int getSimultedInfo(String fileLocation) {
		try 
		{
			lnr = new LineNumberReader(new FileReader(new File(fileLocation)));
			lnr.skip(Long.MAX_VALUE);
			System.out.println(lnr.getLineNumber());


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				lnr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lnr.getLineNumber();
		}

	}
}
