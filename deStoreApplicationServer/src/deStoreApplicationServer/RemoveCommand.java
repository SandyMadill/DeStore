package deStoreApplicationServer;

import java.io.*;
import java.util.*;

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
	public void exec() {
		try {
			String tableName = args.get(0);
			List<CompareStmnt> wheres = CompareStmnt.getCompareStmntsFromArgs(args, "w");
			String result = dataRequestManager.delete(tableName, wheres);
			objectOutputStream.writeObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

}
