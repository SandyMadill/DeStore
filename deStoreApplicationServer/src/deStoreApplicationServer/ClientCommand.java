package deStoreApplicationServer;

import java.io.*;
import java.sql.SQLException;

public interface ClientCommand {
	
	public void exec() throws Exception;
}
