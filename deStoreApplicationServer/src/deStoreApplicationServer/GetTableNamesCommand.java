package deStoreApplicationServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;

public class GetTableNamesCommand implements ClientCommand {
	private DataRequestManager dataRequestManager;
	private ObjectOutputStream objectOutputStream;
	
	public GetTableNamesCommand(DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}

	@Override
	public void exec() throws SQLException, IOException {
		try {
			List<String> tableNames = dataRequestManager.getTableNames();
			objectOutputStream.writeObject(tableNames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
