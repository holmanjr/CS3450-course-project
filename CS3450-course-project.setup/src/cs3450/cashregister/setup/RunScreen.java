/**
 * 
 */
package cs3450.cashregister.setup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

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
public class RunScreen {
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Cash Register Application");
	private JButton doneBtn = new JButton("Done");
	Installation installation;

	public RunScreen(JFrame frame, Installation install) {
		JPanel pane = (JPanel)frame.getContentPane();
		installation = install;
		
		screen.setLayout(new BorderLayout(10, 10));
		screen.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		title.setFont(new Font("Serif", Font.BOLD, 22));
		screen.add(title, BorderLayout.NORTH);
		
		JPanel cntrPanel = new JPanel();
		cntrPanel.setLayout(new BoxLayout(cntrPanel, BoxLayout.Y_AXIS));
		screen.add(cntrPanel, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(installation.getInstallationStatus());
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		cntrPanel.add(scrollpane);
		
		JPanel bttmPanel = new JPanel();
		bttmPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		
		doneBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		bttmPanel.add(doneBtn);
		
		if(installation.isSuccessful()){
			JButton runBtn = new JButton("Run");
			runBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try{
						Process p = Runtime.getRuntime().exec("cmd /c program.jar", null,
								new File(installation.getInstallationLocation()));
					}catch (IOException e1){
						e1.printStackTrace();
					}
					
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			});
			bttmPanel.add(runBtn);
		}
		
		screen.add(bttmPanel, BorderLayout.SOUTH);
		
		pane.add(screen);
		
		frame.pack();
		frame.setVisible(true);
	}

}
