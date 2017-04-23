package cs3450.cashregister.Management;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cs3450.cashregister.Databases.EmpDriver;
import cs3450.cashregister.Databases.Employee;

public class DelEmpScreen {
	
	private EmpDriver driver = new EmpDriver();
	private JFrame frame = new JFrame("Edit Employees");
	private JPanel screen = new JPanel();
	private JTable empTable;
	private JScrollPane scrollpane;
	private JLabel idLbl = new JLabel("Enter ID of Employee to Delete: ");
	private JTextField idTxtFld = new JTextField(10);
	private JButton editBtn = new JButton("Edit");
	private JButton delBtn = new JButton("Delete");
	private JButton finBtn = new JButton("Finished");
	
	public DelEmpScreen(Employee emp) throws SQLException{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel tblPanel = new JPanel();
		empTable = new JTable(driver.getTableData(), driver.getColNames());
		empTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
		empTable.setFillsViewportHeight(true);
		scrollpane = new JScrollPane(empTable);
		tblPanel.add(scrollpane);
		screen.add(tblPanel);
		
		JPanel idPanel = new JPanel();
		idPanel.add(idLbl);
		idPanel.add(idTxtFld);
		screen.add(idPanel);
		
		JPanel btnPanel = new JPanel();
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmpStatusScreen(emp, Integer.parseInt(idTxtFld.getText()));
				frame.dispose();
			}
		});
		btnPanel.add(editBtn);
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					driver.deleteEmployee(Integer.parseInt(idTxtFld.getText()));
					new DelEmpScreen(emp);
					frame.dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPanel.add(delBtn);
		finBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmpManScreen(emp);
			}
		});
		btnPanel.add(finBtn);
		screen.add(btnPanel);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
