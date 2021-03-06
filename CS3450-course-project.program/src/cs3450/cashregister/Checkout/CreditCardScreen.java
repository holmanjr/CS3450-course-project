package cs3450.cashregister.Checkout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs3450.cashregister.Databases.Employee;

public class CreditCardScreen implements ActionListener {
	private JFrame frame = new JFrame("Payment Information");
	private JPanel screen = new JPanel();
	private JButton confirm = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	private JLabel name = new JLabel("Name");
	private JLabel cardNum = new JLabel("Card Number");
	private JLabel expDate = new JLabel("Expiration Date");
	private JLabel CCV = new JLabel("CCV");
	private JTextField eName = new JTextField();
	private JTextField eNum = new JTextField();
	private JTextField eDate = new JTextField();
	private JTextField eCCV = new JTextField();
	private Employee cashier;
	
	//changed...k

	
	public CreditCardScreen(Employee cashier){
		this.cashier = cashier;
		
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new GridLayout(5, 5));
		pane.setPreferredSize(new Dimension(800, 500));
		pane.setSize(50, 50);
		
		
		screen.add(name);
		screen.add(eName);
		screen.add(cardNum);
		screen.add(eNum);
		screen.add(expDate);
		screen.add(eDate);
		screen.add(CCV);
		screen.add(eCCV); 
		screen.add(confirm);
		screen.add(cancel); 
		
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm){
			frame.dispose();
			new ReceiptScreen();
		}
		if(e.getSource() == cancel){
			frame.dispose();
			new PaymentScreen(cashier);
		}
	}

}
