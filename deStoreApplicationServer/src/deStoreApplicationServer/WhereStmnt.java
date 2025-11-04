package deStoreApplicationServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/******
 * 	parses an sql where stmnt for the data request manager
 */
public class WhereStmnt {
	private String operator;
	private String column;
	private String val;
	
	static List<String> operatorList = Arrays.asList(new String[] {
			"=",
			">",
			"<",
			">=",
			"<=",
			"<>"
	});
	
	/*********
	 * crates a list of where statements from a list of arguments
	 * @param args the arguments
	 * @return list of where stmnts
	 * @throws Exception
	 */
	public static List<WhereStmnt> getWhereStmntsFromArgs(List<String> args) throws Exception{
		ArrayList<WhereStmnt> wheres = new ArrayList<WhereStmnt>();
		int wLast = 0;
		for (int i = 0; i < args.size(); i++) {
			if (args.get(i).equals("-w")) {
				if (wheres.size() > 0 || wLast + 3 == i) {
					try {
						wheres.add(new WhereStmnt(args.get(i+1), args.get(i+2), args.get(i+3)));
					} catch (IndexOutOfBoundsException e) {
						throw new Exception("a where statment must have 3 parameters");
					}
				}
				else {
					throw new Exception("a where statment must have 3 parameters");
				}
			}
		}
		
		return wheres;
	}
	
	
	public WhereStmnt(String operator, String column, String val ) throws Exception {
		if(operatorList.contains(operator)) {
			this.operator = operator;
			this.column = column;
			this.val = val;
		}
		else {
			throw (new Exception("Invalid operator '" + operator + "'"));
		}
	}
	
	/***
	 * produces the section of the base prepared statement for this where clause
	 * @return the seciont of the prepared statement for this where clause
	 */
	public String getPreparedStatementSlice() {
		return "? " + operator + " ?";
	}
	
	/***
	 * produces a list containing the params for this where clause in order
	 * @return the list containing the params for this where clause in order
	 */
	public List<String> getParams(){
		return Arrays.asList(new String[] {column, val});
	}
}
