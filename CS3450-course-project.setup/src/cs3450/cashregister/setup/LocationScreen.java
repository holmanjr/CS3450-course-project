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
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Jason
 *
 */
public class LocationScreen {
	
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Cash Register Application");
	private JButton nextBtn = new JButton("Next");
	private JButton prevBtn = new JButton("Previous");
	private JTextField pathField = new JTextField();
	private JButton browseBtn = new JButton("Browse");
	private JFileChooser fileChooser = new JFileChooser();
	Installation installation;

	public LocationScreen(JFrame frame, Installation installation) {
		JPanel pane = (JPanel)frame.getContentPane();
		this.installation = installation;
		
		screen.setLayout(new BorderLayout(10, 10));
		screen.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		title.setFont(new Font("Serif", Font.BOLD, 22));
		screen.add(title, BorderLayout.NORTH);
		
		JPanel cntrPanel = new JPanel();
		cntrPanel.setLayout(new BoxLayout(cntrPanel, BoxLayout.Y_AXIS));
		screen.add(cntrPanel, BorderLayout.CENTER);
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JPanel filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
		
		filePanel.add(new JLabel("Installation path:"));
		filePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		pathField.setText(installation.getInstallationLocation());
		pathField.setMaximumSize(new Dimension(600, 32));
		filePanel.add(pathField);
		filePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		browseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(frame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					String path = file.getAbsolutePath();
					pathField.setText(path);
					Installation.setInstallationLocation(path);
				}
			}
		});
		filePanel.add(browseBtn);
		
		JPanel bttmPanel = new JPanel();
		bttmPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		
		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.remove(screen);
				new WelcomeScreen(frame);
			}
		});
		bttmPanel.add(prevBtn);
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Not impemented yet!");
			}
		});
		bttmPanel.add(nextBtn);
		
		screen.add(bttmPanel, BorderLayout.SOUTH);
		
		cntrPanel.add(filePanel);
		
		pane.add(screen);
		
		frame.pack();
		frame.setVisible(true);
	}

}
