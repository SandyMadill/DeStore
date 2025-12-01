package deStoreClientApp;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class UpdateTableForm extends TableFormView {
	private int row;

	public UpdateTableForm(TableModel table, int row) {
		super(table);
		
		this.row = row;
		
		Object[] rowData = (Object[]) table.getTableData()[row];
		
		for (int i=1;i<rowData.length;i++) {
			//super.textFields.get(i-1).setText((String) rowData[i]);
			String columnName = table.getColumnNames()[i];
			String columnType = table.getDataTypeFromColumnName(columnName);
			try {
				FormComponent formComponent = (FormComponent) ((Class) this.formComponentMap.get(columnType)).getConstructor(null).newInstance();
				formComponent.setLabel(columnName);
				formComponent.setVal(rowData[i]);
				this.formComponents.add(formComponent);
				this.formPannel.add(formComponent.getFrame());
				
				
			} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	protected void onSubmit(Object[] newColumnVals) {
		Object[] newColumns = new Object[formComponents.size()];
		for (int i=0;i<formComponents.size();i++) {
			newColumns[i] = formComponents.get(i).getVal();
		}
		
		try {
			formTable.updateRow(row, newColumns);
			dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
