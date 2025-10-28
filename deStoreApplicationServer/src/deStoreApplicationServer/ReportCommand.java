package deStoreApplicationServer;

public class ReportCommand<T> implements ClientCommand<T> {
	String[] args;
	
	public ReportCommand(String[] args) {
		this.args = args;
	}
	

	@Override
	public T exec(DataRequestManager dataRequestManager) {
		// TODO Auto-generated method stub
		return null;
	}

}
