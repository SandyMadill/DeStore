package deStoreClientApp;

import java.util.*;

public class Table {
	private String tableName;
	private String[] columnNames;
	private String[] columnTypes;
	private Object[][] tableData;
	
	public static List<String> getTableNames(){
		return SendCommand.send("table-names");
	}
	
	public Table(String tableName, HashMap<String, Table> hashMap) {
		hashMap.put(tableName, this);
		ArrayList<String[]> columns = SendCommand.send("column-names " + tableName);
		columnNames = new String[columns.size()];
		columnTypes = new String[columns.size()];
		String command = "report " + tableName;
		System.out.println(columns.size());
		for (int i=0;i<columns.size();i++) {
			columnNames[i] = columns.get(i)[0];
			columnTypes[i] = columns.get(i)[1];
			System.out.println(columnTypes[i]);
			command+= " " + columns.get(i)[0];
		}
		
		Object[] tData = SendCommand.send(command);
		
		tableData = new Object[tData.length][columns.size()];
		
		for(int i=0;i<tableData.length; i++) {
			Object[] row = (Object[]) tData[i];
			tableData[i] = row;
		}
		
		this.tableName = tableName;
		
	}
	
	public void updateRow(int row, String[] newColumns) {
		String command = "update " + tableName;
		for (int i=1;i<columnNames.length;i++) {
			command += " " + columnNames[i];
		}
		command += " -v";
		for (int i=0;i<newColumns.length;i++) {
			command += " \"" + newColumns[i] + "\"";
		}
		command += " -w " + columnNames[0] + " = " + (String) tableData[row][0];
		
		System.out.println(command);
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public Object[][] getTableData() {
		return tableData;
	}
	
	public String getDataTypeFromColumnName(String columnName) {
		for (int i=0;i<columnName.length();i++) {
			if (columnNames[i].equals(columnName)) {
				return columnTypes[i];
			}
		}
		return null;
	}
	
	public void insertRow(String[] newColumns) {
		String command = "insert " + tableName;
		for (int i=1;i<columnNames.length;i++) {
			command += " " + columnNames[i];
		}
		command += " -v";
		for (int i=0;i<newColumns.length;i++) {
			command += " \"" + newColumns[i] + "\"";
		}
		System.out.println(command);
	}
	
	public String getTableName() {
		return tableName;
	}
}
