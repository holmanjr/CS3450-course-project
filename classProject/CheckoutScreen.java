package classProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CheckoutScreen implements ActionListener {

	private JFrame frame = new JFrame("Inven");
	private JPanel screen = new JPanel();
	private JPanel chartScreen = new JPanel();
	private JButton addb = new JButton("Add");
	private JButton removeb = new JButton("Remove");
	private JButton pay = new JButton("Pay");
	private JButton startOver = new JButton("Start Over");

	
	public CheckoutScreen(){
		pay.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new GridLayout());
		chartScreen.setLayout(new GridLayout());
		pane.setSize(50, 50);
		
		//Add buttons 
		screen.add(addb);
		screen.add(removeb);
		screen.add(pay);
		screen.add(startOver);
		
		pane.add(chartScreen);
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new PaymentScreen();
	}
}
