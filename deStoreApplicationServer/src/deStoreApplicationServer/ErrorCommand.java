package deStoreApplicationServer;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ErrorCommand implements ClientCommand {
	private ObjectOutputStream objectOutputStream;
	private String message;
	public ErrorCommand(String message, ObjectOutputStream objectOutputStream) {
		this.message = message;
		this.objectOutputStream = objectOutputStream;
	}

	@Override
	public ObjectOutputStream getObjectOutputStream() {
		// TODO Auto-generated method stub
		return objectOutputStream;
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exec() {
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
