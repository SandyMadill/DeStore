package deStoreClientApp;

import java.io.*;
import java.net.*;

public class ClientMain {
	private static Socket sock;
	
	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		String ip = config.getAppServerIp();
		int port = Integer.parseInt(config.getAppServerPort());
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				String command = input.readLine();
				sock = new Socket(ip,port);
				ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
				DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
				System.out.println(command);
				inputStream.readObject();
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				outputStream.writeBytes(command + '\n');
				System.out.println(inputStream.readObject().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
