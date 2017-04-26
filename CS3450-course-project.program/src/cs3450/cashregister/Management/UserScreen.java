package cs3450.cashregister.Management;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cs3450.cashregister.Checkout.CashierScreen;
import cs3450.cashregister.Databases.EmpDriver;
import cs3450.cashregister.Databases.Employee;

public class UserScreen {
	
	private EmpDriver driver = new EmpDriver();
	private JFrame frame = new JFrame("User Settings");
	private JPanel screen = new JPanel();
	private JLabel usrnmLbl = new JLabel("New Username: ");
	private JLabel codeLbl = new JLabel("New Code: ");
	private JLabel pictLbl = new JLabel("Enter New Picture URL: ");
	private JTextField usrnmTxtFld = new JTextField(20);
	private JTextField codeTxtFld = new JTextField(20);
	private JTextField pictTxtFld = new JTextField(20);
	private JButton saveBtn = new JButton("Save");
	private JButton cancelBtn = new JButton("Cancel");
	
	public UserScreen(Employee emp){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel usrnmPanel = new JPanel();
		usrnmPanel.setLayout(new BorderLayout());
		usrnmPanel.add(usrnmLbl, BorderLayout.WEST);
		usrnmPanel.add(usrnmTxtFld, BorderLayout.EAST);
		usrnmTxtFld.setText(emp.getUsername());
		screen.add(usrnmPanel);
		
		JPanel codePanel = new JPanel();
		codePanel.setLayout(new BorderLayout());
		codePanel.add(codeLbl, BorderLayout.WEST);
		codePanel.add(codeTxtFld, BorderLayout.EAST);
		codeTxtFld.setText(emp.getCode());
		screen.add(codePanel);
		
		JPanel pictPanel = new JPanel();
		pictPanel.setLayout(new BorderLayout());
		pictPanel.add(pictLbl, BorderLayout.WEST);
		pictPanel.add(pictTxtFld, BorderLayout.EAST);
		pictTxtFld.setText(emp.getImageURL());
		screen.add(pictPanel);
		
		JPanel btnPanel = new JPanel();
		saveBtn.setPreferredSize(new Dimension(100, 25));
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					driver.updateEmployee(emp.getId(), usrnmTxtFld.getText(), 
							codeTxtFld.getText(), pictTxtFld.getText());
					emp.setUsername(usrnmTxtFld.getText());
					emp.setCode(codeTxtFld.getText());
					emp.setImageURL(pictTxtFld.getText());
					JOptionPane.showMessageDialog(null, "New Settings Successfully Changed");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(emp.isManager()){
					frame.dispose();
					new ManagerScreen(emp);
				}
				else{
					frame.dispose();
					new CashierScreen(emp);
				}
			}
		});
		btnPanel.add(saveBtn);
		cancelBtn.setPreferredSize(new Dimension(100, 25));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(emp.isManager()){
					frame.dispose();
					new ManagerScreen(emp);
				}
				else{
					frame.dispose();
					new CashierScreen(emp);
				}
			}
		});
		btnPanel.add(cancelBtn);
		screen.add(btnPanel);
		
		pane.add(screen);
		
		frame.pack();	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
