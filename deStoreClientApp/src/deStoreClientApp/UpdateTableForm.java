package deStoreClientApp;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class UpdateTableForm extends TableFormView {
	private int row;

	public UpdateTableForm(TableModel table, int row) {
		super(table);
		
		this.row = row;
		
		String[] rowData = (String[]) table.getTableData()[row];
		
		for (int i=1;i<rowData.length;i++) {
			super.textFields.get(i-1).setText(rowData[i]);
		}
		
		
	}

	@Override
	protected void onSubmit() {
		String[] newColumns = new String[textFields.size()];
		for (int i=0;i<textFields.size();i++) {
			newColumns[i] = textFields.get(i).getText();
		}
		
		try {
			formTable.updateRow(row, newColumns);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		finally {
			dispose();
		}
		
	}

}
