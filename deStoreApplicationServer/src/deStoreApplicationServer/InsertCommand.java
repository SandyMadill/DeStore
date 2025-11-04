package deStoreApplicationServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public void exec() {
		try {
			//	find the value flag in the command, if there isn't one send an error message to the client
			int v = args.indexOf("-v");
					
			if (v== -1) {
				objectOutputStream.writeObject("ERROR: There is no -v flag to indicate where the column names and column values are seperated");
			}
			else {
				//	divide the arguments 
				String tableName = args.get(0);
				List<String> columnNames = args.subList(1, v);
				List<String> columnValues = args.subList(v+1, args.size());
				
				String dataResponse = dataRequestManager.insert(tableName, columnNames, columnValues);
				
				objectOutputStream.writeObject(dataResponse);
			}
		} catch (IOException e) {
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
