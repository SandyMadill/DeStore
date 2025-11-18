package deStoreClientApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Frame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JList tableNameList;
	private HashMap<String, Table> tableMap;
	private Table selectedTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		tableMap = new HashMap<String, Table>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationByPlatform(true);
		setVisible(true);
		setResizable(false);
		setAutoRequestFocus(false);
		List<String> tableNamesArraylist = Table.getTableNames();
		String[] tableNames = new String[tableNamesArraylist.size()];
		for (int i = 0; i < tableNamesArraylist.size(); i++) {
			tableNames[i] = tableNamesArraylist.get(i);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tableNameList = new JList();
		tableNameList.setModel(new AbstractListModel() {
			String[] values = tableNames;
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnGetTable = new JButton("Get Table");
		btnGetTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableNameList.getSelectedIndex() != -1) {
					updateTable();
				}
				else {
					JOptionPane.showMessageDialog(contentPane,
							"You haven't selected a table",
							"Validation Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton updateTableBtn = new JButton("Update Table");
		updateTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (selectedTable == null) {
					JOptionPane.showMessageDialog(contentPane,
							"You haven't selected a table",
							"Validation Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else if (row == -1) {
					JOptionPane.showMessageDialog(contentPane,
							"You haven't selected a row",
							"Validation Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					UpdateTableForm tableForm = new UpdateTableForm(selectedTable, row);
					tableForm.setVisible(true);
				}
			}
		});
		
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedTable == null) {
					JOptionPane.showMessageDialog(contentPane,
							"You haven't selected a table",
							"Validation Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					InsertTableForm tableForm = new InsertTableForm(selectedTable);
					tableForm.setVisible(true);
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGetTable)
						.addComponent(tableNameList, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnAddRow)
							.addComponent(updateTableBtn)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1699, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGetTable)
							.addGap(8)
							.addComponent(tableNameList, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(updateTableBtn)
							.addGap(3)
							.addComponent(btnAddRow)))
					.addContainerGap(307, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setLayout(gl_contentPane);
		

	}
	
	private void updateTable() {
		String tableName = tableNameList.getModel().getElementAt(tableNameList.getSelectedIndex()).toString();
		selectedTable = tableMap.get(tableName);
		if (selectedTable == null) {
			selectedTable = new Table(tableName, tableMap);
		}
		table.setModel(new DefaultTableModel(selectedTable.getTableData(), selectedTable.getColumnNames()));
		
	}
}
