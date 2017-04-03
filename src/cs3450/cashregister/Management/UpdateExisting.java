package cs3450.cashregister.Management;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import cs3450.cashregister.Databases.ProdDriver;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UpdateExisting implements ActionListener{
	
	private InventoryScreen invScreen;
	private UpdateProduct updProd;
	private ProdDriver driver = new ProdDriver("products");
	private JFrame frame = new JFrame("Choose Existing Product");
	private JPanel screen = new JPanel();
	private JPanel id = new JPanel();
	private JTable table;
	private JScrollPane scrollpane;
	private JLabel label = new JLabel("Enter ID: ");
	private JTextField text = new JTextField(4);
	private JButton submit = new JButton("Submit");
	private JButton back = new JButton("Back");
	
	public UpdateExisting() throws FileNotFoundException, SQLException
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new GridBagLayout());
		id.setLayout(new FlowLayout());
		GridBagConstraints c = new GridBagConstraints();
		screen.setPreferredSize(new Dimension(600, 400));
		
		table = new JTable(driver.getTableData(), driver.getColNames());
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		scrollpane = new JScrollPane(table);
		
		pane.add(screen);
		
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 0;
		c.gridy = 0;
		screen.add(scrollpane, c);
		c.gridy ++;
		id.add(label, c);
		id.add(text, c);
		screen.add(id, c);
		c.gridy ++;
		submit.setPreferredSize(new Dimension(125, 25));
		submit.addActionListener(this);
		screen.add(submit, c);
		c.gridy ++;
		back.setPreferredSize(new Dimension(125, 25));
		back.addActionListener(this);
		screen.add(back, c);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton)e.getSource();
		if(tmp == back)
		{
			try {
				invScreen = new InventoryScreen();
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(tmp == submit)
		{
			try {
				updProd = new UpdateProduct(driver.selectRow(text.getText()));
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}