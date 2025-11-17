package deStoreApplicationServer;

import java.io.*;
import java.util.*;

public class GetColumnNamesForTable implements ClientCommand {
	private String tableName;
	private DataRequestManager dataRequestManager;
	private ObjectOutputStream objectOutputStream;
	
	public GetColumnNamesForTable(String tableName, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
		this.tableName = tableName;
	}


	@Override
	public ObjectOutputStream getObjectOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exec() {
		try {
			ArrayList<String[]> columnNames = dataRequestManager.getColumnNamesForTable(tableName);
			System.out.println(columnNames.size());
			objectOutputStream.writeObject(columnNames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
