package deStoreApplicationServer;

public class RemoveCommand<T> implements ClientCommand<T> {
	String[] args;
	
	public RemoveCommand(String[] args) {
		this.args = args;
	}
	

	@Override
	public T exec(DataRequestManager dataRequestManager) {
		// TODO Auto-generated method stub
		return null;
	}

}
