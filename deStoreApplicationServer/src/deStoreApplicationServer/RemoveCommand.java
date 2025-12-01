package deStoreApplicationServer;

import java.io.*;
import java.util.*;

import deStoreApplicationServer.tableEntities.TableEntity;

public class RemoveCommand implements ClientCommand {
	private ArrayList<String> args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;
	
	public RemoveCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}
	

	@Override
	public void exec() throws Exception {
		String tableName = args.get(0);
		TableEntity tableEntity = TableEntity.getTableEntityFromName(tableName);
		int key = Integer.parseInt(args.get(1));
		tableEntity.getKey().setVal(key);
		
		int res = dataRequestManager.deleteRow(tableEntity);
		
		objectOutputStream.writeObject(res);
		
	}

}
