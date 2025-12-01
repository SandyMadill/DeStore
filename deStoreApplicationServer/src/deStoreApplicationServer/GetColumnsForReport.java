package deStoreApplicationServer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

import deStoreApplicationServer.Reports.SpecialReport;
import deStoreApplicationServer.Reports.SpecialReportMapper;
import deStoreApplicationServer.tableEntities.TableEntity;

public class GetColumnsForReport implements ClientCommand {
	private String tableName;
	private DataRequestManager dataRequestManager;
	private ObjectOutputStream objectOutputStream;
	
	public GetColumnsForReport(String tableName, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
		this.tableName = tableName;
	}

	@Override
	public void exec() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class s = (Class) SpecialReportMapper.SpecialReportMap.get(tableName);
		
		if (s != null) {
			SpecialReport specialReport = (SpecialReport) s.getConstructor(null).newInstance();
			objectOutputStream.writeObject(specialReport.getColumns());
		}
		else {
			TableEntity table = (TableEntity) TableEntity.getTableEntityFromName(tableName);
			objectOutputStream.writeObject(table.getColumnMetaData());
		}
	}

}
