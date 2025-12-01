package deStoreApplicationServer;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import deStoreApplicationServer.tableEntities.TableEntity;

public class UpdateCommand implements ClientCommand {
	
	private ArrayList<String> args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;
	
	public UpdateCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}

	

	@Override
	public void exec() throws Exception {
		String tablename = args.get(0);
		int key = Integer.parseInt(args.get(1));
		TableEntity tableEntity = TableEntity.getTableEntityFromName(tablename);
		tableEntity.getKey().setVal(key);
		
		String[] newColumns = new String[args.size()-2];
		for (int i=2;i<args.size();i++) {
			newColumns[i-2] = args.get(i);
		}
		
		tableEntity.mapClientArgsToEntity(newColumns);
		
		
		
		int res = dataRequestManager.updateRow(tableEntity);
		objectOutputStream.writeObject(res);
		
	}
}
