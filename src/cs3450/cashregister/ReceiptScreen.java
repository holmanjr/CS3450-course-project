package cs3450.cashregister;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReceiptScreen {

	private JFrame frame = new JFrame("Your Receipt");
	private JPanel screen = new JPanel();
	private JButton print = new JButton("Print");

	
	public ReceiptScreen(String name){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new GridLayout(2, 1));
		pane.setPreferredSize(new Dimension(800, 500));
		pane.setSize(50, 50);
		
		screen.add(new JLabel(name + ", your total is: " + Float.toString(CheckoutScreen.total)));
		screen.add(print); 
		
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
