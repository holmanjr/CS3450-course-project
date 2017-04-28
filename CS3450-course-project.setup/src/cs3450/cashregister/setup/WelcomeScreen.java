package cs3450.cashregister.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class WelcomeScreen {
	
	private static JFrame mainframe = new JFrame("Installation Wizard");
	private JPanel screen = new JPanel();
	private JLabel title = new JLabel("Cash Register Application");
	private JLabel infoLbl = new JLabel("This is your installation wizard to help "
			+ "you install the Cash Register Application.");
	private JLabel infoLbl2 = new JLabel("You will need MySQL installed on your machine "
			+ "in order for this program to work.");
	private JButton nextBtn = new JButton("Next");
	
	public WelcomeScreen(){
		this(mainframe);
	}
	
	public WelcomeScreen(JFrame frame){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BorderLayout(10, 10));
		screen.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		title.setFont(new Font("Serif", Font.BOLD, 22));
		screen.add(title, BorderLayout.NORTH);
		
		JPanel cntrPanel = new JPanel();
		cntrPanel.setLayout(new BoxLayout(cntrPanel, BoxLayout.Y_AXIS));
		cntrPanel.add(infoLbl);
		cntrPanel.add(infoLbl2);
		screen.add(cntrPanel, BorderLayout.CENTER);
		
		JPanel bttmPanel = new JPanel();
		bttmPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.remove(screen);
				new LocationScreen(frame, new Installation());
			}
		});
		bttmPanel.add(nextBtn);
		screen.add(bttmPanel, BorderLayout.SOUTH);
		
		pane.add(screen);
		
		frame.setPreferredSize(new Dimension(600, 300));
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
				new WelcomeScreen();				
			}});
	}

}
