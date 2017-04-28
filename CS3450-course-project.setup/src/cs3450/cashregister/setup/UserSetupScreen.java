/**
 * 
 */
package cs3450.cashregister.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UserSetupScreen {
	
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Cash Register Application");
	private JLabel infoLbl = new JLabel("Create a username and password for the admin user.");
	private JTextField userField = new JTextField();
	private JPasswordField passField = new JPasswordField();
	private JButton nextBtn = new JButton("Next");
	private JButton prevBtn = new JButton("Previous");
	Installation installation;

	public UserSetupScreen(JFrame frame, Installation install) {
		JPanel pane = (JPanel)frame.getContentPane();
		installation = install;
		
		screen.setLayout(new BorderLayout(10, 10));
		screen.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		title.setFont(new Font("Serif", Font.BOLD, 22));
		screen.add(title, BorderLayout.NORTH);
		
		JPanel cntrPanel = new JPanel();
		cntrPanel.setLayout(new BoxLayout(cntrPanel, BoxLayout.Y_AXIS));
		infoLbl.setFont(new Font("Serif", Font.PLAIN, 16));
		cntrPanel.add(infoLbl);
		
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
		
		userPanel.add(new JLabel("Username:"));
		userPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		userField.setMaximumSize(new Dimension(500, 32));
		userPanel.add(userField);
		
		cntrPanel.add(userPanel);
		
		JPanel passPanel = new JPanel();
		passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.X_AXIS));
		
		passPanel.add(new JLabel("Password:"));
		passPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		passField.setMaximumSize(new Dimension(500, 32));
		passPanel.add(passField);
		
		cntrPanel.add(passPanel);
		
		screen.add(cntrPanel, BorderLayout.CENTER);
		
		JPanel bttmPanel = new JPanel();
		bttmPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		
		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.remove(screen);
				new LocationScreen(frame, installation);
			}
		});
		bttmPanel.add(prevBtn);
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(passField.getPassword());
				installation.setUserVariables(userField.getText(), password);
				pane.remove(screen);
				new ConfirmScreen(frame, installation);
			}
		});
		bttmPanel.add(nextBtn);
		
		screen.add(bttmPanel, BorderLayout.SOUTH);
		
		pane.add(screen);
		
		frame.pack();
		frame.setVisible(true);
	}

}
