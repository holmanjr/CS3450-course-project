/**
 * 
 */
package cs3450.cashregister;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UpdateExisting {
	
	private JFrame frame = new JFrame("Update Existing Product");
	private JPanel screen = new JPanel();
	private JPanel id = new JPanel();
	private JTable table;
	private String[] columnNames = {"ID", "Name", "Price", "Quantity"};
	private String[][] rows = {
			{"000", "Apple", "2.99", "15"}
	};
	private JScrollPane scrollpane;
	private JLabel label = new JLabel("Enter ID: ");
	private JTextField text = new JTextField(4);
	private JButton back = new JButton("Back");
	
	private UpdateExisting() throws FileNotFoundException
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new GridBagLayout());
		id.setLayout(new FlowLayout());
		GridBagConstraints c = new GridBagConstraints();
		screen.setPreferredSize(new Dimension(600, 400));
		
		table = new JTable(rows, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		scrollpane = new JScrollPane(table);
		
		pane.add(screen);
		
		c.gridx = 0;
		c.gridy = 0;
		screen.add(scrollpane, c);
		c.gridy ++;
		id.add(label, c);
		id.add(text, c);
		screen.add(id, c);
		c.gridy ++;
		screen.add(back, c);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					new UpdateExisting();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}				
			}});
	}

}
