package cs3450.cashregister.Management;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cs3450.cashregister.Databases.ProdDriver;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UpdateProduct implements ActionListener{
	
	ProdDriver driver = new ProdDriver("products");
	private JFrame frame = new JFrame("Update Existing Product");
	private JPanel lblPanel = new JPanel();
	private JLabel nameLbl = new JLabel("Name", SwingConstants.CENTER);
	private JLabel priceLbl = new JLabel("Price", SwingConstants.CENTER);
	private JLabel qtyLbl = new JLabel("Qty", SwingConstants.CENTER);
	private JPanel txtPanel = new JPanel();
	private JTextField nameTxtFld = new JTextField(10);
	private JTextField priceTxtFld = new JTextField(10);
	private JTextField qtyTxtFld = new JTextField(10);
	private JPanel screen = new JPanel();
	private JButton save = new JButton("Save");
	private JButton back = new JButton("Back");
	private Integer id;
	
	public UpdateProduct(ResultSet rs) throws FileNotFoundException, SQLException
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel)frame.getContentPane();
		
		id = Integer.parseInt(rs.getString("prodid"));
		
		screen.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		screen.setPreferredSize(new Dimension(600, 400));	

		lblPanel.setLayout(new FlowLayout());
		nameLbl.setPreferredSize(new Dimension(115, 15));
		lblPanel.add(nameLbl, c);
		priceLbl.setPreferredSize(new Dimension(115, 15));
		lblPanel.add(priceLbl, c);
		qtyLbl.setPreferredSize(new Dimension(115, 15));
		lblPanel.add(qtyLbl, c);
		
		nameTxtFld.setText(rs.getString("name"));
		priceTxtFld.setText(rs.getString("price"));
		qtyTxtFld.setText(rs.getString("qty"));
		txtPanel.setLayout(new FlowLayout());
		txtPanel.add(nameTxtFld, c);
		txtPanel.add(priceTxtFld, c);
		txtPanel.add(qtyTxtFld, c);
		
		pane.add(screen);
		
		c.insets = new Insets(0, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		screen.add(lblPanel, c);
		c.insets = new Insets(5, 5, 5, 5);
		c.gridy ++;
		screen.add(txtPanel, c);
		c.gridy ++;
		save.setPreferredSize(new Dimension(125, 25));
		save.addActionListener(this);
		screen.add(save, c);
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
		if(tmp == save)
		{
			try {
				driver.updateRow(id, nameTxtFld.getText(), 
						Double.parseDouble(priceTxtFld.getText()), Integer.parseInt(qtyTxtFld.getText()));
				new UpdateExisting();
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
				new UpdateExisting();
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
