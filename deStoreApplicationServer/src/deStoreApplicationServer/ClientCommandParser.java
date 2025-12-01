package deStoreApplicationServer;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;

import deStoreApplicationServer.tableEntities.TableEntity;


public class ClientCommandParser implements Runnable {
	
	private Socket sock;
	private DataRequestManager dataRequestManager;
	private ObjectOutputStream objectOutputStream;
	
	ClientCommandParser(Socket sock, DataRequestManager dataRequestManager){
		try {
			this.sock = sock;
			this.dataRequestManager = dataRequestManager;
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***
	 * splits up the command into an array of arguments and maps it to it's appropriate implementation of the client command interface
	 * @param command the client command
	 * @return the appropriate implementation of the client command interface as identified by the first word in the client command
	 * @throws IOException if the socket connecting the user to the server breaks down while the command is being executed
	 */
	public ClientCommand parseCommand(String command) throws IOException {
		List<String> tokens= new ArrayList<String>();
		Boolean quoteOpen = false;
		String inQuote = "";
		String[] splitCommand = command.split(" ");
		
		//	arguments in quotations are allowed to contain more than one word 
		//	so this for loop joins up any words that are within a quotation argument
		for (int i=0;i<splitCommand.length;i++) {
			if (quoteOpen == false) {
				if (splitCommand[i].charAt(0) == '"') {
					quoteOpen = true;
				}
				else {
					tokens.add(splitCommand[i]);
				}
			}
			if (quoteOpen == true) {
				inQuote += splitCommand[i];
				if (splitCommand[i].charAt(splitCommand[i].length()-1) == '"') {
					quoteOpen = false;
					tokens.add(inQuote.replaceAll("\"", ""));
					inQuote = "";
				}
				else {
					inQuote += " ";
				}
			}
			
		}
		
		
		String[] args = new String[tokens.size()-1];
		for (int i=0;i < tokens.size()-1;i++) {
			args[i]= tokens.get(i+1);
		}
		
		
		//	map the command to it's accurate implementation
		if (tokens.get(0).equals("insert")) {
			return new InsertCommand(args, dataRequestManager, objectOutputStream);
		}
		else if (tokens.get(0).equals("remove")) {
			return new RemoveCommand( args, dataRequestManager, objectOutputStream);
		} else if (tokens.get(0).equals("update")) {
			return new UpdateCommand(args, dataRequestManager, objectOutputStream);
		} else if (tokens.get(0).equals("report")) {
			return new ReportCommand(args, dataRequestManager, objectOutputStream);
		}
		else if (tokens.get(0).equals("report-names")) {
			return new GetReportNamesCommand(dataRequestManager, objectOutputStream);
		}
		else if (tokens.get(0).equals("columns") ) {
			return new GetColumnsForReport(tokens.get(1), dataRequestManager, objectOutputStream);
		}
		else {
			return new ErrorCommand(("Error: Unknown command \"" + tokens.get(0) + "\""), objectOutputStream);
		}
	}
	
	@Override
	/***
	 * Will determine the implementation of the client command interface made by the user and executes it
	 */
	public void run() {
		String address = sock.getRemoteSocketAddress().toString();
		try {
			objectOutputStream.writeObject("");
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String commandString = inputStream.readLine();
			ClientCommand command = parseCommand(commandString);
			try {
				command.exec();
			} catch (Exception e) {
				e.printStackTrace();
				objectOutputStream.writeObject(e);
			}
			sock.close();
			
		} catch (IOException e) {
			System.out.println("Connection with adress: "+ address + "broken!");
			e.printStackTrace();
		}
	}
}
