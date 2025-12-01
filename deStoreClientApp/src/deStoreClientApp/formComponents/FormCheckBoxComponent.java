package deStoreClientApp.formComponents;

import javax.swing.JPanel;

import deStoreClientApp.FormComponent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;

public class FormCheckBoxComponent extends JPanel implements FormComponent {

	private static final long serialVersionUID = 1L;
	private JCheckBox checkBox;

	/**
	 * Create the panel.
	 */
	public FormCheckBoxComponent() {
		
		checkBox = new JCheckBox("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkBox)
					.addContainerGap(142, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkBox)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	public JPanel getFrame() {
		return this;
	}

	@Override
	public Object getVal() {
		return checkBox.isSelected();
	}

	@Override
	public void setVal(Object val) {
		checkBox.setSelected((boolean) val);
		
	}

	@Override
	public void setLabel(String label) {
		checkBox.setLabel(label);
		
	}
}
