package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReturnScreen implements ActionListener{
	
	private JButton search = new JButton("Search");
	private JButton cancel = new JButton("Cancel");
	private JLabel label = new JLabel("Enter the order ID");
	private JTextField field = new JTextField();
	private JFrame frame = new JFrame("Cashier");
	private JPanel upperScreen = new JPanel();
	private JPanel lowerScreen = new JPanel();
	
	ReturnScreen(){
		//page layout 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel)frame.getContentPane();
		
		//set up the upper screen
		upperScreen.setLayout(new BoxLayout(upperScreen, BoxLayout.LINE_AXIS));
		label.setPreferredSize(new Dimension(200, 100));
		field.setPreferredSize(new Dimension(200, 100));
		upperScreen.add(label);
		upperScreen.add(field);
		pane.add(upperScreen, BorderLayout.CENTER);
		
		//set up second screensss
		lowerScreen.setLayout(new BoxLayout(lowerScreen, BoxLayout.LINE_AXIS));		
		search.setPreferredSize(new Dimension(200, 100));
		cancel.setPreferredSize(new Dimension(200, 100));
		lowerScreen.add(search);
		lowerScreen.add(cancel);
		search.addActionListener(this);
		cancel.addActionListener(this);
		pane.add(lowerScreen, BorderLayout.PAGE_END);
		
		//more layouts
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			
		}
		if(e.getSource() == cancel){
			frame.dispose();
			new CashierScreen();
		}
	}

}
