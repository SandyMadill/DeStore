package deStoreApplicationServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Connects to the data server and makes requests
 */
public class DataRequestManager {
	private Connection conn;
	
	public DataRequestManager() {
		try {
			System.out.println("Connecting to data server");
			Class.forName("com.mysql.cj.jdbc.Driver");
			AppServerConfig config = new AppServerConfig();
			conn = DriverManager.getConnection("jdbc:mysql://" + config.getDataServerIp() + ":" + config.getDataServerPort() + "/destore?user=root&password="+config.getDataServerPassword());
			System.out.println("connected to data server");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 	creates the columns and values section of the prepared statement
	 * @param columns the number of columns in the sql statement
	 * @return the prepared statement section for the columns and values (should be reused for both)
	 */
	private String generateColumnsStmnt(int columns) {
		String stmnt = "";
		for (int i=0;i<columns;i++) {
			stmnt += "?";
			if(i+1<columns) {
				stmnt += ",";
			}
		}
		return stmnt;
	}
	
	/****
	 * Creates a prepared statement
	 * @param params an array list of each parameter in order
	 * @param stmntString the string of the prepared statement
	 * @return a prepared statement using the statement string and the parameters
	 * @throws SQLException
	 */
	private PreparedStatement prepareStatement(ArrayList<String> params, String stmntString) throws SQLException {
		PreparedStatement stmnt = conn.prepareStatement(stmntString);
		for (int i=0;i<params.size();i++) {
			stmnt.setObject(i+1, params.get(i));
		}
		
		return stmnt;
	}
	
	
	
	public String insert(String tableName, List<String> columnNames, List<String> columnValues) {
		try {
			if (columnNames.size() != columnValues.size()) {
				return "ERROR: the number of column names and values in the request do not match";
			}
			else {
				String columnsStmnt = generateColumnsStmnt(columnNames.size());
				String stmntString = "INSERT INTO ? (" + columnsStmnt + ") VALUES (" + columnsStmnt + ")";
				ArrayList<String> params = new ArrayList<String>();
				
				params.add(tableName);
				params.addAll(columnNames);
				params.addAll(columnValues);
				
				PreparedStatement stmnt = prepareStatement(params, stmntString);
				
				int res = stmnt.executeUpdate();
				stmnt.close();
				return String.valueOf(res);
			}
		} catch (SQLException e) {
			return ("SQL EXCEPTION:" + e.getLocalizedMessage());
		}
	}
	
	public String select(String tableName, List<String> columnNames, List<WhereStmnt> wheres) {
		try {
			String columnsStmnt = generateColumnsStmnt(columnNames.size());
			String wheresStmnt = "";
			
			if (wheres.size() > 0) {
				wheresStmnt += " WHERE ";
				for (int i=0;i<wheres.size();i++) {
					wheresStmnt += wheres.get(i).getPreparedStatementSlice();
					if (i+1 < wheres.size() ) {
						wheresStmnt+=" AND ";
					}
				}
			}
			
			String stmntString = "SELECT " + columnsStmnt + " FROM " + tableName + wheresStmnt;
			
			
			ArrayList<String> params = new ArrayList<String>();
			params.addAll(columnNames);
			
			wheres.forEach(w ->{
				params.addAll(w.getParams());
			});
			
			System.out.println(params.toString());
			
			PreparedStatement stmnt = prepareStatement(params, stmntString);
			stmnt.executeQuery();
			return "good";
		}
		catch (SQLException e) {
			return ("SQL EXCEPTION:" + e.getLocalizedMessage());
		}
	}
	
	
}
