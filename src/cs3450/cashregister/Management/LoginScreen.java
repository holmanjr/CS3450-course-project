/**
 * 
 */
package cs3450.cashregister.Management;

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
	private JLabel msgLbl = new JLabel("To be implemented...");
	private JButton mngrBtn = new JButton("Manager");
//	private URL url;
//	private Image image;
//	private ImageIcon imgIcon;
	private JButton cshrBtn = new JButton("Cashier");
	
	//Array of images that represent employees
	private ImageIcon avatars = new ImageIcon();
	
	public LoginScreen() throws IOException, SQLException{
//		url = new URL("http://icons.iconarchive.com/icons/hopstarter/sleek-xp-software/256/Yahoo-Messenger-icon.png");
//		image = ImageIO.read(url);
//		Image img = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
//		imgIcon = new ImageIcon(img);
//		cshrBtn = new JButton(imgIcon);
		
		
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
		
		JPanel usrPanel = new JPanel();
		for(Vector<Object> row : driver.getTableData()){
			URL url = new URL((String)row.get(3));
			Image img1 = ImageIO.read(url);
			Image img2 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img2);
			JButton usrBtn = new JButton(imgIcon);
			usrBtn.setPreferredSize(new Dimension(100, 100));
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
					frame.dispose();
					new ManagerScreen();
				}
			});
		}
		else{
			usrBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.dispose();
					new CashierScreen();
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
