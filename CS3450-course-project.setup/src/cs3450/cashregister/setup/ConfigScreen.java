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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class ConfigScreen {
	
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Cash Register Application");
	private JLabel infoLbl = new JLabel("Enter your MySQL Database name, hostname, and password.");
	private JTextField dbField = new JTextField();
	private JTextField hostField = new JTextField();
	private JPasswordField passField = new JPasswordField();
	private JButton nextBtn = new JButton("Next");
	private JButton prevBtn = new JButton("Previous");
	Installation installation;

	public ConfigScreen(JFrame frame, Installation install) {
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
		
		JPanel dbPanel = new JPanel();
		dbPanel.setLayout(new BoxLayout(dbPanel, BoxLayout.X_AXIS));
		
		dbPanel.add(new JLabel("DB Name:  "));
		dbPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		dbField.setMaximumSize(new Dimension(500, 32));
		dbPanel.add(dbField);
		
		cntrPanel.add(dbPanel);
		
		JPanel hostPanel = new JPanel();
		hostPanel.setLayout(new BoxLayout(hostPanel, BoxLayout.X_AXIS));
		
		hostPanel.add(new JLabel("Hostname:"));
		hostPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		hostField.setMaximumSize(new Dimension(500, 32));
		hostPanel.add(hostField);
		
		cntrPanel.add(hostPanel);
		
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
				installation.setConfigVariables(dbField.getText(), hostField.getText(), password);
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
