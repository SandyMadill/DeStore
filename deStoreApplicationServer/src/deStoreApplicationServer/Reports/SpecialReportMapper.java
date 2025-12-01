package deStoreApplicationServer.Reports;

import java.util.HashMap;
import java.util.Map;

public class SpecialReportMapper {
	public static Map<String, Class<?>> SpecialReportMap = Map.of(
			"Monthly-Sales", MonthlySalesReport.class
		);
}
