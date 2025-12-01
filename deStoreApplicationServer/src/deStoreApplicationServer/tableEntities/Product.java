package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Product extends TableEntity {
	private final Column<Integer> productId = new Column<Integer>("Product Id", "product_id", 0);
	private final Column<String> productName = new Column<String>("Name", "product_name", "");
	private final Column<Float> productPrice = new Column<Float>("Price", "product_price", 0.0F);
	private final List<Column> columns = Arrays.asList(productName, productPrice);

	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		productId.setVal(resultSet.getInt(productId.getDataName()));
		productName.setVal(resultSet.getString(productName.getDataName()));
		productPrice.setVal(resultSet.getFloat(productPrice.getDataName()));
	}
	
	@Override
	public void mapClientArgsToEntity(String[] args) {
		productName.setVal(args[0]);
		productPrice.setVal(Float.parseFloat(args[1]));
		
	}

	@Override
	public String getTableName() {
		return "Product";
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return productId;
	}

}
