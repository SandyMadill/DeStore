package deStoreApplicationServer;

import java.io.ObjectOutputStream;

public class UpdateCommand implements ClientCommand {
	
	private String[] args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;
	
	public UpdateCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = args;
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}
	
	public UpdateCommand(String[] args) {
		this.args = args;
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
