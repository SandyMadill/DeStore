package deStoreApplicationServer.Reports;

import java.sql.SQLException;
import java.util.List;

import deStoreApplicationServer.DataRequestManager;

public interface SpecialReport {
	public List<Object[]> getColumns();
	public List<Object> generateReport(DataRequestManager dataRequestManager) throws SQLException, NoSuchMethodException, SecurityException;
}
