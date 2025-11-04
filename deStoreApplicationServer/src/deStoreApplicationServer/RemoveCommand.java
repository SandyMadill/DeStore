package deStoreApplicationServer;

import java.io.ObjectOutputStream;

public class RemoveCommand implements ClientCommand {
	private String[] args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;
	
	public RemoveCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = args;
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}
	

	@Override
	public void exec() {
		// TODO Auto-generated method stub
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
