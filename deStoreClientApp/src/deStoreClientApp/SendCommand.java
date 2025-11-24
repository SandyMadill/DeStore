package deStoreClientApp;
import java.io.*;
import java.net.*;

public class SendCommand {
	public static <T> T send(String command) throws Exception, UnknownHostException{
		T result = null;
		Object res = null;
		String ip = ClientConfig.getAppServerIp(); 
		int port = 0;
		try {
			port = Integer.parseInt(ClientConfig.getAppServerPort());
		}
		catch (NumberFormatException e) {
			
		}
		
		Socket sock = new Socket(ip, port);
		ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
		inputStream.readObject();
		outputStream.writeBytes(command + '\n');
		res = inputStream.readObject();
		result = (T) res;
			
		return result;
	}
}
