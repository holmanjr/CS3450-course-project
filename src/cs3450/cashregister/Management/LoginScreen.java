/**
 * 
 */
package cs3450.cashregister.Management;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cs3450.cashregister.Checkout.CashierScreen;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class LoginScreen {
	
	private JFrame frame = new JFrame("Cash Register System");
	private JPanel screen = new JPanel();
	private JLabel msgLbl = new JLabel("To be implemented...");
	private JButton mngrBtn = new JButton("Manager");
	private JButton cshrBtn = new JButton("Cashier");
	
	//Array of images that represent employees
	private ImageIcon avatars = new ImageIcon();
	
	public LoginScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		msgLbl.setFont(new Font("Serif", Font.BOLD, 26));
		screen.add(msgLbl);
		
		JPanel btnPanel = new JPanel();
		mngrBtn.setPreferredSize(new Dimension(200, 100));
		mngrBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ManagerScreen();
			}
		});
		btnPanel.add(mngrBtn);
		cshrBtn.setPreferredSize(new Dimension(200, 100));
		cshrBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new CashierScreen();
			}
		});
		btnPanel.add(cshrBtn);
		screen.add(btnPanel);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new LoginScreen();				
			}});

	}
}
