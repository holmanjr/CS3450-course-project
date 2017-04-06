package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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

public class ReturnScreen implements ActionListener{
	
	private JButton search = new JButton("Search");
	private JButton cancel = new JButton("Cancel");
	private JLabel label = new JLabel("Enter the name of the item");
	private JTextField field = new JTextField();
	private JFrame frame = new JFrame("Cashier");
	private JPanel upperScreen = new JPanel();
	private JPanel lowerScreen = new JPanel();
	private Employee cashier;
	private ProdDriver driver = new ProdDriver("products");
	private ResultSet rs;

	
	public void returnProd() throws SQLException{
		String item = field.getText();
		rs = driver.searchByName(item);
		if (!rs.first()){
			JOptionPane.showMessageDialog(frame, item + " is not in our database");
		}
		else {
	    	Object amount = JOptionPane.showInputDialog(frame, "How many do you want to return?");
	    	String price = rs.getString("price");
			String id = rs.getString("prodid");
			String sup = rs.getString("supplier");
			String contact = rs.getString("contactInfo");
			String qty = rs.getString("qty");
			int newAmount = Integer.parseInt(qty) + Integer.parseInt((String) amount);
	        driver.updateRow(Integer.parseInt(id), (String) item, Double.parseDouble(price), newAmount, sup, contact);
	        float returnVal = Float.parseFloat((String) amount) * Float.parseFloat(price);
			JOptionPane.showMessageDialog(frame, returnVal + " is your total return.");
		}
	}
	
	ReturnScreen(Employee cashier){
		//creating cashier employee to pass
		this.cashier = cashier;
		
		//page layout 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel)frame.getContentPane();
		
		//set up the upper screen
		upperScreen.setLayout(new BoxLayout(upperScreen, BoxLayout.LINE_AXIS));
		label.setPreferredSize(new Dimension(200, 100));
		field.setPreferredSize(new Dimension(200, 100));
		upperScreen.add(label);
		upperScreen.add(field);
		pane.add(upperScreen, BorderLayout.CENTER);
		
		//set up second screensss
		lowerScreen.setLayout(new BoxLayout(lowerScreen, BoxLayout.LINE_AXIS));		
		search.setPreferredSize(new Dimension(200, 100));
		cancel.setPreferredSize(new Dimension(200, 100));
		lowerScreen.add(search);
		lowerScreen.add(cancel);
		search.addActionListener(this);
		cancel.addActionListener(this);
		pane.add(lowerScreen, BorderLayout.PAGE_END);
		
		//more layouts
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			try {
				returnProd();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == cancel){
			frame.dispose();
			new CashierScreen(cashier);
		}
	}

}
