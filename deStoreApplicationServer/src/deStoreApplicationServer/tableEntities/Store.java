package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Store extends TableEntity {
	private final Column<Integer> storeId = new Column<Integer>("Store Id", "store_id", 0);
	private final Column<String> storeName = new Column<String>("Store Name","store_name", "");
	private final Column<String> storeAddress = new Column<String>("Store Address", "store_address", "");
	private final List<Column> columns = (Arrays.asList(storeName, storeAddress));
	
	public Store() {
		
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return storeId;
	}

	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		storeId.setVal(resultSet.getInt(storeId.getDataName()));
		storeName.setVal(resultSet.getString(storeName.getDataName()));
		storeAddress.setVal(resultSet.getString(storeAddress.getDataName()));
	}
	
	@Override
	public void mapClientArgsToEntity(String[] args) {
		storeName.setVal((String) args[0]);
		storeAddress.setVal((String) args[1]);
		
	}

	@Override
	public String getTableName() {
		return "Store";
	}

}
