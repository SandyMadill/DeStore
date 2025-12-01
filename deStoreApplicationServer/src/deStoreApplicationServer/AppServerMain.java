package deStoreApplicationServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

import org.json.simple.parser.ParseException;


public class AppServerMain {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		DataRequestManager dataRequestManager = startDataRequestManager();
		ClientRequestManager clientRequestManager = startClientRequestManager(dataRequestManager);
		console(dataRequestManager, clientRequestManager);
	}
	
	/***
	 * local console can edit config properties and start up the server connectors
	 */
	private static void console(DataRequestManager dataRequestManager, ClientRequestManager clientRequestManager) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			String command = scan.nextLine();
			
			String args[] = command.split(" ");
			
			try {
				if (args[0].equals("data-server")) {
					if (dataRequestManager != null) {
						System.out.println("The data server is already running");
					}
					else {
						dataRequestManager = startDataRequestManager();
					}
				}
				else if (args[0].equals("app-server")) {
					if (clientRequestManager != null) {
						System.out.println("The app server is already running");
					}
					else {
						clientRequestManager = startClientRequestManager(dataRequestManager);
					}
				}
				else if (args[0].equals("config")) {
					if (args[1].equals("app-port")) {
						AppServerConfig.setAppServerPort(args[2]);
					}
					else if (args[1].equals("data-ip")) {
						AppServerConfig.setDataServerIp(args[2]);
					}
					else if (args[1].equals("data-port")) {
						AppServerConfig.setDataServerPort(args[2]);
					}
					else if (args[1].equals("data-pass")) {
						AppServerConfig.setDataServerPassword(args[2]);
					}
					else {
						System.out.println("Unknown config propert \"" + args[1] + "\"");
					}
				}
			
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Too few arguments");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private static DataRequestManager startDataRequestManager() {
		System.out.println("Connecting to data server");
		try {
			DataRequestManager dataRequestManager = new DataRequestManager();
			System.out.println("Connected to data server");
			return dataRequestManager;
		} catch (ClassNotFoundException | SQLException | IOException | ParseException e) {
			System.out.println("Failed to connect to the data server");
			System.out.println("Either the config properties are wrong or the server is not running");
			System.out.println("Please enter data-server to try again");
			return null;
		}
	}
	
	private static ClientRequestManager startClientRequestManager(DataRequestManager dataRequestManager) {
		if (dataRequestManager == null) {
			System.out.println("the data request manager needs to be functioning before the app server can start");
			return null;
		}
		
		System.out.println("Starting the app server");
		try {
			ClientRequestManager clientRequestManager = new ClientRequestManager(dataRequestManager);
			Thread thread = new Thread(clientRequestManager);
			thread.start();
			System.out.println("App server started successfully");
			return clientRequestManager;
		} catch (NumberFormatException | IOException | ParseException e) {
			System.out.println("Failed to start the app server");
			System.out.println("Either the config app server port is not a number or the port is already in use");
			System.out.println("Please enter app-server to try again");
			return null;
		}
	}

}
