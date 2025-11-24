package deStoreClientApp;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class TableFormView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected ArrayList<JTextField> textFields;
	protected TableModel formTable;

	
	private JPanel createFormComponent(String columnName) {
		
		JPanel newPanel = new JPanel();
		newPanel.add(new JLabel(columnName));
		JTextField textField = null;
		try {
			textField = formTable.getTextFieldForColumn(columnName);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		textField.setColumns(10);
		textFields.add(textField);
		newPanel.add(textField);
		return newPanel;
	}
	

	/**
	 * Create the frame.
	 */
	public TableFormView(TableModel table) {
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSubmit();
			}
		});
		
		setResizable(false);
		textFields = new ArrayList<JTextField>();
		formTable = table;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel formPannel = new JPanel();
		formPannel.setMinimumSize(new Dimension(240, 420));
		scrollPane.setViewportView(formPannel);
		formPannel.setBorder(new EmptyBorder(5, 5, 5, 5));
		formPannel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel submitPannel = new JPanel();
		formPannel.add(submitPannel);
		
		String[] columnNames = formTable.getColumnNames();
		int key = table.getKey();
		for (int i=0;i< columnNames.length;i++) {
			if (i!=key) {
				formPannel.add(createFormComponent(columnNames[i]));
			}
		}
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(149)
							.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	protected abstract void onSubmit();

}
