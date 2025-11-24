package deStoreClientApp;

public enum FilterCategory {
	Greater(">"),
	LESS("<"),
	EQUAL("=");
	
	private String operator;
	
	private FilterCategory(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return operator;
	}
}
