package cs3450.cashregister.Management;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cs3450.cashregister.Checkout.CashierScreen;
import cs3450.cashregister.Databases.EmpDriver;
import cs3450.cashregister.Databases.Employee;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class LoginScreen {
	
	private EmpDriver driver = new EmpDriver();
	private JFrame frame = new JFrame("Cash Register System");
	private JPanel screen = new JPanel();
	private JLabel msgLbl = new JLabel("Click your Image to Login");
	private JButton mngrBtn = new JButton("Manager");
	private JButton cshrBtn = new JButton("Cashier");
	private Employee manager, cashier;
	
	//Array of images that represent employees
	private ImageIcon avatars = new ImageIcon();
	
	public LoginScreen() throws IOException, SQLException{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 500));
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		msgLbl.setFont(new Font("Serif", Font.BOLD, 22));
		msgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(msgLbl);
		screen.add(titlePanel);
		
		manager = new Employee();
		cashier = new Employee();
		cashier.setUsername("cashier");
		cashier.setStatus(false);
		
		JPanel btnPanel = new JPanel();
		
		//uncomment if there are no active users. 
		/*mngrBtn.setPreferredSize(new Dimension(150, 75));
		mngrBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ManagerScreen(manager);
			}
		});
		btnPanel.add(mngrBtn);
		cshrBtn.setPreferredSize(new Dimension(150, 75));
		cshrBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new CashierScreen(cashier);
			}
		});
		btnPanel.add(cshrBtn);
		screen.add(btnPanel);*/
		
		JPanel usrPanel = new JPanel();
		for(Vector<Object> row : driver.getTableData()){
			URL url = new URL((String)row.get(3));
			Image img1 = ImageIO.read(url);
			Image img2 = img1.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img2);
			JButton usrBtn = new JButton(imgIcon);
			usrBtn.setPreferredSize(new Dimension(200, 200));
			Employee emp = new Employee(row);
			addActionListener(emp, usrBtn);
			usrPanel.add(usrBtn);
		}
		screen.add(usrPanel);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void addActionListener(Employee emp, JButton usrBtn) {
				if(emp.isManager()){
					usrBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							JPasswordField pf = new JPasswordField();
							int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password for " + emp.getUsername(), 
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
							if(okCxl == JOptionPane.OK_OPTION){
								String password = new String(pf.getPassword());
								if(password.equals(emp.getCode())){
									frame.dispose();
									new ManagerScreen(emp);
								}
								else{
									JOptionPane.showMessageDialog(null, "Password is incorrect");
								}
							}
						}
					});
				}
				else{
					usrBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							JPasswordField pf = new JPasswordField();
							int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password for " + emp.getUsername(), 
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
							if(okCxl == JOptionPane.OK_OPTION){
								String password = new String(pf.getPassword());
								if(password.equals(emp.getCode())){
									frame.dispose();
									new CashierScreen(emp);
								}
								else{
									JOptionPane.showMessageDialog(null, "Password is incorrect");
								}
							}
						}
					});
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					new LoginScreen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}});

	}
}
