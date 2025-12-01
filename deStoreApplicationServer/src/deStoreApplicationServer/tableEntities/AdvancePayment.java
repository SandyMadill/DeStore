package deStoreApplicationServer.tableEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AdvancePayment extends TableEntity {
	private final Column<Integer> advancePaymentId = new Column<Integer>("Advance Payment Id","advance_payment_id", 0);
	private final Column<Integer> productId = new Column<Integer>("Product Id","product_id", 0);
	private final Column<Integer> customerId = new Column<Integer>("Customer Id", "customer_id", 0);
	private final Column<Integer> paymentRate = new Column<Integer>("Payment Rate","payment_rate", 0);
	private final Column<LocalDate> purchaseDate = new Column<LocalDate>("Purchase Date", "purchase_date", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	private final Column<Integer> paymentsMade = new Column<Integer>("Payments Made", "payments_made", 0);
	private final Column<Float> paymentCost = new Column<Float>("Payment Cost", "payment_cost", 0.0F);
	private final Column<Boolean> complete = new Column<Boolean>("Complete", "complete", false);
	private final List<Column> columns = Arrays.asList(productId, customerId, paymentRate, purchaseDate, paymentsMade, paymentCost, complete);
	@Override
	public void mapDataToAppEntity(ResultSet resultSet) throws SQLException {
		advancePaymentId.setVal(resultSet.getInt(advancePaymentId.getDataName()));
		productId.setVal(resultSet.getInt(productId.getDataName()));
		customerId.setVal(resultSet.getInt(customerId.getDataName()));
		paymentRate.setVal(resultSet.getInt(paymentRate.getDataName()));
		purchaseDate.setVal(resultSet.getDate(purchaseDate.getDataName()).toLocalDate());
		paymentsMade.setVal(resultSet.getInt(paymentsMade.getDataName()));
		paymentCost.setVal(resultSet.getFloat(paymentCost.getDataName()));
		complete.setVal(resultSet.getBoolean(complete.getDataName()));
		
	}

	@Override
	public String getTableName() {
		return "Advance_Payment";
	}

	@Override
	public List<Column> getColumns() {
		return columns;
	}

	@Override
	public Column<Integer> getKey() {
		return advancePaymentId;
	}

	@Override
	public void mapClientArgsToEntity(String[] args) {
		productId.setVal(Integer.parseInt(args[0]));
		customerId.setVal(Integer.parseInt(args[1]));
		paymentRate.setVal(Integer.parseInt(args[2]));
		purchaseDate.setVal(LocalDate.parse(args[3]));
		paymentsMade.setVal(Integer.parseInt(args[4]));
		paymentCost.setVal(Float.parseFloat(args[5]));
		complete.setVal(Boolean.parseBoolean(args[6]));
		
	}
	
}
