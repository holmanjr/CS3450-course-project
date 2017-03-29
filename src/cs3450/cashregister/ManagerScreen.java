package cs3450.cashregister;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * @author Jason Holman Boden Archuleta
 * @version 2.0
 */
public class ManagerScreen {
	
	private JFrame frame = new JFrame("Manager Screen");
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Management Options");
	private JButton usrSttngsBtn = new JButton("User Settings");
	private JButton invBtn = new JButton("Manage Inventory");
	private JButton empBtn = new JButton("Manage Empoyees");
	private JButton logoutBtn = new JButton("Logout");
	
	public ManagerScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		title.setFont(new Font("Serif", Font.BOLD, 26));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(title);
		screen.add(topPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		screen.add(panel);
		
		usrSttngsBtn.setPreferredSize(new Dimension(200, 100));
		usrSttngsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserScreen();
			}
		});
		panel.add(usrSttngsBtn);
		
		invBtn.setPreferredSize(new Dimension(200, 100));
		invBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					new InventoryScreen();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(invBtn);
		
		empBtn.setPreferredSize(new Dimension(200, 100));
		empBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This funtionality has not been implemented yet!");
			}
		});
		panel.add(empBtn);
		screen.add(panel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		logoutBtn.setPreferredSize(new Dimension(150, 25));
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This funtionality has not been implemented yet!");
			}
		});
		bottomPanel.add(logoutBtn, BorderLayout.EAST);
		screen.add(bottomPanel);
		
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
				new ManagerScreen();				
			}});
	}

}
