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
		
		tableName = tableName;
		
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public Object[][] getTableData() {
		return tableData;
	}
	
	public String getTableName() {
		return tableName;
	}
}
