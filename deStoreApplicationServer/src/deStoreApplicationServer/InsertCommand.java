package deStoreApplicationServer;

public class InsertCommand<T> implements ClientCommand<T> {
	String[] args;

	public InsertCommand(String[] args) {
		this.args = args;
	}
	
	@Override
	public T exec(DataRequestManager dataRequestManager) {
		System.out.println("INSERT COMMAND");
		for(String arg: args) {
			System.out.println(arg);
		}
		return null;
	}

	
}
