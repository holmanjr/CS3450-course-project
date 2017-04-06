package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import cs3450.cashregister.Databases.Employee;
import cs3450.cashregister.Databases.ProdDriver;
import cs3450.cashregister.Management.UpdateProduct;

public class CheckoutScreen implements ActionListener {

	private JFrame frame = new JFrame("Checkout");
	private JPanel screen = new JPanel();
	private JButton addb = new JButton("Add");
	private JButton removeb = new JButton("Remove");
	private JButton pay = new JButton("Pay");
	private JButton startOver = new JButton("Start Over");
	private JButton cancel = new JButton("Cancel");
	public static float total = 0;
	private JLabel tracker = new JLabel("Your current total: " + Float.toString(total));
	private DefaultTableModel model = new DefaultTableModel(); 
	public static JTable table;
	public Employee cashier;
	private ProdDriver driver = new ProdDriver("products");
	private ResultSet rs;
	private int cur = 0;
	

    void addRow() throws SQLException, FileNotFoundException{
    	Object name = JOptionPane.showInputDialog(frame, "name");
		rs = driver.searchByName(name.toString());
		if (!rs.first()){
			JOptionPane.showMessageDialog(frame, name + " is not in stock.");
		}
		else {
			Object amountOrdered = JOptionPane.showInputDialog(frame, "Quantity");
			String amountInStock = rs.getString("qty");
			int newAmount = Integer.parseInt(amountInStock) - Integer.valueOf((String) amountOrdered);
			if (newAmount < 0){
				JOptionPane.showMessageDialog(frame, "There is not enough " + name + "(s) in stock.");
			}
			else {
				String price = rs.getString("price");
				String id = rs.getString("prodid");
				String sup = rs.getString("supplier");
				String contact = rs.getString("contactInfo");
				
		    	model.addRow(new Object[]{cur++, name, price, amountOrdered});
		        total += Float.parseFloat(price) * Integer.parseInt((String) amountOrdered);
		        tracker.setText("Your Current total: " + Float.toString(total));
		        driver.updateRow(Integer.parseInt(id), (String) name, Double.parseDouble(price), newAmount, sup, contact);
			}
		}
    	
    }
    
    void removeRow(){
    	Object row = JOptionPane.showInputDialog(frame, "# of item to remove");
    	model.removeRow(Integer.parseInt((String) row));
    }
    
    void clearAll(){
    	frame.dispose();
    	new CheckoutScreen(cashier);
    }
	
	public CheckoutScreen(Employee cashier){
		// creating cashier employee to pass
		this.cashier = cashier;
		
		//screen layout 
		model.addColumn("#");
		model.addColumn("Name");
		model.addColumn("Price");
		model.addColumn("Quantity");
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(tracker);
		screen.add(topPanel);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		
		//add listeners to the buttons
		pay.addActionListener(this);
		addb.addActionListener(this);
		startOver.addActionListener(this);
		removeb.addActionListener(this);
		cancel.addActionListener(this);
		
		//Add buttons
		panel.add(scrollPane);
		panel.add(addb);
		panel.add(removeb);
		panel.add(startOver);
		panel.add(pay);
		panel.add(cancel); 
		screen.add(panel);

		
		//more screen layout
		pane.add(screen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addb){
			try {
				addRow();
			} catch (FileNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == pay) {
			frame.dispose();
			new PaymentScreen(cashier);
		}
		if(e.getSource() == removeb){
			removeRow();
		}
		if(e.getSource() == startOver){
			clearAll();
		}
		if(e.getSource() == cancel){
			frame.dispose();
			new CashierScreen(cashier);
		}
	}
}

