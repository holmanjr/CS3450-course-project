package cs3450.cashregister.Management;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cs3450.cashregister.Databases.Employee;
import cs3450.cashregister.Databases.ProdDriver;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UpdateExisting implements ActionListener{
	
	private ProdDriver driver = new ProdDriver("products");
	private JFrame frame = new JFrame("Choose Existing Product");
	private JPanel screen = new JPanel();
	private JPanel id = new JPanel();
	private JTable table;
	private JScrollPane scrollpane;
	private JLabel label = new JLabel("Enter ID: ");
	private JTextField text = new JTextField(4);
	private JButton submit = new JButton("Submit");
	private JButton remove = new JButton("Remove");
	private JButton back = new JButton("Back");
	private Employee emp;
	
	public UpdateExisting(Employee emp) throws FileNotFoundException, SQLException
	{
		this.emp = emp;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		id.setLayout(new FlowLayout());
		
		JPanel tblPanel = new JPanel();
		table = new JTable(driver.getTableData(), driver.getColNames());
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		scrollpane = new JScrollPane(table);
		tblPanel.add(scrollpane);
		screen.add(tblPanel);
		
		id.add(label);
		id.add(text);
		screen.add(id);
		JPanel btnPanel = new JPanel();
		submit.setPreferredSize(new Dimension(125, 25));
		submit.addActionListener(this);
		btnPanel.add(submit);
		remove.setPreferredSize(new Dimension(125, 25));
		remove.addActionListener(this);
		btnPanel.add(remove);
		back.setPreferredSize(new Dimension(125, 25));
		back.addActionListener(this);
		btnPanel.add(back);
		screen.add(btnPanel);
		
		pane.add(screen);
		
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
				new InventoryScreen(emp);
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(tmp == submit)
		{
			try {
				new UpdateProduct(driver.selectRow(text.getText()), emp);
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(tmp == remove){
			try {
				driver.deleteProduct(Integer.parseInt(text.getText()));
				frame.dispose();
				new UpdateExisting(emp);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}