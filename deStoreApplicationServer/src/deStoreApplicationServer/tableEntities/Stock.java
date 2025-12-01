package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Stock extends TableEntity {
	private final Column<Integer> stockId = new Column<Integer>("Stock Id", "stock_id", 0);
	private final Column<Integer> productId = new Column<Integer>("Product Id", "product_id", 0);
	private final Column<Integer> storeId = new Column<Integer>("Store Id", "store_id", 0);
	private final Column<Integer> stock = new Column<Integer>("stock", "stock", 0);
	private final List<Column> columns = Arrays.asList(productId, storeId, stock);

	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		stockId.setVal(resultSet.getInt(stockId.getDataName()));
		productId.setVal(resultSet.getInt(productId.getDataName()));
		storeId.setVal(resultSet.getInt(storeId.getDataName()));
		stock.setVal(resultSet.getInt(stock.getDataName()));
	}
	
	@Override
	public void mapClientArgsToEntity(String[] args) {
		productId.setVal(Integer.parseInt(args[0]));
		storeId.setVal(Integer.parseInt(args[1]));
		stock.setVal(Integer.parseInt(args[2]));
		
	}

	@Override
	public String getTableName() {
		return "Stock";
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return stockId;
	}

}
