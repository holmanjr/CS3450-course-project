package cs3450.cashregister;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DelEmpScreen {
	
	private JFrame frame = new JFrame("Delete Employees");
	private JPanel screen = new JPanel();
	private JTable empTable;
	private JLabel idLbl = new JLabel("Enter ID of Employee to Delete: ");
	private JTextField idTxtFld = new JTextField(10);
	private JButton delBtn = new JButton("Delete");
	private JButton finBtn = new JButton("Finished");
	
	public DelEmpScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));
		
		JPanel tblPanel = new JPanel();
		
		
		pane.add(screen);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
