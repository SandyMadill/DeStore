package deStoreClientApp.formComponents;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import deStoreClientApp.FormComponent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FormTextComponent extends JPanel implements FormComponent {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel label;
	
	/**
	 * Create the panel.
	 */
	public FormTextComponent() {
		
		textField = new JTextField();
		textField.setColumns(20);
		textField.setText("");
		
		label = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

	@Override
	public Object getVal() {
		return textField.getText();
	}

	@Override
	public JPanel getFrame() {
		return this;
	}

	@Override
	public void setVal(Object val) {
		this.textField.setText(val.toString());
		
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);
		
	}
}
