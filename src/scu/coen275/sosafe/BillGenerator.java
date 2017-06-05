/**
 * @author mounika
 *
 */
package scu.coen275.sosafe;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BillGenerator extends Component {
	public void getCustomerInfo(){
		try{
			FileReader fr = new FileReader("res/CustomerInfo.txt");
			BufferedReader br = new BufferedReader(fr);
			String s;  
			while((s = br.readLine()) != null) {  
				String[] a = s.split(",");
				for (int i = 0; i < a.length; i++) {
					System.out.println(a[i]);
				}  
			}  
			fr.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
