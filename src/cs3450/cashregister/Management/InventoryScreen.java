package cs3450.cashregister.Management;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cs3450.cashregister.Databases.Employee;

/**
 * @author Jason Holman, Boden Archuleta
 * @version 1.0
 */
public class InventoryScreen implements ActionListener{
	
	private UpdateExisting updateScreen;
	private NewProduct newProd;
	private JFrame frame = new JFrame("Inventory Management");
	private JPanel focusBtns = new JPanel();
	private JPanel screen = new JPanel();
	private JButton update = new JButton("Update existing");
	private JButton addNew = new JButton("Add new product");
	private JButton back = new JButton("Back");
	private Employee emp;
	
	/**
	 *  Constructor to display the inventory screen
	 * @param emp 
	 */
	public InventoryScreen(Employee emp) throws FileNotFoundException
	{
		this.emp = emp;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 15, 10, 15);
		screen.setPreferredSize(new Dimension(600, 400));
		
		focusBtns.setLayout(new GridBagLayout());
		
		update.setPreferredSize(new Dimension(150, 80));
		update.addActionListener(this);
		focusBtns.add(update, gbc);
		
		addNew.addActionListener(this);
		addNew.setPreferredSize(new Dimension(150, 80));
		focusBtns.add(addNew, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		screen.add(focusBtns, gbc);
		
		gbc.gridy ++;
		back.addActionListener(this);
		back.setPreferredSize(new Dimension(150, 25));
		screen.add(back, gbc);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton)e.getSource();
		if(tmp == update)
		{
			try {
				updateScreen = new UpdateExisting(emp);
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(tmp == addNew)
		{
			try {
				newProd = new NewProduct(emp);
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(tmp == back){
			new ManagerScreen(emp);
			frame.dispose();
		}
	}

}