package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Sale extends TableEntity {
	private final Column<Integer> saleId = new Column<Integer>("Sale Id", "sale_id", 0);
	private final Column<Integer> productId = new Column<Integer>("Product Id", "product_id", 0);
	private final Column<Integer> customerId = new Column<Integer>("Customer Id", "customer_id", 0);
	private final Column<Integer> storeId = new Column<Integer>("Store Id", "store_id", 0);
	private final Column<LocalDate> saleDate = new Column<LocalDate>("Sale Date", "sale_date", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	private final List<Column> columns = Arrays.asList(productId, customerId, storeId, saleDate);

	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		saleId.setVal(resultSet.getInt(saleId.getDataName()));
		productId.setVal(resultSet.getInt(productId.getDataName()));
		customerId.setVal(resultSet.getInt(customerId.getDataName()));
		storeId.setVal(resultSet.getInt(storeId.getDataName()));
		saleDate.setVal(resultSet.getDate(saleDate.getDataName()).toLocalDate());
	}
	
	@Override
	public void mapClientArgsToEntity(String[] args) {
		productId.setVal(Integer.parseInt(args[0]));
		customerId.setVal(Integer.parseInt(args[1]));
		storeId.setVal(Integer.parseInt(args[2]));
		saleDate.setVal(LocalDate.parse(args[3]));
		
	}

	@Override
	public String getTableName() {
		return "Sale";
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return saleId;
	}

}
