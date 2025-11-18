package deStoreClientApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTableForm extends TableForm {

	public UpdateTableForm(Table table, int row) {
		super(table);
		
		String[] rowData = (String[]) table.getTableData()[row];
		
		for (int i=1;i<rowData.length;i++) {
			super.textFields.get(i-1).setText(rowData[i]);
		}
		
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] newColumns = new String[textFields.size()];
				for (int i=0;i<textFields.size();i++) {
					newColumns[i] = textFields.get(i).getText();
				}
				
				table.updateRow(row, newColumns);
				
			}
			
		});
	}

}
