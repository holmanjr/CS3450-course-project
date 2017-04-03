package cs3450.cashregister;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class NewProduct implements ActionListener{
	
	private JFrame frame = new JFrame("Add New Product");
	private InventoryScreen invScreen;
	private ProdDriver driver = new ProdDriver("products");
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
	
	public NewProduct() throws FileNotFoundException
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
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
			if(!nameTxtFld.getText().isEmpty() && !priceTxtFld.getText().isEmpty() && !qtyTxtFld.getText().isEmpty()){
				try {
					driver.addRow(nameTxtFld.getText(), priceTxtFld.getText(), qtyTxtFld.getText());
					new UpdateExisting();
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
				invScreen = new InventoryScreen();
				frame.dispose();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}