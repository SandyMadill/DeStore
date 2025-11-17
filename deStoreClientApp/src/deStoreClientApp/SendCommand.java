package deStoreClientApp;
import java.io.*;
import java.net.*;

public class SendCommand {
	public static <T> T send(String command){
		ClientConfig config = new ClientConfig();
		T result = null;
		try {
			Socket sock = new Socket(config.getAppServerIp(), Integer.parseInt(config.getAppServerPort()));
			ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
			inputStream.readObject();
			outputStream.writeBytes(command + '\n');
			result = (T) inputStream.readObject();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
