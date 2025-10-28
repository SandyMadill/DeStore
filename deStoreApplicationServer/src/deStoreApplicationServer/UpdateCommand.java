package deStoreApplicationServer;

public class UpdateCommand<T> implements ClientCommand<T> {
	
	private String[] args;
	
	public UpdateCommand(String[] args) {
		this.args = args;
	}
	

	@Override
	public T exec(DataRequestManager dataRequestManager) {
		// TODO Auto-generated method stub
		return null;
	}

}
