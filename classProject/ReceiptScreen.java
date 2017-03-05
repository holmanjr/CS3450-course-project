package classProject;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReceiptScreen {

	private JFrame frame = new JFrame("Your Receipt");
	private JPanel screen = new JPanel();
	private JButton print = new JButton("Print");

	
	public ReceiptScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new GridLayout());
		pane.setSize(50, 50);
		
		screen.add(print); 
		
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
