package deStoreApplicationServer.tableEntities;

import java.lang.reflect.ParameterizedType;

public class Column<T> {
	private String appName;
	private String dataName;
	private T val;
	
	public Column(String appName, String dataName, T defaultVal) {
		this.appName = appName;
		this.dataName = dataName;
		val = defaultVal;
	}
	
	public String getAppName() {
		return appName;
	};
	
	public String getDataName() {
		return dataName;
	}
	
	public void setVal(T val) {
		this.val = val;
	}
	
	public T getVal() {
		return val;
	}
	
	
	public String getType(){
		return val.getClass().getSimpleName();
		
	}
	
}
