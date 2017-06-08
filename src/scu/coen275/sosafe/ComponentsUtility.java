/**
 * 
 */
package scu.coen275.sosafe;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * @author mounika
 *
 */
public class ComponentsUtility {
	private ArrayList<Component> components;
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
		   if(components.get(i).getName().equals(tempsensorInfo)){
			  temp = (JLabel) components.get(i);
		   }
	   }
	return temp;
	    
	}
	
}
