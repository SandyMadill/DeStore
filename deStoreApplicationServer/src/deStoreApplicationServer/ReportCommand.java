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
	public void exec() throws Exception {
		String tableName = args.get(0);
		List<CompareStmnt> wheres = new ArrayList<CompareStmnt>();
		wheres.addAll(CompareStmnt.getCompareStmntsFromArgs(args, "w"));
		int w = args.indexOf("-w");
		if (w == -1) {
			w = args.size();
		}
		List<String> columns = args.subList(1, w);
		Object[] result = dataRequestManager.select(tableName, columns, wheres);
		objectOutputStream.writeObject(result);
	}
}
