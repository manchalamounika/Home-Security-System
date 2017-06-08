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
	private JLabel nameLabel, startLabel, endLabel, addressLabel,intrusionInstallationLabel,fireInstallationLabel,headLabel, billHeadLabel;
	private JFrame mainFrame;
	private LineNumberReader lnr;
	private double total_amount= 0;
	private BufferedReader br,br1;
	private String service_contract_id;



	public BillGenerator(JFrame mainFrame){
		this.mainFrame = mainFrame;

		twoPanel = new JPanel(new GridLayout(1,2));
		twoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		custInfoPanel = new JPanel();
		custInfoPanel.setLayout(new BoxLayout(custInfoPanel, BoxLayout.Y_AXIS));
		custInfoPanel.setPreferredSize(new Dimension(300, 700));
		custInfoPanel.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.ORANGE));
		custInfoPanel.setBackground(Color.white);

		billingInfo = new JPanel();
		billingInfo.setLayout(new BoxLayout(billingInfo, BoxLayout.Y_AXIS));
		billingInfo.setPreferredSize(new Dimension(300, 700));
		billingInfo.setBorder(BorderFactory.createTitledBorder("Bill Information"));
		billingInfo.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(244, 244, 244)));
		billingInfo.setBackground(Color.white);
		
	}

	public JPanel getTwoP()
	{
		return this.twoPanel;
	}


	public JPanel createBillWindow()
	{
		
		Customer customer = new Customer();
		getCustomerInfo(customer);
		
		headLabel = new JLabel("Customer Information");
		headLabel.setFont(new Font("serif", Font.BOLD, 25));
		headLabel.setForeground(new Color(0, 12, 155));

		ImageIcon image = new ImageIcon("img/monika.png");
		JLabel picLabel = new JLabel(image);
		
			
		String nameValue = "Name : "+ customer.getName();
		nameLabel = new JLabel(nameValue);
		nameLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		nameLabel.setForeground(Color.DARK_GRAY);

		String service_start = "Service start :" + customer.getServiceStart();
		startLabel = new JLabel(service_start);
		startLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		startLabel.setForeground(Color.DARK_GRAY);

		String service_end = "Service start :" + customer.getServiceEnd();
		endLabel = new JLabel(service_end);
		endLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		endLabel.setForeground(Color.DARK_GRAY);

		
		String address[] =  customer.getAddress().split(",");
		addressLabel = new JLabel("Address :");
		JLabel[] tempLabel= new JLabel[address.length];
		addressLabel.setFont(new Font("serif", Font.BOLD, 20));
		addressLabel.setForeground(Color.DARK_GRAY);
		
		JLabel service_id = new JLabel("Service Contract ID: "+service_contract_id);
		service_id.setFont(new Font("serif", Font.BOLD, 20));
		service_id.setForeground(Color.DARK_GRAY);
		
		addressLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		custInfoPanel.add(Box.createRigidArea(new Dimension(200, 100)));
		custInfoPanel.add(headLabel);
		custInfoPanel.add(picLabel);
		custInfoPanel.add(service_id);
		custInfoPanel.add(nameLabel);
		custInfoPanel.add(startLabel);
		custInfoPanel.add(endLabel);
		custInfoPanel.add(addressLabel);
		for(int i=0;i<address.length;i++) {
			tempLabel[i] = new JLabel();
			tempLabel[i].setText(address[i]);
			custInfoPanel.add(tempLabel[i]);
		}
		billHeadLabel = new JLabel("Billing Information");
		billHeadLabel.setFont(new Font("serif", Font.BOLD, 20));
		billHeadLabel.setForeground(new Color(0, 12, 155));
		
		
		intrusionInstallationLabel = new JLabel("Intrusion Package Installation---------------------------------------------------------------------->200");
		intrusionInstallationLabel.setFont(new Font("serif", Font.PLAIN, 14));
		intrusionInstallationLabel.setForeground(Color.black);
		intrusionInstallationLabel.setVisible(false);
		
		
		fireInstallationLabel = new JLabel("Fire Detection Package Installation----------------------------------------------------------------->300");
		fireInstallationLabel.setFont(new Font("serif", Font.PLAIN, 14));
		fireInstallationLabel.setForeground(Color.black);
		fireInstallationLabel.setVisible(false);
		
		billingInfo.add(billHeadLabel);
		billingInfo.add(new JLabel("                  "));
		billingInfo.add(intrusionInstallationLabel);
		billingInfo.add(fireInstallationLabel);
	
		generateBill(billingInfo);			
		twoPanel.add(custInfoPanel);
		twoPanel.add(billingInfo);

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
			JLabel heading = new JLabel("------------------Sensors Installation------------------");
			billingInfo.add(heading);
			heading.setForeground(Color.ORANGE);
			heading.setFont(new Font("serif", Font.PLAIN, 18));
			
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
					JLabel jb = new JLabel("Motion Sensor in "+b[2]+" "+b[3]+"----------------------------------------------------------------------------------------------->"+50);
					billingInfo.add(jb);
				}
				else{
					fireInstallationLabel.setVisible(true);
					fireDetecionPackage++;
					total_amount = total_amount + 100.00;
					JLabel j = new JLabel("Temperature Sensor in "+b[2]+" "+b[3]+"------------------------------------------------------------------------------------->"+100);
					billingInfo.add(j);
				}				
				System.out.println(b);
			}
			
			JLabel t = new JLabel("-----------------------Intrusion detected Info-------------------------");
			t.setForeground(Color.orange);
			t.setFont(new Font("serif", Font.PLAIN, 18));
			billingInfo.add(t);
			br = new BufferedReader(new FileReader("res/triggeredIntrusionInfo.txt"));
			String line;
			while((line = br.readLine()) != null){
				total_amount += 20;
				JLabel l = new JLabel(line+"--------------------------------------------------------------------->20");
				billingInfo.add(l);
			}
			
			br1 = new BufferedReader(new FileReader("res/triggeredFireInfo.txt"));
			JLabel t1 = new JLabel("-----------------------Fire detected Info-------------------------");
			t1.setForeground(Color.ORANGE);
			t1.setFont(new Font("serif", Font.PLAIN, 18));
			billingInfo.add(t1);
			String line2;
			while((line2 = br1.readLine()) != null){
				total_amount += 50;
				JLabel l1 = new JLabel();
				l1.setText(line2+"-------------------------------------------------------------------------->50");
				billingInfo.add(l1);
			}
			if (intrusionPackage > 0 && fireDetecionPackage > 0){
				JLabel t3 = new JLabel("----------------Discounts------------");
				t3.setForeground(Color.orange);
				t3.setFont(new Font("serif", Font.PLAIN, 18));
				billingInfo.add(t3);
				JLabel d = new JLabel("If both Package then 20% discount on $300 Fire Detection Package---------------------------------->(-)60");
				billingInfo.add(d);
				total_amount = total_amount + 200 +240;
			}
			else if(intrusionPackage > 0){
				total_amount = total_amount + 200;
			}
			else
				total_amount= total_amount + 300;
			JLabel finalamt = new JLabel("-----------------Toatal Amount in Dollars is------------");
			finalamt.setForeground(Color.orange);
			finalamt.setFont(new Font("serif", Font.PLAIN, 18));
			
			billingInfo.add(finalamt);
			JLabel amt = new JLabel("Total amount in Dollars--------------------------------------------------->");
			amt.setForeground(new Color(10, 199, 116));
			amt.setFont(new Font("serif", Font.BOLD, 18));
			amt.setText("Total----------------------------------------------------------------------------->"+total_amount);
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
			service_contract_id =(String) jsonObject.get("service_id");
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
				e.printStackTrace();
			}
			return lnr.getLineNumber();
		}

	}
}
