package deStoreApplicationServer.Reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import deStoreApplicationServer.DataRequestManager;
import deStoreApplicationServer.tableEntities.Sale;

public class MonthlySalesReport implements SpecialReport {
	
	public MonthlySalesReport(){
		
	}

	@Override
	public List<Object> generateReport(DataRequestManager dataRequestManager) throws SQLException, NoSuchMethodException, SecurityException {
		Sale sale = new Sale();
		List<Sale> sales = new ArrayList<Sale>();
		List<Object> reportList = new ArrayList<Object>();
		ResultSet res = dataRequestManager.getTable(sale);
		
		while (res.next()) {
			Sale newSale = new Sale();
			newSale.mapDataToAppEntity(res);
			sales.add(newSale);
		}
		if (sales.size() > 0) {
			sales.sort( (a, b) -> {
				return  ((LocalDate) a.getColumns().get(3).getVal()).compareTo((LocalDate) b.getColumns().get(3).getVal());
			});
			
			
			LocalDate date = (LocalDate) sales.getFirst().getColumns().get(3).getVal();
			
			String dateMonth = date.getMonth().name() + "-" + date.getYear();
			
			int monthSales = 0;
			
			for (int i=0;i<sales.size();i++) {
				LocalDate newDate = (LocalDate) sales.get(i).getColumns().get(3).getVal();
				
				String newDateMonth = newDate.getMonth().name() + "-" + newDate.getYear();
				if (!newDateMonth.equals(dateMonth)) {
					Object[] reportRow = new Object[2];
					reportRow[0] = dateMonth;
					reportRow[1] = monthSales;
					dateMonth = newDateMonth;
					monthSales =1;
					reportList.add(reportRow);
				}
				else {
					monthSales++;
				}
			}
			
			Object[] reportRow = new Object[2];
			reportRow[0] = dateMonth;
			reportRow[1] = monthSales;
			reportList.add(reportRow);
		}
		
		return reportList;
		
	}

	@Override
	public List<Object[]> getColumns() {
		List<Object[]> toReturn = new ArrayList<Object[]>();
		Object[] dateMonth = new Object[2];
		dateMonth[0] = "Date And Month";
		dateMonth[1] = String.class.getSimpleName();
		toReturn.add(dateMonth);
		Object[] totalSales = new Object[2];
		totalSales[0] = "Total Sales";
		totalSales[1] = Float.class.getSimpleName();
		toReturn.add(totalSales);
		return toReturn;
	}

}
