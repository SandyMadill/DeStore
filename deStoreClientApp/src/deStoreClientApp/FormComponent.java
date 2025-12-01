package deStoreClientApp;

import javax.swing.JPanel;

public interface FormComponent {
	JPanel getFrame();
	Object getVal();
	void setVal(Object val);
	void setLabel(String label);
}
