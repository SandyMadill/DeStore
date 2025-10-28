package deStoreApplicationServer;

public interface ClientCommand<T> {

	T exec(DataRequestManager dataRequestManager);
}
