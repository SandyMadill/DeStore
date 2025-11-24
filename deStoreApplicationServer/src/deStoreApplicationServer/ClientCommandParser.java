package deStoreApplicationServer;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;


public class ClientCommandParser extends Thread {
	
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
	
	public ClientCommand parseCommand(String command) throws IOException {
		List<String> tokens= new ArrayList<String>();
		Boolean quoteOpen = false;
		String inQuote = "";
		String[] splitCommand = command.split(" ");
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
		else if (tokens.get(0).equals("table-names")) {
			return new GetTableNamesCommand(dataRequestManager, objectOutputStream);
		}
		else if (tokens.get(0).equals("columns") ) {
			return new GetColumnsForTable(tokens.get(1), dataRequestManager, objectOutputStream);
		}
		else {
			return new ErrorCommand(("Error: Unknown command \"" + tokens.get(0) + "\""), objectOutputStream);
		}
	}
	
	@Override
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
				objectOutputStream.writeObject(e);
			}
			sock.close();
			
		} catch (IOException e) {
			System.out.println("Connection with adress: "+ address + "broken!");
			e.printStackTrace();
		}
	}
}
