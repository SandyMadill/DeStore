package deStoreClientApp;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import deStoreClientApp.formComponents.FormCheckBoxComponent;
import deStoreClientApp.formComponents.FormDateComponent;
import deStoreClientApp.formComponents.FormTextComponent;

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
	protected ArrayList<FormComponent> formComponents;
	protected TableModel formTable;
	protected JPanel formPannel;
	
	protected static Map<String, ?> formComponentMap = Map.of(
				"LocalDate", FormDateComponent.class,
				"String" , FormTextComponent.class,
				"Integer" , FormTextComponent.class,
				"Float" , FormTextComponent.class,
				"Boolean" , FormCheckBoxComponent.class
			);

	/**
	 * Create the frame.
	 */
	public TableFormView(TableModel table) {
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] newColumnVals = new Object[formComponents.size()];
				for (int i=0;i<formComponents.size();i++) {
					newColumnVals[i] = formComponents.get(i).getVal();
				}
				onSubmit(newColumnVals);
			}
		});
		
		setResizable(false);
		formComponents = new ArrayList<FormComponent>();
		formTable = table;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		formPannel = new JPanel();
		formPannel.setMinimumSize(new Dimension(240, 420));
		scrollPane.setViewportView(formPannel);
		formPannel.setBorder(new EmptyBorder(5, 5, 5, 5));
		formPannel.setLayout(new GridLayout(0, 1, 0, 0));
		
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
	
	protected abstract void onSubmit(Object[] newColumnVals);

}
