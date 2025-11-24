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
	private HashMap<String, String> foreignTableMap;
	private HashMap<String, String> foreignColumnMap;
	private ArrayList<String[]> filters;
	private Object[][] tableData;
	
	public static List<String> getTableNames() throws Exception, UnknownHostException{
		return SendCommand.send("table-names");
	}
	
	public TableModel(String tableName) throws Exception {
		filters = new ArrayList<String[]>();
		foreignTableMap = new HashMap<String,String>();
		foreignColumnMap = new HashMap<String, String>();
		columnTypesMap = new HashMap<String,String>();
		
		ArrayList<String[]> columns= SendCommand.send("columns " + tableName);
		columnNames = new String[columns.size()];
		
		for (int i=0;i<columns.size();i++) {
			String[] column = columns.get(i);
			String columnName = column[0];
			columnNames[i] = columnName;
			columnTypesMap.put(columnName, column[1]);
			if (column[2].equals("PRI")) {
				key = i;
			}
			else if (column[2].equals("MUL")) {
				foreignTableMap.put(columnName, column[3]);
				foreignColumnMap.put(columnName, column[4]);
			}
		}
		
		this.tableName = tableName;
	}
	
	public JTextField getTextFieldForColumn(String columnName) throws ParseException {
		if (getDataTypeFromColumnName(columnName).equals("datetime")) {
			MaskFormatter dateFormatter;
			dateFormatter = new MaskFormatter("##/##/####");
			dateFormatter.setPlaceholderCharacter('_');
			return new JFormattedTextField(dateFormatter);
				
		}
		else {
			return new JTextField();
			
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	      support.addPropertyChangeListener(listener);
	   }
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	      support.removePropertyChangeListener(listener);
	      }
	
	public void deleteRow(int row) throws Exception {
		String result = SendCommand.send("remove " + tableName + " -w " + columnNames[key] + " = " + (String) tableData[row][key]);
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
		
		command += getFiltersForCommand();
		
		Object[] tData = SendCommand.send(command);
		
		tableData = new Object[tData.length][columnNames.length];
		
		for(int i=0;i<tableData.length; i++) {
			Object[] row = (Object[]) tData[i];
			tableData[i] = row;
		}
		Object[][] old = new Object[0][0];
		support.firePropertyChange("tableData", old, tableData);
	}
	
	public Object[] getForeignKeyTables(){
		return foreignTableMap.values().toArray();
	}
	
	public int getKey() {
		return key;
	}
	
	public void updateRow(int row, String[] newColumns) throws Exception {
		String command = "update " + tableName;
		int i =0;
		for (int j=0;j<newColumns.length;j++) {
			command += " -s";
			if (i==key) {
				i++;
			}
			command += " " + columnNames[i] + " = \"" + newColumns[j] + "\"";
			i++;
		}
		command += " -w " + columnNames[key] + " = " + (String) tableData[row][key];
		
		String res = SendCommand.send(command);
		getTableFromServer();
	}
	
	public void insertRow(String[] newColumns) throws Exception {
		String command = "insert " + tableName;
		for (int i=0;i<columnNames.length;i++) {
			if (i!=key) {
				command += " " + columnNames[i];
			}
		}
		command += " -v";
		for (int i=0;i<newColumns.length;i++) {
			command += " \"" + newColumns[i] + "\"";
		}
		String res = SendCommand.send(command);
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
	
	/**
	 * inserts a new filter to the table with it's three arguments in a single string array and then receives the table data with the new filter applied
	 * @param args the three arguments must have at least 3 items, any items more than three will not be used
	 * @throws Exception may occur from retrieving the table data
	 */
	public void addFilter(String[] args) throws Exception {
		String[] newFilter = new String[3];
		newFilter[0] = args[0];
		newFilter[1] = args[1];
		newFilter[2] = args[2];
		
		filters.add(newFilter);
		getTableFromServer();
	}
	
	/***
	 * formats the filters into their command notation
	 * @return the filters in their command notation
	 */
	public String getFiltersForCommand() {
		String com = "";
		for (int i=0;i<filters.size();i++) {
			com += " -w " + filters.get(i)[0] + " " + filters.get(i)[1] + " " + filters.get(i)[2];
		}
		
		return com;
	}
	
	/***
	 * removes all filters and then receives the table data from the server 
	 * @throws Exception may occur from retrieving the table data
	 */
	public void clearFilters() throws Exception {
		filters = new ArrayList<String[]>();
		getTableFromServer();
	}
	
}
