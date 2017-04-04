package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs3450.cashregister.Databases.Employee;
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
	private Employee cashier;
	
	public CashierScreen(Employee cashier){
		//creating cashier employee to pass to user screen
		this.cashier = cashier;
		title = new JLabel("User: " + cashier.getUsername());
				
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
			if(cashier.getUsername().equals("cashier")){
				JOptionPane.showMessageDialog(null, "User settings not available for this user");
			}
			else{
				frame.dispose();
				new UserScreen(cashier);
			}
		}
		if(e.getSource() == RButton){
			frame.dispose();
			new ReturnScreen(cashier);
		}
		if(e.getSource() == COButton){
			frame.dispose();
			new CheckoutScreen(cashier);
		}
		if(e.getSource() == logout){
			frame.dispose();
			try {
				new LoginScreen();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
