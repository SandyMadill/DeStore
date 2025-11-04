package deStoreApplicationServer;
import java.io.*;
import java.net.*;
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
		System.out.println(command);
		List<String> tokens= new ArrayList<String>();
			Arrays.asList(
				command.split(" (?=(?:[^\"]*\"[^\"]*\"[^\"]*)*$)")).forEach(a ->{
					a = a.replaceAll("[\"]", "");
					tokens.addAll(Arrays.asList(a.split(" ")));
				});
		String[] args = new String[tokens.size()-1];
		for (int i=0;i < tokens.size()-1;i++) {
			args[i]= tokens.get(i+1);
		}
		
		System.out.println(tokens.get(0));
		
		if (tokens.get(0).equals("insert")) {
			return new InsertCommand(args, dataRequestManager, objectOutputStream);
		}
		else if (tokens.get(0).equals("remove")) {
			return new RemoveCommand( args, dataRequestManager, objectOutputStream);
		} else if (tokens.get(0).equals("update")) {
			return new UpdateCommand(args, dataRequestManager, objectOutputStream);
		} else if (tokens.get(0).equals("report")) {
			System.out.println("fgjsflksjdflkjsdlkfjsdlkfjsdlkjfk");
			return new ReportCommand(args, dataRequestManager, objectOutputStream);
		}
		
		return null;
	}
	
	@Override
	public void run() {
		try {
			objectOutputStream.writeObject("");
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String commandString = inputStream.readLine();
			ClientCommand command = parseCommand(commandString);
			command.exec();
			sock.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
