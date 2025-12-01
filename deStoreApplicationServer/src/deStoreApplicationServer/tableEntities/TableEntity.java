package deStoreApplicationServer.tableEntities;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.*;

/***
 * Holds the properties of a table on the data server pairing the application properties and the data server properties 
 */
public abstract class TableEntity {
	public static List<String> tableEntityNames = Arrays.asList("AdvancePayment", "Customer", "PriceControl", "Product", "Sale", "Stock", "Store");
	
	
	/***
	 * parses an invocation of a specific type of table entity in a client command
	 * @param The class name of the table entity
	 * @return a new instance using the default constructor of the table entity or null if the parameter name could not be mapped to and existing class
	 */
	public static TableEntity getTableEntityFromName(String entityName) {
		try {
			return (TableEntity) Class.forName(TableEntity.class.getPackageName() + "." + entityName).getConstructor(null).newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	public TableEntity() {
		
	}
	
	/***
	 * the table data for the selected row as sent to the client
	 * @return the table data for the row including the table id at the start
	 */
	public Object[] getRowData(){
		List<Column> columns = getColumns();
		Object[] row = new Object[columns.size()+1];
		
		//	get the id value
		row[0] = getKey().getVal();
		
		//	get the values for the other columns
		for (int i=1;i<row.length;i++) {
			row[i] = columns.get(i-1).getVal();
		}
		return row;
	}
	
	/***
	 * gets relevant meta data needed by the client for each column in a table
	 * @return a 2d array with each row containing the name and 
	 */
	public List<Object[]> getColumnMetaData(){
		ArrayList<Object[]> columnNames =  new ArrayList<Object[]>();
		Object[] keyCol = new Object[2];
		
		// get the table id meta data
		keyCol[0] = getKey().getAppName();
		keyCol[1] = getKey().getType();
		
		//	get the meta data for each column
		columnNames.add(keyCol);
		getColumns().forEach(column ->{
			Object[] columnMetaData = new Object[2];
			columnMetaData[0] = column.getAppName();
			columnMetaData[1] = column.getType();
			columnNames.add(columnMetaData);
		});
		return columnNames;
	}
	
	/**
	 * maps the current row in a result set to the properties of the table entity
	 * @param resultSet a result set of the data server table for this table entity
	 * @throws SQLException if the result set does not contain the properties of this table entity
	 */
	public abstract void mapDataToAppEntity(ResultSet resultSet) throws SQLException;
	
	/***
	 * parses a client command containing data properties for this client entity
	 * @param args the arguments for the client entities column data (excluding the key column) in the same order as the get columns method
	 * @throws IllegalArgumentException if an argument cannot be parsed to it's appropriate data type
	 */
	public abstract void mapClientArgsToEntity(String[] args) throws IllegalArgumentException;
	
	/**
	 * @return the table name of entity in the data server
	 */
	public abstract String getTableName();
	
	/**
	 * @return a list of the column properties of the table entity
	 */
	public abstract List<Column> getColumns();
	
	/***
	 * @return the key column of the table entity
	 */
	public abstract Column<Integer> getKey();
}
