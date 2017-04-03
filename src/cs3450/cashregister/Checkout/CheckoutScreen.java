package cs3450.cashregister.Checkout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    void addRow(){
        Object newID = JOptionPane.showInputDialog(frame, "Enter ID");
        Object newName = JOptionPane.showInputDialog(frame, "Enter name");
        String newPrice = JOptionPane.showInputDialog(frame, "Enter price");
        total += Float.parseFloat(newPrice);
        Object newAmount = JOptionPane.showInputDialog(frame, "Enter amount");
    	model.addRow(new Object[]{newID, newName, newPrice, newAmount});
    }
    
    void removeRow(){
    	model.removeRow(model.getRowCount() - 1);
    }
    
    void clearAll(){
    	for (int i = model.getRowCount() - 1; i > -1; i--){
    		model.removeRow(i);
    	}
    }
	
	public CheckoutScreen(){
		//screen layout 
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Price");
		model.addColumn("Quantity");
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		JPanel pane = (JPanel)frame.getContentPane();
		screen.setLayout(new BorderLayout());
		pane.setPreferredSize(new Dimension(800, 500));
		pane.setSize(50, 50);
		
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
			addRow();
		}
		if (e.getSource() == pay) {
			frame.dispose();
			new PaymentScreen();
		}
		if(e.getSource() == removeb){
			removeRow();
		}
		if(e.getSource() == startOver){
			clearAll();
		}
		if(e.getSource() == cancel){
			frame.dispose();
			new CashierScreen();
		}
	}
}
