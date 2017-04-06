package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	private JButton addb = new JButton("Add                     ");
	private JButton removeb = new JButton("Remove                    ");
	private JButton pay = new JButton("Pay");
	private JButton startOver = new JButton("Start Over");
	private JButton cancel = new JButton("Cancel");
	public static float total = 0;
	private DefaultTableModel model = new DefaultTableModel(); 
	public static JTable table;
	public Employee cashier;
	private UpdateProduct prod;
	private ProdDriver driver = new ProdDriver("products");
	private ResultSet rs;
	
	void search(int id){
		
	}

    void addRow() throws SQLException{
    	/*Object newID = JOptionPane.showInputDialog(frame, "id");
        Object newName = JOptionPane.showInputDialog(frame, "Enter name");
        String newPrice = JOptionPane.showInputDialog(frame, "Enter price");
        total += Float.parseFloat(newPrice);
        Object newAmount = JOptionPane.showInputDialog(frame, "Enter amount");
    	model.addRow(new Object[]{newID, newName, newPrice, newAmount});*/
    	
    	Object name = JOptionPane.showInputDialog(frame, "name");
		rs = driver.searchByName(name.toString());
    	
    	String id = rs.getString("prodid");
    	String amount = rs.getString("qty");
    	
    	System.out.println(id + " " + amount);
    	
    }
    
    void removeRow(){
    	model.removeRow(model.getRowCount() - 1);
    }
    
    void clearAll(){
    	for (int i = model.getRowCount() - 1; i > -1; i--){
    		model.removeRow(i);
    	}
    }
	
	public CheckoutScreen(Employee cashier){
		// creating cashier employee to pass
		this.cashier = cashier;
		
		//screen layout 
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Price");
		model.addColumn("Quantity");
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new FlowLayout());
		
		
		//add listeners to the buttons
		pay.addActionListener(this);
		addb.addActionListener(this);
		startOver.addActionListener(this);
		removeb.addActionListener(this);
		cancel.addActionListener(this);
		
		//Add buttons
		screen.add(scrollPane, BorderLayout.PAGE_START);
		screen.add(addb, BorderLayout.WEST);
		screen.add(removeb, BorderLayout.EAST);
		screen.add(pay, BorderLayout.CENTER);
		screen.add(cancel, BorderLayout.PAGE_END); 

		
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
			} catch (SQLException e1) {
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

