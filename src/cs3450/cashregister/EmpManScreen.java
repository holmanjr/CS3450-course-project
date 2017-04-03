/**
 * 
 */
package cs3450.cashregister;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Jason
 *
 */
public class EmpManScreen {
	
	private JFrame frame = new JFrame("Employee Management");
	private JPanel screen = new JPanel();
	private JButton addEmpBtn = new JButton("Add Employee");
	private JButton delEmpBtn = new JButton("Delete Employee");
	private JButton backBtn = new JButton("Back");
	
	public EmpManScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel btnPanel = new JPanel();
		
		addEmpBtn.setPreferredSize(new Dimension(200, 100));
		addEmpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AddEmpScreen();
			}
		});
		btnPanel.add(addEmpBtn);
		delEmpBtn.setPreferredSize(new Dimension(200, 100));
		delEmpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					new DelEmpScreen();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPanel.add(delEmpBtn);
		screen.add(btnPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		backBtn.setPreferredSize(new Dimension(150, 25));
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ManagerScreen();
			}
		});
		bottomPanel.add(backBtn, BorderLayout.EAST);
		screen.add(bottomPanel);
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
