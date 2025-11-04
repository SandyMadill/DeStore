package deStoreApplicationServer;

import java.io.*;

public interface ClientCommand {
	
	public ObjectOutputStream getObjectOutputStream();
	
	public String help();
	
	public void exec();
}
