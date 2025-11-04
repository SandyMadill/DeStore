package deStoreApplicationServer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportCommand implements ClientCommand {
	private ArrayList<String> args;
	private ObjectOutputStream objectOutputStream;
	private DataRequestManager dataRequestManager;
	
	public ReportCommand(String[] args, DataRequestManager dataRequestManager, ObjectOutputStream objectOutputStream) {
		this.args = new ArrayList(Arrays.asList(args));
		this.dataRequestManager = dataRequestManager;
		this.objectOutputStream = objectOutputStream;
	}
	

	@Override
	public void exec() {
		try {
			String tableName = args.get(0);
			List<WhereStmnt> wheres = new ArrayList<WhereStmnt>();
			try {
				wheres.addAll(WhereStmnt.getWhereStmntsFromArgs(args));
			} catch (Exception e) {
				objectOutputStream.writeObject(e.getMessage());
			} finally {
				System.out.println("sflksdjflksdjflkejlkfjlkjwerfoierfjhejfhejkdf");
				int w = args.indexOf("-w");
				if (w == -1) {
					w = args.size();
				}
				List<String> columns = args.subList(1, w);
				objectOutputStream.writeObject(dataRequestManager.select(tableName, columns, wheres));
			}
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ObjectOutputStream getObjectOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
