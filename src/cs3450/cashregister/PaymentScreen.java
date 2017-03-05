package cs3450.cashregister;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaymentScreen implements ActionListener {
	
	private JFrame frame = new JFrame("Select a payment type");
	private JPanel screen = new JPanel();
	private JButton credit = new JButton("Credit");
	private JButton debit = new JButton("Debit");
	private JButton cash = new JButton("Cash");
	
	public PaymentScreen(){
		credit.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new GridLayout());
		pane.setSize(50, 50);

		screen.add(credit);
		screen.add(debit);
		screen.add(cash);
		
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new CreditCardScreen();
	}

}
