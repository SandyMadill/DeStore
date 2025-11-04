package deStoreApplicationServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Date;

/****
 * Handles requests to the app server from the client
 */
public class ClientRequestManager {
	
	private DataRequestManager dataRequestManager;
	private ServerSocket server;
	private AppServerConfig config;
	
	public ClientRequestManager(DataRequestManager dataRequestManager) {
		try {
			this.dataRequestManager = dataRequestManager;
			config = new AppServerConfig();
			server = new ServerSocket(Integer.parseInt(config.getAppServerPort()));
			recieveClientRequests();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void recieveClientRequests() {
		while(true) {
			try {
				Socket sock = server.accept();
				Thread thread = new ClientCommandParser(sock, dataRequestManager);
				thread.run();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
