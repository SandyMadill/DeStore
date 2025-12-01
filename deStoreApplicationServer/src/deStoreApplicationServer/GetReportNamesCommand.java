package deStoreApplicationServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import deStoreApplicationServer.Reports.SpecialReportMapper;
import deStoreApplicationServer.tableEntities.TableEntity;

public class GetReportNamesCommand implements ClientCommand {
	private DataRequestManager dataRequestManager;
	private ObjectOutputStream objectOutputStream;
	
	public GetReportNamesCommand(DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}

	@Override
	public void exec() throws SQLException, IOException {
		try {
			List<Object[]> output = new ArrayList<Object[]>();
			List<String> tableNames = TableEntity.tableEntityNames;
			tableNames.forEach(tableName ->{
				Object[] pair = new Object[2];
				pair[0] = tableName;
				pair[1] = false;
				output.add(pair);
			});
			
			SpecialReportMapper.SpecialReportMap.forEach( (k, v)->{
				Object[] pair = new Object[2];
				pair[0] = k;
				pair[1] = true;
				output.add(pair);
			});
			
			objectOutputStream.writeObject(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
