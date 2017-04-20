package catcombo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame {
	
	static int INPUT_COLUMN_WIDTH = 20;
	
	static int WINDOW_WIDTH = 900;
	static int WINDOW_HEIGHT = 600;
	
	private Matcher matcher;
	private JComboBox<String> effects = new JComboBox<>();
	private JComboBox<String> strengths = new JComboBox<>();
	private JTextField input = new JTextField(INPUT_COLUMN_WIDTH);
	private JButton search = new JButton("Search");
	private JButton reset = new JButton("Reset");
	
	private DefaultTableCellRenderer headerRenderer;
	private DefaultTableCellRenderer columnRenderer;
	
	private class JTableModel extends DefaultTableModel {
		public JTableModel() {
		      super();
		}
		
		public boolean isCellEditable(int row, int column) {
		     return false;
		}
	};
	
	private JTableModel model = new JTableModel();
	private JTable output = new JTable(model);
	String[] column_names = {"Units", "Effect", "Strength"};
	
	private String[] generateList(HashMap<Integer, String> map) {
		String str[] = map.values().toArray(new String[0]);
		String list[] = new String[str.length+1];
		for (int i = 0; i < str.length; i++) {
			list[i+1] = str[i];
		}
		list[0] = new String("All");
		return list;
	}
	
	private void addItems(JComboBox<String> combo_box, HashMap<Integer, String> map) {
		String items[] = generateList(map);
		for (int i = 0; i < items.length; i++) {
			combo_box.addItem(items[i]);
		}
	}
	
	void renderTable() {
		for (int i = 0; i < 3; i++) {
			output.getColumnModel().getColumn(i).setCellRenderer(columnRenderer);
		}
		output.getTableHeader().setDefaultRenderer(headerRenderer);
		output.getColumnModel().getColumn(0).setPreferredWidth(WINDOW_WIDTH - 365);
		output.getColumnModel().getColumn(1).setPreferredWidth(255);
		output.getColumnModel().getColumn(2).setPreferredWidth(90);
	}
	
	private void display(String name, String effect, String strength) {
		model.setDataVector(matcher.search(name, effect, strength), column_names);
		renderTable();
	}
	
	public MainWindow(String combo_file_name, String cat_file_name) {

		matcher = new Matcher(Combo.parseCombos(combo_file_name, cat_file_name));
		display("", "All", "All");
		
		setLayout(new BorderLayout(20, 20));
		
		{ // top panel
			JPanel top = new JPanel();
			top.setLayout(new FlowLayout());
			
			addItems(effects, Matcher.effects);
			addItems(strengths, Matcher.strengths);
			
			ActionListener listener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					display(input.getText(), (String) effects.getSelectedItem(), (String) strengths.getSelectedItem());
				}
			};
			
			reset.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					effects.setSelectedIndex(0);
					strengths.setSelectedIndex(0);
					input.setText("");
					display("", "All", "All");
				}
			});
			
			effects.addActionListener(listener);
			strengths.addActionListener(listener);
			search.addActionListener(listener);
			
			top.add(new JLabel("Enter cat name:"));
			top.add(input);
			top.add(effects);
			top.add(strengths);
			top.add(search);
			top.add(reset);
			
			this.add(top, BorderLayout.NORTH);
		}
		
		{ // bottom panel
			JPanel bot = new JPanel();
			JScrollPane scroll = new JScrollPane(output); 
			scroll.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, WINDOW_HEIGHT - 75));
			 
			headerRenderer = new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object
						value, boolean isSelected, boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                
					setBackground(new Color(240, 240, 240));
					Border border = BorderFactory.createLineBorder(Color.black);
					setHorizontalAlignment(JLabel.CENTER);
					setBorder(border);
					return this;
	           	 	}
			};
			
			columnRenderer = new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object
						value, boolean isSelected, boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                
					setHorizontalAlignment(JLabel.CENTER);
					return this;
	           	 	}
			};
			
			renderTable();
	
			output.setRowSelectionAllowed(false);
			output.setColumnSelectionAllowed(false);
			output.setEnabled(false);
			
			bot.add(scroll);
			this.add(bot, BorderLayout.SOUTH);
		}
		
		setResizable(false);
		setTitle("Battle Cats EN - Cat Combos");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
