package deStoreApplicationServer;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ClientCommandParser clientCommandParser = new ClientCommandParser();
		DataRequestManager dataRequestManager = new DataRequestManager();
		Scanner scanner = new Scanner(System.in);
		String com = "";
		while (!com.equals("quit")) {
			com = scanner.nextLine();
			ClientCommand clientCommand = clientCommandParser.parseCommand(com);
			clientCommand.exec(null);
		}
	}

}
