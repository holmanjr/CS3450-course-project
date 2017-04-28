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
public class EmpStatusScreen {
	private EmpDriver driver = new EmpDriver();
	private JFrame frame = new JFrame("User Settings");
	private JPanel screen = new JPanel();
	private JLabel posLbl = new JLabel("New Position(Manager/Cashier): ");
	private JTextField posTxtFld = new JTextField(20);
	private JButton saveBtn = new JButton("Save");
	private JButton cancelBtn = new JButton("Cancel");
	
	public EmpStatusScreen(Employee emp, int empid){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel posPanel = new JPanel();
		posPanel.setLayout(new BorderLayout());
		posPanel.add(posLbl, BorderLayout.WEST);
		posPanel.add(posTxtFld, BorderLayout.EAST);
		screen.add(posPanel);
		
		JPanel btnPanel = new JPanel();
		saveBtn.setPreferredSize(new Dimension(100, 25));
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean position;
					if(posTxtFld.getText().equals("Manager")||posTxtFld.getText().equals("manager")){
						position = true;
						driver.setStatus(empid, position);
						JOptionPane.showMessageDialog(null, "New Settings Successfully Changed");
					}
					else if(posTxtFld.getText().equals("Cashier")||posTxtFld.getText().equals("cashier")){
						position = false;
						driver.setStatus(empid, position);
						JOptionPane.showMessageDialog(null, "New Settings Successfully Changed");
					}
					else{
						JOptionPane.showMessageDialog(null, "Postion not recognized");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPanel.add(saveBtn);
		cancelBtn.setPreferredSize(new Dimension(100, 25));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if(emp.isManager()){
							frame.dispose();
						}
						else{
							frame.dispose();
							new DelEmpScreen(emp);
						}
						new DelEmpScreen(emp);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
