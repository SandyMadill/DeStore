package deStoreApplicationServer;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		DataRequestManager dataRequestManager = new DataRequestManager();
		ClientRequestManager clientRequestManager = new ClientRequestManager(dataRequestManager);
	}

}
