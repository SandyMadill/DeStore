package deStoreApplicationServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/******
 * 	parses an sql where stmnt for the data request manager
 */
public class CompareStmnt {
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
	 * @return list of comparison stmnts
	 * @throws Exception
	 */
	public static List<CompareStmnt> getCompareStmntsFromArgs(List<String> args, String flag) throws Exception{
		ArrayList<CompareStmnt> wheres = new ArrayList<CompareStmnt>();
		int last = 0;
		for (int i = 0; i < args.size(); i++) {
			if (args.get(i).equals("-"+flag)) {
				System.out.println(last);
				System.out.println(i);
				if (wheres.size() == 0 || last + 4 == i) {
					try {
						last = i;
						wheres.add(new CompareStmnt(args.get(i+1), args.get(i+2), args.get(i+3)));
					} catch (IndexOutOfBoundsException e) {
						throw new Exception("a compare statement must have 3 parameters");
					}
				}
				else {
					throw new Exception("a compare statement must have 3 parameters");
				}
			}
		}
		
		return wheres;
	}
	
	
	public CompareStmnt(String column, String operator,  String val ) throws Exception {
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
		return column + " " + operator + " ?";
	}
	
	public String getVal() {
		return val;
	}
}
