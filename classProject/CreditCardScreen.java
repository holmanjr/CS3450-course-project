package classProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreditCardScreen implements ActionListener {
	private JFrame frame = new JFrame("Payment Information");
	private JPanel screen = new JPanel();
	private JButton confirm = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	
	public CreditCardScreen(){
		confirm.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new GridLayout());
		pane.setSize(50, 50);
		
		screen.add(confirm);
		screen.add(cancel); 
		
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new ReceiptScreen();
	}

}
