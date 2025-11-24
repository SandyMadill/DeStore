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
import javax.swing.JComboBox;
import java.awt.Font;

public abstract class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTable table;
	protected JList tableNameList;
	protected JButton btnConnect;
	


	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public MainView() throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationByPlatform(true);
		setVisible(true);
		setResizable(false);
		setAutoRequestFocus(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tableNameList = new JList();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGetTable = new JButton("Get Table");
		btnGetTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGetTableClicked();
			}
		});
		
		
		
		JButton updateTableBtn = new JButton("Update Row");
		updateTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateRowClicked();
			}
		});
		
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddRowClicked();
			}
		});
		
		JButton btnFilterColumn = new JButton("Filter Column");
		btnFilterColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFilterColumnClicked();
			}
		});
		
		JButton btnConfig = new JButton("Config");
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfigClicked();
			}
		});
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnectClicked();
			}
		});
		
		JButton btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteRowClicked();
			}
		});
		
		JButton btnClearFilters = new JButton("Clear Filters");
		btnClearFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClearFiltersClicked();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGetTable, Alignment.TRAILING)
						.addComponent(tableNameList, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(updateTableBtn, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnAddRow, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnFilterColumn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnClearFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnDeleteRow, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnConfig, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnConnect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1699, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
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
							.addGap(9)
							.addComponent(btnConnect)
							.addGap(8)
							.addComponent(updateTableBtn)
							.addGap(3)
							.addComponent(btnAddRow)
							.addGap(5)
							.addComponent(btnDeleteRow)
							.addGap(2)
							.addComponent(btnFilterColumn)
							.addGap(3)
							.addComponent(btnClearFilters)
							.addGap(3)
							.addComponent(btnConfig)))
					.addContainerGap(235, Short.MAX_VALUE))
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
	
	
	protected abstract void btnGetTableClicked();
	
	protected abstract void btnAddRowClicked();
	
	protected abstract void btnUpdateRowClicked();
	
	protected abstract void btnDeleteRowClicked();
	
	protected abstract void btnFilterColumnClicked();
	
	protected abstract void btnClearFiltersClicked();
	
	protected abstract void btnConfigClicked();
	
	protected abstract void btnConnectClicked();
	
}
