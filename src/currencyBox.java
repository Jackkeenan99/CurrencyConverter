
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*; 

public class currencyBox implements ActionListener {
	
	JFrame jf; 
	JComboBox jcb, jcb1;
	JLabel l,l1;
	JButton next;
	public String to = "Fijian Dollar";
	public String from = "Fijian Dollar";
	
	public void showBox(String [] currency) {
		jf = new JFrame("Live Currency Converter");
		jf.setLayout(new FlowLayout());
		jcb = new JComboBox(currency);
		jcb1 = new JComboBox(currency);
		jcb.setBorder(BorderFactory.createEmptyBorder(100,50,100,100));
		jcb.addItemListener(
				new ItemListener() {			//anonymous class 
					public void itemStateChanged(ItemEvent event) {
						if(event.getStateChange()== ItemEvent.SELECTED) {
							from = currency[jcb.getSelectedIndex()];
							
						}
					}
				}
			);
		jcb1.setBorder(BorderFactory.createEmptyBorder(100,50,100,100));
		jcb1.addItemListener(
				new ItemListener() {			//anonymous class
					public void itemStateChanged(ItemEvent event) {
						if(event.getStateChange()== ItemEvent.SELECTED) {
							to = currency[jcb1.getSelectedIndex()];
						}
					}
				}
			);
		l = new JLabel("Convert From");
		l1 = new JLabel("Convert to");
		next = new JButton("Next");
		next.addActionListener(this);
		jf.add(l);
		jf.add(jcb , BorderLayout.CENTER);
		jf.add(l1);
		jf.add(jcb1 , BorderLayout.CENTER);
		jf.add(next);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(to != from) {
			jf.dispose();
			main.getRates(from, to);
		}
		else JOptionPane.showMessageDialog(null, "Same Currency was selected, Try Again", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
 
}
