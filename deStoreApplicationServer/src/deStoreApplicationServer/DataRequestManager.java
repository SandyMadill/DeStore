package deStoreApplicationServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.*;

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
	private String generateColumnsStmnt(List<String> columnNames) {
		String stmnt = "";
		for (int i=0;i<columnNames.size();i++) {
			stmnt+=columnNames.get(i);
			if (i+1 < columnNames.size()) {
				stmnt+=", ";
			}
		}
		return stmnt;
	}
	
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
	 * @return a prepared statement using the statement string and the parameters
	 * @throws SQLException
	 */
	private PreparedStatement prepareStatement(ArrayList<String> params, String stmntString) throws SQLException {
		PreparedStatement stmnt = conn.prepareStatement(stmntString);
		for (int i=0;i<params.size();i++) {
			stmnt.setString(i+1, params.get(i));
		}
		
		return stmnt;
	}
	
	
	
	public String insert(String tableName, List<String> columnNames, List<String> columnValues) {
		try {
			if (columnNames.size() != columnValues.size()) {
				return "ERROR: the number of column names and values in the request do not match";
			}
			else {
				String columnsStmnt = generateStmntSlice(columnNames, ",", "");
				ArrayList<String> qs = new ArrayList<String>();
				columnValues.forEach(column ->{
					qs.add("?");
				});
				String valuesStmnt = generateStmntSlice(qs, ",", "");
				String stmntString = "INSERT INTO ? (" + columnsStmnt + ") VALUES (" + valuesStmnt + ")";
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
	
	public Object[] select(String tableName, List<String> columnNames, List<CompareStmnt> wheres) {
		try {
			String columnsStmnt = generateColumnsStmnt(columnNames);
			String wheresStmnt = "";
			ArrayList<String> params = new ArrayList<String>();
			
			if (wheres.size() > 0) {
				List<String> whereComparisons = new ArrayList<String>();
				for (int i=0;i<wheres.size();i++) {
					params.add(wheres.get(i).getVal());
					whereComparisons.add(wheres.get(i).getPreparedStatementSlice());
				}
				wheresStmnt = generateStmntSlice(whereComparisons, " AND ", " Where ");
			}
			
			String stmntString = "SELECT " + columnsStmnt + " FROM " + tableName + wheresStmnt;
			
			System.out.println(params.toString());
			
			PreparedStatement stmnt = prepareStatement(params, stmntString);
			System.out.println(stmnt.toString());
			ResultSet result = stmnt.executeQuery();
			ArrayList<String[]> tableData = new ArrayList<String[]>();
			
			while (result.next()) {
				String[] row = new String[columnNames.size()];
				for (int i=0;i<columnNames.size();i++) {
					row[i]= result.getString(columnNames.get(i));
				}
				tableData.add(row);
			}
			
			return tableData.toArray();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public String update(String tableName, List<CompareStmnt> sets, List<CompareStmnt> wheres) {
		try {
			ArrayList<String> params = new ArrayList<String>();
			String setsStmnt = "";
			if (sets.size() > 0) {
				List<String> setComparisons = new ArrayList<String>();
				sets.forEach(s -> {
					params.add(s.getVal());
					setComparisons.add(s.getPreparedStatementSlice());
				});
				setsStmnt = generateStmntSlice(setComparisons, ",", " SET ");
			}
		
			String wheresStmnt = "";
		
			if (wheres.size() > 0) {
				List<String> whereComparisons = new ArrayList<String>();
				wheres.forEach(w ->{
					params.add(w.getVal());
					whereComparisons.add(w.getPreparedStatementSlice());
				});
				wheresStmnt = generateStmntSlice(whereComparisons, " AND ", " WHERE ");
			}
		
			String stmntString = "UPDATE " + tableName + setsStmnt + wheresStmnt;
		
		
			PreparedStatement stmnt = prepareStatement(params, stmntString);
			
			System.out.println(stmnt.toString());
			
			int res = stmnt.executeUpdate();
			return String.valueOf(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String delete(String tableName, List<CompareStmnt> wheres) {
		try {
			ArrayList<String> params = new ArrayList<String>();
			String wheresStmnt = "";
			
			if (wheres.size() > 0) {
				List<String> whereComparisons = new ArrayList<String>();
				wheres.forEach(w ->{
					params.add(w.getVal());
					whereComparisons.add(w.getPreparedStatementSlice());
				});
				wheresStmnt = generateStmntSlice(whereComparisons, " AND ", " WHERE ");
			}
		
			String stmntString = "DELETE FROM " + tableName + wheresStmnt;
		
		
			PreparedStatement stmnt = prepareStatement(params, stmntString);
			
			System.out.println(stmnt.toString());
			
			int res = stmnt.executeUpdate();
			return String.valueOf(res);
			
		}
		catch (SQLException e) {
			
		}
		return null;
		
	}
	
	public ArrayList<String> getTableNames(){
		ArrayList<String> tableNames = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = \"destore\";");
			while (result.next()) {
				tableNames.add(result.getString("table_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableNames;
	}
	
	public ArrayList<String[]> getColumnNamesForTable(String tableName){
		ArrayList<String[]> columnNames = new ArrayList<String[]>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT column_name, data_type FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '" + tableName + "'");
			String command = "report " + tableName;
			while (result.next()) {
				String[] column = new String[2];
				column[0] = result.getString("COLUMN_NAME");
				column[1] = result.getString("DATA_TYPE");
				columnNames.add(column);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return columnNames;
	}
	
	
}
