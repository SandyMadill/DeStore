package deStoreClientApp;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class TableFilterFormController extends TableFilterFormView {

	private TableModel tableModel;
	
	public TableFilterFormController(TableModel tableModel) {
		super();
		this.tableModel = tableModel;
		cmbColumn.setModel(new DefaultComboBoxModel(tableModel.getColumnNames()));
	}
	
	@Override
	protected void btnSubmitClicked() {
		String[] args = new String[3];
		args[0] = (String) cmbColumn.getSelectedItem();
		FilterCategory cat = (FilterCategory) cmbFilterCat.getSelectedItem();
		args[1] = cat.getOperator();
		args[2] = txtValue.getText();
		try {
			tableModel.addFilter(args);
			dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
