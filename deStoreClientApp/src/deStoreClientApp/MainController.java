package deStoreClientApp;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainController extends MainView {
	private HashMap<String, TableModel> tableMap;
	private TableModel selectedTable;
	private List<TableModel> foreignTables;
	private InputStream inputStream;
	private PropertyChangeListener listener;

	public MainController(List<TableModel> tableList) throws Exception {
		super();
		if (tableList.size() == 0) {
			JOptionPane.showMessageDialog(getContentPane(),
					"Failed to connect to the application server at " + ClientConfig.getAppServerIp() + ":" + ClientConfig.getAppServerPort(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
			enableConnectButton();
		}
		else {
			disableConnectButton();
			initial(tableList);
		}
		
		
		
	}
	
	/***
	 * Sets up the initial state of the view after connecting to the app server
	 * @param tableList list of the table names received from the app server
	 */
	private void initial(List<TableModel> tableList) {
		listener = e ->{
			table.setModel(new DefaultTableModel(selectedTable.getTableData(), selectedTable.getColumnNames()));
		};
		
		tableMap = new HashMap<String, TableModel>();
		
		String[] tableNames = new String[tableList.size()];
		
		for (int i=0;i<tableList.size();i++) {
			TableModel tableModel = tableList.get(i);
			tableMap.put(tableModel.getTableName(), tableModel);
			tableNames[i] = tableModel.getTableName();
		}
		
		reportNameList.setModel(new AbstractListModel() {
			String[] values = tableNames;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	private void updateSelectedTable() {
		if (reportNameList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(getContentPane(),
					"No table selected",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			selectedTable = tableMap.get(reportNameList.getSelectedValue());
			if (selectedTable.getSpecialReport()) {
				disableEditButtons();
			}
			else {
				enableEditButtons();
			}
		}
	}
	
	private boolean rowSelected() {
		if (selectedTable == null) {
			return false;
		}
		else if (table.getSelectedRow() == -1) {
			return false;
		}
		else {
			return true;		}
	}

	@Override
	protected void btnGetTableClicked() {
		if (selectedTable !=null) {
			selectedTable.removePropertyChangeListener(listener);
		}
		updateSelectedTable();
		selectedTable.addPropertyChangeListener(listener);
		try {
			selectedTable.refreshTableData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void btnAddRowClicked() {
		InsertTableForm tableForm = new InsertTableForm(selectedTable);
		tableForm.setVisible(true);
	}

	@Override
	protected void btnUpdateRowClicked() {
		if (rowSelected()) {
			UpdateTableForm tableForm = new UpdateTableForm(selectedTable, table.getSelectedRow());
			tableForm.setVisible(true);
		}
	}

	@Override
	protected void btnDeleteRowClicked() {
		if(rowSelected()) {
			int option = JOptionPane.showConfirmDialog(getContentPane(),
					"Are you sure you want to delete this row?",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				try {
					selectedTable.deleteRow(table.getSelectedRow());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	protected void btnConfigClicked() {
		try {
			ConfigView configView = new ConfigView();
			configView.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	protected void btnConnectClicked() {
		try {
			ArrayList<TableModel> tableList = new ArrayList<TableModel>();
			TableModel.getTableNames().forEach(rep ->{
				try {
					tableList.add(new TableModel((String) rep[0], (boolean) rep[1]));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			initial(tableList);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					"Failed to connect to the server.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/***
	 * Sets the connect button to visible and enabled. Only for when the server has failed to connect to the app server
	 */
	private void enableConnectButton() {
		super.btnConnect.setVisible(true);
		super.btnConnect.setEnabled(true);
	}
	
	/***
	 * Sets the connect button on the main view to invisible and disabled, When the client has successfully connected to the app server
	 */
	private void disableConnectButton() {
		super.btnConnect.setVisible(false);
		super.btnConnect.setEnabled(false);
	}
	
	/***
	 * disables the update row and add row buttons
	 */
	private void disableEditButtons() {
		btnAddRow.setVisible(false);
		btnAddRow.setEnabled(false);
		btnUpdateRow.setVisible(false);
		btnUpdateRow.setEnabled(false);
		btnDeleteRow.setVisible(false);
		btnDeleteRow.setEnabled(false);
	}
	
	/**
	 * enables the update row and add row buttons
	 *
	 */
	private void enableEditButtons() {
		btnAddRow.setVisible(true);
		btnAddRow.setEnabled(true);
		btnUpdateRow.setVisible(true);
		btnUpdateRow.setEnabled(true);
		btnDeleteRow.setVisible(true);
		btnDeleteRow.setEnabled(true);
	}


}
