package scu.coen275.sosafe;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;


import java.util.*;

public class ComboBoxGen  {
	
	
	final DefaultComboBoxModel<String> combolist;
	public ComboBoxGen() {
		combolist = new DefaultComboBoxModel<String>();
		 
	}
	
	public JComboBox createCustomCombo(ArrayList<Room> elements) {
		
		final JComboBox<String> customCombo = new JComboBox<String>(combolist);
			for(int i=0; i<elements.size(); i++)
			combolist.addElement(elements.get(i).getRoomName());	
			return customCombo;
	}
	
}
