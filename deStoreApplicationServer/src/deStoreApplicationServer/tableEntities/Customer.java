package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Customer extends TableEntity {
	private final Column<Integer> customerId = new Column<Integer>("Customer Id", "customer_id", 0);
	private final Column<String> forename = new Column<String>("Forename", "forename", "");
	private final Column<String> surname = new Column<String>("Surname", "surname", "");
	private final Column<String> address = new Column<String>("Address", "address", "");
	private final Column<Float> totalSpent = new Column<Float>("Total Spent", "total_spent", 0.0F);
	private final List<Column> columns = Arrays.asList(forename, surname, address, totalSpent);
	
	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		customerId.setVal(resultSet.getInt(customerId.getDataName()));
		forename.setVal(resultSet.getString(forename.getDataName()));
		surname.setVal(resultSet.getString(surname.getDataName()));
		address.setVal(resultSet.getString(address.getDataName()));
		totalSpent.setVal(resultSet.getFloat(totalSpent.getDataName()));
	}
	
	@Override
	public void mapClientArgsToEntity(String[] args) {
		forename.setVal(args[0]);
		surname.setVal( args[1]);
		address.setVal( args[2]);
		totalSpent.setVal(Float.parseFloat(args[3]));
		
	}

	@Override
	public String getTableName() {
		return "Customer";
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return customerId;
	}

}
