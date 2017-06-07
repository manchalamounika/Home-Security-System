/**
 * @author mounika
 *
 */
package scu.coen275.sosafe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.swing.*;

import java.awt.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BillGenerator extends Component {

	private JPanel sidePanel, twoPanel;
	private JLabel nameLabel, startLabel, endLabel, addressLabel;
	private JFrame mainFrame;


	public BillGenerator(JFrame mainFrame){
		this.mainFrame = mainFrame;
	}

	public JPanel createBillWindow()
	{
		Customer customer = new Customer();
		getCustomerInfo(customer);
	
		twoPanel = new JPanel(new GridLayout(1,2));
		twoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setPreferredSize(new Dimension(300, 700));
		sidePanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));

		String nameValue = "Name : "+ customer.getName();
		nameLabel = new JLabel(nameValue);
		nameLabel.setFont(new Font("Cambria", Font.BOLD, 23));
		 
		String service_start = "Service start :" + customer.getServiceStart();
		startLabel = new JLabel(service_start);
		startLabel.setFont(new Font("Cambria", Font.BOLD, 23));

		String service_end = "Service start :" + customer.getServiceEnd();
		endLabel = new JLabel(service_end);
		endLabel.setFont(new Font("Cambria", Font.BOLD, 23));

		String address = "Address :" +customer.getAddress();
		addressLabel = new JLabel(address);
		addressLabel.setFont(new Font("Cambria", Font.BOLD, 23));

		sidePanel.add(nameLabel);
		sidePanel.add(startLabel);
		sidePanel.add(endLabel);
		sidePanel.add(addressLabel);
		
		twoPanel.add(sidePanel);
		return twoPanel;
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
}
