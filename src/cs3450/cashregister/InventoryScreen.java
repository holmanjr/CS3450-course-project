package cs3450.cashregister;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Jason Holman, Boden Archuleta
 * @version 1.0
 */
public class InventoryScreen implements ActionListener{
	
	private UpdateExisting updateScreen;
	private NewProduct newProd;
	private JFrame frame = new JFrame("Inventory Management");
	private JPanel screen = new JPanel();
	private JButton update = new JButton("Update existing");
	private JButton addNew = new JButton("Add new product");
	
	/**
	 *  Constructor to display the inventory screen
	 */
	public InventoryScreen() throws FileNotFoundException
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 15, 0, 15);
		screen.setPreferredSize(new Dimension(600, 400));		
		
		update.setPreferredSize(new Dimension(150, 80));
		update.addActionListener(this);
		screen.add(update, gbc);
		
		addNew.addActionListener(this);
		addNew.setPreferredSize(new Dimension(150, 80));
		screen.add(addNew, gbc);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 * Temporary main method for testing
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					new InventoryScreen();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}				
			}});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton)e.getSource();
		if(tmp == update)
		{
			try {
				updateScreen = new UpdateExisting();
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(tmp == addNew)
		{
			try {
				newProd = new NewProduct();
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
