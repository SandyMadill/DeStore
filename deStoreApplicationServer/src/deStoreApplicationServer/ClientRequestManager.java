package deStoreApplicationServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Date;

import org.json.simple.parser.ParseException;

/****
 * Handles requests to the app server from the client
 */
public class ClientRequestManager implements Runnable {
	
	private DataRequestManager dataRequestManager;
	private ServerSocket server;
	
	public ClientRequestManager(DataRequestManager dataRequestManager) throws NumberFormatException, IOException, ParseException {
		this.dataRequestManager = dataRequestManager;
		server = new ServerSocket(Integer.parseInt(AppServerConfig.getAppServerPort()));
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Socket sock = server.accept();
				Thread thread = new Thread(new ClientCommandParser(sock, dataRequestManager));
				thread.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
