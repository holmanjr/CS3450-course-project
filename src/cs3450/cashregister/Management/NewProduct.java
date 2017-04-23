package cs3450.cashregister.Management;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs3450.cashregister.Databases.Employee;
import cs3450.cashregister.Databases.ProdDriver;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class NewProduct implements ActionListener{
	
	private JFrame frame = new JFrame("Add New Product");
	private InventoryScreen invScreen;
	private ProdDriver driver = new ProdDriver("products");
	private JLabel nameLbl = new JLabel("Name: ");
	private JLabel priceLbl = new JLabel("Price: ");
	private JLabel qtyLbl = new JLabel("Qty: ");
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
	private Employee emp;
	
	public NewProduct(Employee emp) throws FileNotFoundException
	{
		this.emp = emp;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));	
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		namePanel.add(nameLbl, BorderLayout.WEST);
		namePanel.add(nameTxtFld, BorderLayout.EAST);
		screen.add(namePanel);
		
		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		pricePanel.add(priceLbl, BorderLayout.WEST);
		pricePanel.add(priceTxtFld, BorderLayout.EAST);
		screen.add(pricePanel);
		
		JPanel qtyPanel = new JPanel();
		qtyPanel.setLayout(new BorderLayout());
		qtyPanel.add(qtyLbl, BorderLayout.WEST);
		qtyPanel.add(qtyTxtFld, BorderLayout.EAST);
		screen.add(qtyPanel);
		
		JPanel suppPanel = new JPanel();
		suppPanel.setLayout(new BorderLayout());
		suppPanel.add(suppLbl, BorderLayout.WEST);
		suppPanel.add(suppTxtFld, BorderLayout.EAST);
		screen.add(suppPanel);
		
		JPanel contPanel = new JPanel();
		contPanel.setLayout(new BorderLayout());
		contPanel.add(contLbl, BorderLayout.WEST);
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
			if(!nameTxtFld.getText().isEmpty() && !priceTxtFld.getText().isEmpty() && !qtyTxtFld.getText().isEmpty()){
				try {
					driver.addRow(nameTxtFld.getText(), priceTxtFld.getText(), qtyTxtFld.getText(),
							suppTxtFld.getText(), contTxtFld.getText());
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
			else{
				JOptionPane.showMessageDialog(null, "All values need to be inserted");
			}
		}
		else if(tmp == back)
		{
			try {
				invScreen = new InventoryScreen(emp);
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}