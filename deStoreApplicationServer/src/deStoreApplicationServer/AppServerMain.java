package deStoreApplicationServer;

import java.util.Scanner;

public class AppServerMain {

	public static void main(String[] args) {
		DataRequestManager dataRequestManager = new DataRequestManager();
		ClientRequestManager clientRequestManager = new ClientRequestManager(dataRequestManager);
	}

}
