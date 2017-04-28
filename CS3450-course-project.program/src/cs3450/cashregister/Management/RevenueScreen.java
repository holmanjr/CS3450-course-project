package cs3450.cashregister.Management;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs3450.cashregister.Databases.Employee;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class RevenueScreen {
	private JFrame frame = new JFrame("Revenue Report");
	private JPanel screen = new JPanel();
	//private OrderDriver driver = new OrderDriver();
	private JButton searchBtn = new JButton("Search");
	private JButton backBtn = new JButton("Back");
	private JLabel idLbl = new JLabel("Customer/Product ID: ");
	private JLabel strtDateLbl = new JLabel("Start Date: ");
	private JLabel endDateLbl = new JLabel("End Date: ");
	private JTextField idTxtFld = new JTextField(10);
	private JTextField strtDateTxtFld = new JTextField(10);
	private JTextField endDateTxtFld = new JTextField(10);
	
	public RevenueScreen(Employee emp){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new BorderLayout());
		idPanel.add(idLbl, BorderLayout.WEST);
		idPanel.add(idTxtFld, BorderLayout.EAST);
		screen.add(idPanel);
		
		JPanel strtDatePnl = new JPanel();
		strtDatePnl.setLayout(new BorderLayout());
		strtDatePnl.add(strtDateLbl, BorderLayout.WEST);
		strtDatePnl.add(strtDateTxtFld, BorderLayout.EAST);
		screen.add(strtDatePnl);
		
		JPanel endDatePnl = new JPanel();
		endDatePnl.setLayout(new BorderLayout());
		endDatePnl.add(endDateLbl, BorderLayout.WEST);
		endDatePnl.add(endDateTxtFld, BorderLayout.EAST);
		screen.add(endDatePnl);
		
		JPanel btnPanel = new JPanel();
		searchBtn.setPreferredSize(new Dimension(125, 25));
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Not Implemented yet!");
			}
		});
		btnPanel.add(searchBtn);
		backBtn.setPreferredSize(new Dimension(125, 25));
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerScreen(emp);
				frame.dispose();
			}
		});
		btnPanel.add(backBtn);
		screen.add(btnPanel);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
