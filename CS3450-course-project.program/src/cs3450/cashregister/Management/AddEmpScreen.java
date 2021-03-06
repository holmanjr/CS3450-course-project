/**
 * 
 */
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

import cs3450.cashregister.Databases.EmpDriver;
import cs3450.cashregister.Databases.Employee;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class AddEmpScreen {
	
	private EmpDriver driver = new EmpDriver();
	private JFrame frame = new JFrame("Add New Employee");
	private JPanel screen = new JPanel();
	private JLabel usrNmLbl, codeLbl, picLbl, posLbl;
	private JTextField usrNmTxtFld, codeTxtFld, picTxtFld, posTxtFld;
	private JButton saveBtn, cancelBtn;
	
	public AddEmpScreen(Employee emp){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel usrnmPanel = new JPanel();
		usrnmPanel.setLayout(new BorderLayout());
		usrNmLbl = new JLabel("Username: ");
		usrnmPanel.add(usrNmLbl, BorderLayout.WEST);
		usrNmTxtFld = new JTextField(15);
		usrnmPanel.add(usrNmTxtFld, BorderLayout.EAST);
		screen.add(usrnmPanel);
		
		JPanel codePanel = new JPanel();
		codePanel.setLayout(new BorderLayout());
		codeLbl = new JLabel("Code: ");
		codePanel.add(codeLbl, BorderLayout.WEST);
		codeTxtFld = new JTextField(15);
		codePanel.add(codeTxtFld, BorderLayout.EAST);
		screen.add(codePanel);
		
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new BorderLayout());
		picLbl = new JLabel("Picture URL: ");
		picPanel.add(picLbl, BorderLayout.WEST);
		picTxtFld = new JTextField(15);
		picPanel.add(picTxtFld, BorderLayout.EAST);
		screen.add(picPanel);
		
		JPanel posPanel = new JPanel();
		posPanel.setLayout(new BorderLayout());
		posLbl = new JLabel("Position (Manager/Cashier)");
		posPanel.add(posLbl, BorderLayout.WEST);
		posTxtFld = new JTextField(15);
		posPanel.add(posTxtFld, BorderLayout.EAST);
		screen.add(posPanel);
		
		JPanel btnPanel = new JPanel();
		saveBtn = new JButton("Save");
		saveBtn.setPreferredSize(new Dimension(100, 25));
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!usrNmTxtFld.getText().isEmpty() && !codeTxtFld.getText().isEmpty() 
						&& !picTxtFld.getText().isEmpty() && !posTxtFld.getText().isEmpty()){
					try {
						if(posTxtFld.getText().equals("Manager")||posTxtFld.getText().equals("manager")
								||posTxtFld.getText().equals("Cashier")||posTxtFld.getText().equals("cashier")){
							driver.addEmployee(usrNmTxtFld.getText(), codeTxtFld.getText(), 
									picTxtFld.getText(), posTxtFld.getText());
							JOptionPane.showMessageDialog(null, "Employee successfully added!");
						}
						else{
							JOptionPane.showMessageDialog(null, "Postion not recognized");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "All values need to be inserted");
				}
			}
		});
		btnPanel.add(saveBtn);
		cancelBtn = new JButton("Back");
		cancelBtn.setPreferredSize(new Dimension(100, 25));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmpManScreen(emp);
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
