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
import java.awt.Component;

public abstract class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTable table;
	protected JList reportNameList;
	protected JButton btnConnect;
	protected JButton btnUpdateRow;
	protected JButton btnAddRow;
	protected JButton btnDeleteRow;
	


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
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGetReport = new JButton("Get Report");
		btnGetReport.setActionCommand("Get Report");
		btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGetTableClicked();
			}
		});
		
		
		
		btnUpdateRow = new JButton("Update Row");
		btnUpdateRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateRowClicked();
			}
		});
		
		btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddRowClicked();
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
		
		btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteRowClicked();
			}
		});
		
		JScrollPane tableListScroll = new JScrollPane();
		tableListScroll.setAutoscrolls(true);
		tableListScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
		tableListScroll.setAlignmentY(Component.TOP_ALIGNMENT);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpdateRow, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnAddRow, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnDeleteRow, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnConfig, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(btnGetReport)
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(tableListScroll, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1696, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(83)
							.addComponent(btnGetReport)
							.addGap(28)
							.addComponent(tableListScroll, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnConnect)
							.addGap(8)
							.addComponent(btnUpdateRow)
							.addGap(3)
							.addComponent(btnAddRow)
							.addGap(5)
							.addComponent(btnDeleteRow)
							.addGap(3)
							.addComponent(btnConfig))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(238, Short.MAX_VALUE))
		);
		
		reportNameList = new JList();
		tableListScroll.setViewportView(reportNameList);
		
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
	
	protected abstract void btnConfigClicked();
	
	protected abstract void btnConnectClicked();
}
