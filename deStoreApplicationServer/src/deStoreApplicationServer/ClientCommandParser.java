package deStoreApplicationServer;
import java.io.*;
import java.util.*;

public class ClientCommandParser {
	
	public ClientCommand parseCommand(String command) {
		StringTokenizer tokenizer = new StringTokenizer(command, " ");
		command = tokenizer.nextToken();
		String[] args = new String[tokenizer.countTokens()];
		for (int i = 0; tokenizer.hasMoreTokens(); i++) {
			args[i] = tokenizer.nextToken();
		}
		
		if (command.equals("insert")) {
			return new InsertCommand<Boolean>(args);
		}
		else if (command.equals("remove")) {
			return new RemoveCommand<Boolean>(args);
		} else if (command.equals("update")) {
			return new UpdateCommand<Boolean>(args);
		} else if (command.equals("report")) {
			return new ReportCommand(args);
		}
		
		return null;
	}
}
