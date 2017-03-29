package cs3450.cashregister;

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
import javax.swing.SwingConstants;

public class UserScreen {
	
	private JFrame frame = new JFrame("User Settings");
	private JPanel screen = new JPanel();
	private JLabel usrnmLbl = new JLabel("New Username: ");
	private JLabel codeLbl = new JLabel("New Code: ");
	private JLabel pictLbl = new JLabel("Enter New Picture URL/Filepath: ");
	private JTextField usrnmTxtFld = new JTextField(15);
	private JTextField codeTxtFld = new JTextField(15);
	private JTextField pictTxtFld = new JTextField(15);
	private JButton saveBtn = new JButton("Save");
	private JButton cancelBtn = new JButton("Cancel");
	
	public UserScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel usrnmPanel = new JPanel();
		usrnmPanel.setLayout(new BorderLayout());
		usrnmPanel.add(usrnmLbl, BorderLayout.WEST);
		usrnmPanel.add(usrnmTxtFld, BorderLayout.EAST);
		screen.add(usrnmPanel);
		
		JPanel codePanel = new JPanel();
		codePanel.setLayout(new BorderLayout());
		codePanel.add(codeLbl, BorderLayout.WEST);
		codePanel.add(codeTxtFld, BorderLayout.EAST);
		screen.add(codePanel);
		
		JPanel pictPanel = new JPanel();
		pictPanel.setLayout(new BorderLayout());
		pictPanel.add(pictLbl, BorderLayout.WEST);
		pictPanel.add(pictTxtFld, BorderLayout.EAST);
		screen.add(pictPanel);
		
		JPanel btnLabel = new JPanel();
		saveBtn.setPreferredSize(new Dimension(100, 25));
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This functionality is not implemented yet!");
			}
		});
		btnLabel.add(saveBtn);
		cancelBtn.setPreferredSize(new Dimension(100, 25));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ManagerScreen();
			}
		});
		btnLabel.add(cancelBtn);
		screen.add(btnLabel);
		
		pane.add(screen);
		
		frame.pack();	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
