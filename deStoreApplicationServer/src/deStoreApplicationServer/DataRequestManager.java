package deStoreApplicationServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

import deStoreApplicationServer.tableEntities.Column;
import deStoreApplicationServer.tableEntities.Store;
import deStoreApplicationServer.tableEntities.TableEntity;

/**
 * Connects to the data server and makes sql requests
 */
public class DataRequestManager {
	private Connection conn;
	
	public DataRequestManager() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, ParseException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		AppServerConfig config = new AppServerConfig();
		conn = DriverManager.getConnection("jdbc:mysql://" + AppServerConfig.getDataServerIp() + ":" + AppServerConfig.getDataServerPort() + "/destore?user=root&password="+AppServerConfig.getDataServerPassword());
		System.out.println("connected to data server");
	}
	
	/***
	 * generates a section of an sql statment for column names or values where 
	 * @param values the text being put into the statement
	 * @param Delimiter the text that seperates the different propeties
	 * @param start if the statement needs a section at the start it can be put here
	 * @return a string starting with the start parameter followed by each values item with the Delimiter string in between each
	 */
	private String generateStmntSlice(List<String> values, String Delimiter, String start) {
		String stmnt = start;
		for (int i=0;i<values.size();i++) {
			stmnt += values.get(i);
			if(i+1<values.size()) {
				stmnt += Delimiter;
			}
		}
		return stmnt;
	}
	
	/****
	 * Creates a prepared statement
	 * @param params an array list of each parameter in order
	 * @param stmntString the string of the prepared statement
	 * @return a prepared statement with each of the param
	 * @throws SQLException if it fails to set an object into the statement
	 */
	private PreparedStatement prepareStatement(ArrayList<Object> params, String stmntString) throws SQLException {
		PreparedStatement stmnt = conn.prepareStatement(stmntString);
		for (int i=0;i<params.size();i++) {
			stmnt.setObject(i+1, params.get(i));
		}
		
		return stmnt;
	}
	
	/***
	 * retrieves a result table from the data server of a specific table entity
	 * @param <T> the type of table entity being requested
	 * @param entity an instance of the table entity class being requested (the values of it's columns will have no impact on this method) it's only needed because of java's type erasure
	 * @return a result set of the data from the data server
	 * @throws SQLException if the SQL request fails 
	 */
	public <T extends TableEntity> ResultSet getTable(T entity) throws  SQLException {
		String tableName = entity.getTableName();
		List<String> columns = new ArrayList<String>();
		return conn.createStatement().executeQuery("SELECT * FROM " + tableName);
	}
	
	/***
	 * adds an instance of a table entity to the table in the data server
	 * @param <T> the type of table entity being inserted
	 * @param entity the instance of the table entity being inserted with it's values in each of it's column properties
	 * @return an integer indicating the SQL update was executed successfully
	 * @throws SQLException if the SQL update fails
	 */
	public <T extends TableEntity> int insertRow(T entity) throws SQLException {
		List<String> columns = new ArrayList<String>();
		ArrayList<Object> args = new ArrayList<Object>();
		ArrayList<String> qs = new ArrayList<String>();
		String tableName = entity.getTableName();
		
		//	get the relevent data for each column
		entity.getColumns().forEach(column -> {
			columns.add(column.getDataName());
			args.add(column.getVal());
			qs.add("?");
		});
		
		//	generate the sql statement
		String stmntstr = "INSERT INTO " + tableName + "(" + generateStmntSlice(columns, ", " , "") + ") VALUES (" + generateStmntSlice(qs, ",", "") + ")";
		PreparedStatement stmnt = prepareStatement(args, stmntstr);
		
		//	execute the sql update
		return stmnt.executeUpdate();
	}
	
	/***
	 * updates a row in a table in the data server
	 * @param <T> the table entity type being updated
	 * @param entity the instance of the new table row with each column containing it's new data and it's key column containing it's id (this is needed to identify it in the data server)
	 * @return an integer indicating the SQL update was executed successfully
	 * @throws SQLException if the SQL update fails
	 */
	public <T extends TableEntity> int updateRow(T entity) throws SQLException {
		List<String> comparisons = new ArrayList<String>();
		ArrayList<Object> args = new ArrayList<Object>();
		
		//	get the relevant propeties from the table entity instance
		Column<Integer> key = entity.getKey();
		String tableName = entity.getTableName();
		entity.getColumns().forEach(column -> {
			comparisons.add(column.getDataName() + " = ?");
			args.add(column.getVal());
		});
		
		//	generate the SQL statement
		String stmntstr = "UPDATE " + tableName + " SET " + generateStmntSlice(comparisons, ", ", "") + " WHERE " + key.getDataName() + " = ?";
		args.add(key.getVal());
		PreparedStatement stmnt = prepareStatement(args, stmntstr);
		
		//	execute the SQL update
		return stmnt.executeUpdate();
	}
	
	/***
	 * removes a row from a table in the data server
	 * @param <T> the table entity type being removed
	 * @param the instance of the table entity being removed (it only needs the key column the rest of the column property values are irrelevant)
	 * @return an integer indicating the SQL update was executed successfully
	 * @throws SQLException if the SQL update fails
	 */
	public <T extends TableEntity> int deleteRow(T entity) throws SQLException {
		Column<Integer> key = entity.getKey();
		String tableName = entity.getTableName();
		
		//	this array list only contains the tables key, it's only needed to make use of the prepareStatement helper function
		ArrayList<Object> args = new ArrayList<Object>();	
		args.add(key.getVal());
		
		//	generate the SQL statement
		String stmntstr = "DELETE FROM " + tableName + " WHERE " + key.getDataName() + " = ?";
		PreparedStatement preparedStatement = prepareStatement(args, stmntstr);
		
		//	execute the SQL update
		return preparedStatement.executeUpdate();
	}
	
	
	
}
