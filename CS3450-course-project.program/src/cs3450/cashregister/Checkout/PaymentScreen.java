package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs3450.cashregister.Databases.Employee;

public class PaymentScreen implements ActionListener {
	
	private JFrame frame = new JFrame("Select a payment type");
	private JPanel screen = new JPanel();
	private JButton credit = new JButton("Credit");
	private JButton debit = new JButton("Debit");
	private JButton cash = new JButton("Cash");
	private JButton cancel = new JButton("Cancel");
	private Employee cashier;
	
	public PaymentScreen(Employee cashier){
		//creating cashier employee to pass
		this.cashier = cashier;
		
		//set up page
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new FlowLayout());
		
		//manage buttons
		credit.setPreferredSize(new Dimension(200, 100));
		debit.setPreferredSize(new Dimension(200, 100));
		cash.setPreferredSize(new Dimension(200, 100));
		cancel.setPreferredSize(new Dimension(200, 100));
		credit.addActionListener(this);
		debit.addActionListener(this);
		cash.addActionListener(this);
		cancel.addActionListener(this);
		JLabel total = new JLabel(Float.toString(CheckoutScreen.total) + " is due.");
		screen.add(total);
		screen.add(credit);
		screen.add(debit);
		screen.add(cash);
		screen.add(cancel);
		
		//manage page
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == credit || e.getSource() == debit) {
			frame.dispose();
			new CreditCardScreen(cashier);
		}
		if (e.getSource() == cash){
	    	Object given = JOptionPane.showInputDialog(frame, "How much cash was given?");
	    	Float change = CheckoutScreen.total - Float.parseFloat((String) given);
			JOptionPane.showMessageDialog(frame, "The change is: " + change);
			frame.dispose();
			new ReceiptScreen(); 
		}
		if(e.getSource() == cancel){
			frame.dispose();
		}
	}

}
