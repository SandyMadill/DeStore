package deStoreApplicationServer;
import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;

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
			List<CompareStmnt> wheres = new ArrayList<CompareStmnt>();
			try {
				wheres.addAll(CompareStmnt.getCompareStmntsFromArgs(args, "w"));
				int w = args.indexOf("-w");
				if (w == -1) {
					w = args.size();
				}
				List<String> columns = args.subList(1, w);
				JSONArray result = dataRequestManager.select(tableName, columns, wheres);
				System.out.println(result.toString());
				objectOutputStream.writeObject(result);
			} catch (Exception e) {
				objectOutputStream.writeObject(e.getMessage());
			}
		
		}
		catch (IOException e) {
			
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
