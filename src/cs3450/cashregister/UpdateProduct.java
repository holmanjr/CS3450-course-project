package cs3450.cashregister;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


/**
 * @author Jason Holman Boden Archuleta
 *
 */
public class UpdateProduct {
	
	private JFrame frame = new JFrame("Update Existing Product");
	private JPanel screen = new JPanel();
	private JTable table;
	private String[] columnNames = {"ID", "Name", "Price", "Quantity"};
	private String[][] rows = {
			{"000", "Apple", "2.99", "15"}
	};
	private JScrollPane scrollpane;
	private JButton save = new JButton("Save");
	private JButton cancel = new JButton("Cancel");
	
	public UpdateProduct() throws FileNotFoundException
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = (JPanel)frame.getContentPane();
		
		screen.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		screen.setPreferredSize(new Dimension(600, 400));
		
		table = new JTable(rows, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 16));
		table.setFillsViewportHeight(true);
		
		scrollpane = new JScrollPane(table);
		
		pane.add(screen);
		
		c.insets = new Insets(5, 5, 5, 5);
		
		c.gridx = 0;
		c.gridy = 0;
		screen.add(scrollpane, c);
		c.gridy ++;
		save.setPreferredSize(new Dimension(125, 25));
		screen.add(save, c);
		c.gridy ++;
		cancel.setPreferredSize(new Dimension(125, 25));
		screen.add(cancel, c);
		
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
					new UpdateProduct();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}				
			}});
	}

}
