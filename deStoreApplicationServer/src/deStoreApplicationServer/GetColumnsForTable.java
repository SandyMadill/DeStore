package deStoreApplicationServer;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class GetColumnsForTable implements ClientCommand {
	private String tableName;
	private DataRequestManager dataRequestManager;
	private ObjectOutputStream objectOutputStream;
	
	public GetColumnsForTable(String tableName, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
		this.tableName = tableName;
	}

	@Override
	public void exec() throws SQLException, IOException {
		ArrayList<String[]> columnNames = dataRequestManager.getColumnsForTable(tableName);
		System.out.println(columnNames.size());
		objectOutputStream.writeObject(columnNames);
	}

}
