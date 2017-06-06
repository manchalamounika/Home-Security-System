/**
 * @author mounika
 *
 */
package scu.coen275.sosafe;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BillGenerator extends Component {
	public BillGenerator(){
		getCustomerInfo();
	}
	public void getCustomerInfo(){
		try{
			FileReader fr = new FileReader("res/customer.json");
			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fr);
            Customer c = new Customer();
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
