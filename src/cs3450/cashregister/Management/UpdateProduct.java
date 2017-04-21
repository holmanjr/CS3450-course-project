package cs3450.cashregister.Management;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cs3450.cashregister.Databases.Employee;
import cs3450.cashregister.Databases.ProdDriver;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UpdateProduct implements ActionListener{
	
	ProdDriver driver = new ProdDriver("products");
	private JFrame frame = new JFrame("Update Existing Product");
	private JLabel nameLbl = new JLabel("Name: ", SwingConstants.CENTER);
	private JLabel priceLbl = new JLabel("Price: ", SwingConstants.CENTER);
	private JLabel qtyLbl = new JLabel("Qty: ", SwingConstants.CENTER);
	private JLabel suppLbl = new JLabel("Supplier: ");
	private JLabel contLbl = new JLabel("Contact Info: ");
	private JTextField nameTxtFld = new JTextField(10);
	private JTextField priceTxtFld = new JTextField(10);
	private JTextField qtyTxtFld = new JTextField(10);
	private JTextField suppTxtFld = new JTextField(10);
	private JTextField contTxtFld = new JTextField(10);
	private JPanel screen = new JPanel();
	private JButton save = new JButton("Save");
	private JButton back = new JButton("Back");
	private Integer id;
	private Employee emp;
	
	public UpdateProduct(ResultSet rs, Employee emp) throws FileNotFoundException, SQLException
	{
		this.emp = emp;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel)frame.getContentPane();
		
		id = Integer.parseInt(rs.getString("prodid"));
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));	
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLbl, BorderLayout.WEST);
		nameTxtFld.setText(rs.getString("name"));
		namePanel.add(nameTxtFld, BorderLayout.EAST);
		screen.add(namePanel);
		
		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		pricePanel.add(priceLbl, BorderLayout.WEST);
		priceTxtFld.setText(rs.getString("price"));
		pricePanel.add(priceTxtFld, BorderLayout.EAST);
		screen.add(pricePanel);
		
		JPanel qtyPanel = new JPanel();
		qtyPanel.setLayout(new BorderLayout());
		qtyPanel.add(qtyLbl, BorderLayout.WEST);
		qtyTxtFld.setText(rs.getString("qty"));
		qtyPanel.add(qtyTxtFld, BorderLayout.EAST);
		screen.add(qtyPanel);
		
		JPanel suppPanel = new JPanel();
		suppPanel.setLayout(new BorderLayout());
		suppPanel.add(suppLbl, BorderLayout.WEST);
		suppTxtFld.setText(rs.getString("supplier"));
		suppPanel.add(suppTxtFld, BorderLayout.EAST);
		screen.add(suppPanel);
		
		JPanel contPanel = new JPanel();
		contPanel.setLayout(new BorderLayout());
		contPanel.add(contLbl, BorderLayout.WEST);
		contTxtFld.setText(rs.getString("contactInfo"));
		contPanel.add(contTxtFld, BorderLayout.EAST);
		screen.add(contPanel);
		
		JPanel btnPanel = new JPanel();
		save.setPreferredSize(new Dimension(125, 25));
		save.addActionListener(this);
		btnPanel.add(save);
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
		if(tmp == save)
		{
			try {
				driver.updateRow(id, nameTxtFld.getText(), Double.parseDouble(priceTxtFld.getText()), 
						Integer.parseInt(qtyTxtFld.getText()), suppTxtFld.getText(), contTxtFld.getText());
				new UpdateExisting(emp);
				frame.dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(tmp == back)
		{
			try {
				new UpdateExisting(emp);
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
