package classProject;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainScreen implements ActionListener {
	
	private JFrame frame = new JFrame("Inventory Management");
	private JPanel screen = new JPanel();
	private JButton checkout = new JButton("Go to checkout");
	private JButton inventory = new JButton("Edit Inventory");

	public MainScreen(){
		checkout.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		pane.setPreferredSize(new Dimension(500, 200));
		pane.setSize(50, 50);
		screen.setLayout(new GridLayout());
		
		screen.add(checkout);
		screen.add(inventory);
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.dispose();
        new CheckoutScreen();
	}
}



