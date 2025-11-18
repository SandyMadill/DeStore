package deStoreClientApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertTableForm extends TableForm {

	public InsertTableForm(Table table) {
		super(table);
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] newColumns = new String[textFields.size()];
				for (int i=0;i<textFields.size();i++) {
					newColumns[i] = textFields.get(i).getText();
				}
				
				table.insertRow(newColumns);
				
			}
			
		});
	}

}
