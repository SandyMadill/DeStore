package deStoreApplicationServer;
import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import org.json.simple.JSONArray;

import deStoreApplicationServer.Reports.SpecialReport;
import deStoreApplicationServer.Reports.SpecialReportMapper;
import deStoreApplicationServer.tableEntities.TableEntity;

public class ReportCommand implements ClientCommand {
	private ArrayList<String> args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;

	public ReportCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = new ArrayList(Arrays.asList(args));
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}
	

	@Override
	public void exec() throws Exception {
		String entityName = args.get(0);
		Class s = (Class) SpecialReportMapper.SpecialReportMap.get(entityName);
		
		if (s != null) {
			SpecialReport specialReport = (SpecialReport) s.getConstructor(null).newInstance();
			objectOutputStream.writeObject(specialReport.generateReport(dataRequestManager));
		}
		else {
			TableEntity te = TableEntity.getTableEntityFromName(entityName);
			ResultSet resultSet = dataRequestManager.getTable(te);
			List<Object> result = new ArrayList<Object>();
			while (resultSet.next()) {
				TableEntity next = TableEntity.getTableEntityFromName(entityName);
				next.mapDataToAppEntity(resultSet);
				result.add(next.getRowData());
				
			}
			
			objectOutputStream.writeObject(result);
		}
		
	}
}
