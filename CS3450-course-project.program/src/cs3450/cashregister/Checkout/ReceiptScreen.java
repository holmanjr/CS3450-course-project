package cs3450.cashregister.Checkout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReceiptScreen implements ActionListener{

	private JFrame frame = new JFrame("Your Receipt");
	private JPanel screen = new JPanel();
	private JButton print = new JButton("Print");

	
	public ReceiptScreen() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new FlowLayout());
		
		//buttons
		screen.add(CheckoutScreen.table);
		JLabel total = new JLabel("Your total is: $" + Float.toString(CheckoutScreen.total));
		total.setPreferredSize(new Dimension(200, 100));
		print.setPreferredSize(new Dimension(200, 100));
		print.addActionListener(this);
		screen.add(total);
		screen.add(print); 
		
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(frame, "Please take your receipt.");
		frame.dispose();
	}
}
