package scu.coen275.sosafe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SaveClickListener implements ActionListener {
	private JComboBox jc;
	private JCheckBox tempSensor;
	private JCheckBox motionSensor;
	private JPanel mainFrame;
	private ArrayList<Component> components;

	public SaveClickListener(JPanel twoColPanel, JComboBox jComponent, JCheckBox tempSensor, JCheckBox motionSensor) {
		this.jc =jComponent;
		this.tempSensor = tempSensor;
		this.motionSensor = motionSensor;
		this.mainFrame = twoColPanel;
		components = new ArrayList<Component>();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("save")) {
			System.out.println(jc.getSelectedItem().toString());
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+tempSensor.isSelected());
			System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"+motionSensor.isSelected());
			System.out.println();
			//TODO to a json file 
			Component c[] = mainFrame.getComponents();
			listAllComponentsIn(this.mainFrame);

			if(tempSensor.isSelected()) {
				String tempsensorInfo = tempSensor.isSelected()?"temp_"+jc.getSelectedItem().toString():"";
				JLabel a = findComponent(tempsensorInfo);
				a.setBackground(Color.GREEN);
			}
			else if(!tempSensor.isSelected()) {
				String tempsensorInfo = !tempSensor.isSelected()?"temp_"+jc.getSelectedItem().toString():"";
				JLabel a = findComponent(tempsensorInfo);
				System.out.println("===========================================TempSensor Uncheckx"+ a.getBackground());
				Color sensorTypeColor = a.getBackground();
				if(sensorTypeColor.equals(Color.GREEN)) {
					a.setBackground(Color.GRAY);
				}
			}

			if(motionSensor.isSelected()) {
				String motionsensorInfo = motionSensor.isSelected()?"motion_"+jc.getSelectedItem().toString():"";
				JLabel b = findComponent(motionsensorInfo);
				b.setBackground(Color.GREEN);
			}
			else if(!motionSensor.isSelected()) {
				String tempsensorInfo = !motionSensor.isSelected()?"motion_"+jc.getSelectedItem().toString():"";
				JLabel a = findComponent(tempsensorInfo);
				System.out.println("===========================================MotionSensor Uncheckx"+ a.getBackground());
				Color sensorTypeColor = a.getBackground();
				if(sensorTypeColor.equals(Color.GREEN)) {
					a.setBackground(Color.GRAY);
				}
			}


			//a.setBackground(Color.GREEN);


			//			for(int i=0;i<this.mainFrame.getComponentCount();i++)
			//			{
			//			if ()
			//			{
			//			System.out.println("found element");
			//			}
			//			}
			System.out.println(this.mainFrame.getComponent(0));

		}
	}
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
			//System.out.println(components.get(i).getName());
			if(components.get(i).getName().equals(tempsensorInfo)){
				temp = (JLabel) components.get(i);
				//components.get(i).setBackground(Color.BLUE);;
				System.out.println("My Label Triggered");
			}
		}
		return temp;
	}
}

