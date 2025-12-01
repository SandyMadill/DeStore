package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PriceControl extends TableEntity {
	private final Column<Integer> priceControlId = new Column<Integer>("Price Control Id", "price_control_id", 0);
	private final Column<Integer> productId = new Column<Integer>("Product Id", "product_id", 0);
	private final Column<Integer> storeId = new Column<Integer>("Store Id", "store_id", 0);
	private final Column<Integer> minQuantity = new Column<Integer>("Minimum Quantity", "min_quantity", 0);
	private final Column<Float> rate = new Column<Float>("rate", "rate", 0.0F);
	private final List<Column> columns = Arrays.asList(productId, storeId, minQuantity, rate);
	
	
	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		priceControlId.setVal(resultSet.getInt(priceControlId.getDataName()));
		productId.setVal(resultSet.getInt(productId.getDataName()));
		storeId.setVal(resultSet.getInt(storeId.getDataName()));
		minQuantity.setVal(resultSet.getInt(minQuantity.getDataName()));
		rate.setVal(resultSet.getFloat(rate.getDataName()));

	}
	
	@Override
	public void mapClientArgsToEntity(String[] args) {
		productId.setVal(Integer.parseInt(args[0]));
		storeId.setVal(Integer.parseInt(args[1]));
		minQuantity.setVal(Integer.parseInt(args[2]));
		rate.setVal(Float.parseFloat(args[3]));
		
	}

	@Override
	public String getTableName() {
		return "Price_Control";
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return priceControlId;
	}

}
