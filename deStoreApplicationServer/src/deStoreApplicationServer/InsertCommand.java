package deStoreApplicationServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import deStoreApplicationServer.tableEntities.TableEntity;

public class InsertCommand implements ClientCommand {
	ArrayList<String> args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;
	
	public InsertCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}
	
	@Override
	public void exec() throws Exception {
		String tableName = args.get(0);
		String[] newColumns = new String[args.size()-1];
		for (int i=1;i<args.size();i++) {
			newColumns[i-1] = args.get(i);
		}
		
		TableEntity table = TableEntity.getTableEntityFromName(tableName);
		table.mapClientArgsToEntity(newColumns);
		int res = dataRequestManager.insertRow(table);
		objectOutputStream.writeObject(res);
	}

	
}
