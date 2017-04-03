package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cs3450.cashregister.Management.LoginScreen;
import cs3450.cashregister.Management.UserScreen;

public class CashierScreen implements ActionListener{
	
	private JFrame frame = new JFrame("Cashier");
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("User: Cashier");
	private JButton usrButton = new JButton("User Settings");
	private JButton COButton = new JButton("Checkout");
	private JButton RButton = new JButton("Return an Item");
	private JButton logout = new JButton("Logout");
	
	public CashierScreen(){
		//page layout 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(title);
		screen.add(topPanel);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		//Add buttons to screen
		usrButton.setPreferredSize(new Dimension(200, 100));
		panel.add(usrButton);
		COButton.setPreferredSize(new Dimension(200, 100));
		panel.add(COButton);
		RButton.setPreferredSize(new Dimension(200, 100));
		panel.add(RButton);
		logout.setPreferredSize(new Dimension(100, 50));
		panel.add(logout);
		screen.add(panel);
		
		//Add listeners to buttons 
		usrButton.addActionListener(this);
		RButton.addActionListener(this);
		COButton.addActionListener(this);
		logout.addActionListener(this);
		
		//more page layout
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == usrButton){
			frame.dispose();
			new UserScreen();
		}
		if(e.getSource() == RButton){
			frame.dispose();
			new ReturnScreen();
		}
		if(e.getSource() == COButton){
			frame.dispose();
			new CheckoutScreen();
		}
		if(e.getSource() == logout){
			frame.dispose();
			new LoginScreen();
		}
	}

}
