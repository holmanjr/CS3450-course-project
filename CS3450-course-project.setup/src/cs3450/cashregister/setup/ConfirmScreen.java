/**
 * 
 */
package cs3450.cashregister.setup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class ConfirmScreen {
	
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Cash Register Application");
	private JTextArea textArea = new JTextArea();
	private JButton nextBtn = new JButton("Install");
	private JButton prevBtn = new JButton("Previous");
	Installation installation;

	public ConfirmScreen(JFrame frame, Installation install) {
		JPanel pane = (JPanel)frame.getContentPane();
		installation = install;
		
		screen.setLayout(new BorderLayout(10, 10));
		screen.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		title.setFont(new Font("Serif", Font.BOLD, 22));
		screen.add(title, BorderLayout.NORTH);
		
		JPanel cntrPanel = new JPanel();
		cntrPanel.setLayout(new BoxLayout(cntrPanel, BoxLayout.Y_AXIS));
		screen.add(cntrPanel, BorderLayout.CENTER);
		
		String statusText = installation.getInstallationSetting();
		textArea.setText(statusText);
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		cntrPanel.add(scrollpane);
		
		JPanel bttmPanel = new JPanel();
		bttmPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		
		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.remove(screen);
				new ConfigScreen(frame, installation);
			}
		});
		bttmPanel.add(prevBtn);
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				installation.install();
				pane.remove(screen);
				new RunScreen(frame, installation);
			}
		});
		bttmPanel.add(nextBtn);
		
		screen.add(bttmPanel, BorderLayout.SOUTH);
		
		pane.add(screen);
		
		frame.pack();
		frame.setVisible(true);
	}

}
