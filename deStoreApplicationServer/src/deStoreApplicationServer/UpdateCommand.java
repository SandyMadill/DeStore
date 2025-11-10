package deStoreApplicationServer;

import java.io.*;
import java.util.*;

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
	public void exec() {
		try {
			String tablename = args.get(0);
			List<CompareStmnt> sets = CompareStmnt.getCompareStmntsFromArgs(args, "s");
			List<CompareStmnt> wheres = CompareStmnt.getCompareStmntsFromArgs(args, "w");
			String dataResponse = dataRequestManager.update(tablename, sets, wheres);
			objectOutputStream.writeObject(dataResponse);
		} catch (Exception e) {
			try {
				objectOutputStream.writeObject(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
