package deStoreClientApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

public class InsertTableForm extends TableFormView {

	public InsertTableForm(TableModel table) {
		super(table);
		for (int i=1;i <table.getColumnNames().length;i++) {
			String columnName = table.getColumnNames()[i];
			String columnType = table.getDataTypeFromColumnName(columnName);
			try {
				FormComponent formComponent = (FormComponent) ((Class) this.formComponentMap.get(columnType)).getConstructor(null).newInstance();
				formComponent.setLabel(columnName);
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
			formTable.insertRow(newColumnVals);
			dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
