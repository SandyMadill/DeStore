package deStoreClientApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class InsertTableForm extends TableFormView {

	public InsertTableForm(TableModel table) {
		super(table);
		
	}

	@Override
	protected void onSubmit() {
		String[] newColumns = new String[textFields.size()];
		for (int i=0;i<textFields.size();i++) {
			newColumns[i] = textFields.get(i).getText();
		}
		
		try {
			formTable.insertRow(newColumns);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			dispose();
		}
		
	}

}
