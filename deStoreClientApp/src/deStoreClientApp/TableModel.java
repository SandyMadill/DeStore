package deStoreClientApp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.*;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class TableModel {
	private final PropertyChangeSupport support = new PropertyChangeSupport(this);
	private String tableName;
	private String[] columnNames;
	private int key;
	private HashMap<String, String> columnTypesMap;
	private Object[][] tableData;
	private boolean special;
	
	public static List<Object[]> getTableNames() throws Exception, UnknownHostException{
		return SendCommand.send("report-names");
	}
	
	public TableModel(String tableName, boolean specialReport) throws Exception {
		special = specialReport;
		columnTypesMap = new HashMap<String,String>();
		
		List<Object[]> columns= SendCommand.send("columns " + tableName);
		columnNames = new String[columns.size()];
		
		key = 0;
		for (int i=0;i<columns.size();i++) {
			Object[] column = columns.get(i);
			columnNames[i] = (String) column[0];
			columnTypesMap.put((String) column[0], (String) column[1]);
		}
		
		this.tableName = tableName;
	}
	
	public boolean getSpecialReport() {
		return special;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}
	
	public void deleteRow(int row) throws Exception {
		int result = SendCommand.send("remove " + tableName + " " + tableData[row][key]);
		getTableFromServer();
	}
	
	public void refreshTableData() throws Exception {
		getTableFromServer();
	}
	
	public void getTableFromServer() throws Exception {
		String command = "report " + tableName;
		
		for (int i =0;i<columnNames.length;i++) {
			command+= " " + columnNames[i];
		}
		
		List<Object> tData = SendCommand.send(command);
		
		tableData = new Object[tData.size()][columnNames.length];
		
		for(int i=0;i<tableData.length; i++) {
			Object[] row = (Object[]) tData.get(i);
			tableData[i] = row;
		}
		Object[][] old = new Object[0][0];
		support.firePropertyChange("tableData", old, tableData);
	}
	
	
	public int getKey() {
		return key;
	}
	
	public void updateRow(int row, Object[] newColumns) throws Exception {
		String command = "update " + tableName + " " + tableData[row][key];
		for (int i=0;i<newColumns.length;i++) {
			command += " \"" + newColumns[i] + "\"";
		}
		
		int res = SendCommand.send(command);
		getTableFromServer();
	}
	
	public void insertRow(Object[] newColumns) throws Exception {
		String command = "insert " + tableName;
		for (int i=0;i<newColumns.length;i++) {
			command += " \"" + newColumns[i] + "\"";
		}
		
		int res = SendCommand.send(command);
		getTableFromServer();
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public Object[][] getTableData() {
		return tableData;
	}
	
	public String getDataTypeFromColumnName(String columnName) {
		return columnTypesMap.get(columnName);
	}
	
	
	public String getTableName() {
		return tableName;
	}
	
}
