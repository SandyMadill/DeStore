package deStoreClientApp;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientMain {
	private static List<TableModel> tableList; 
	
	public static void main(String[] args) {
		tableList = new ArrayList<TableModel>();
		try {
			TableModel.getTableNames().forEach(tableName ->{
				try {
					tableList.add(new TableModel(tableName));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainController(tableList);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
