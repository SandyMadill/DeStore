package deStoreClientApp.formComponents;

import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import deStoreClientApp.FormComponent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.LayoutStyle.ComponentPlacement;

public class FormDateComponent extends JPanel implements FormComponent {
	private JDateChooser dateChooser;
	private JLabel label;
	public FormDateComponent() {
		dateChooser = new JDateChooser();
		
		label = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addContainerGap(126, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
							.addGap(102))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@Override
	public JPanel getFrame() {
		return this;
	}

	@Override
	public Object getVal() {
		return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public void setVal(Object val) { 
		LocalDate localDate = (LocalDate) val;
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dateChooser.setDate(date);
		
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);	
	}
}
