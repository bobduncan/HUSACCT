package husacct.define.presentation;

import husacct.control.ILocaleChangeListener;
import husacct.define.presentation.jpanel.DefinitionJPanel;
import husacct.define.presentation.utils.JPanelStatus;

import java.awt.BorderLayout;
import java.util.Locale;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class ApplicationJInternalFrame extends JInternalFrame implements ILocaleChangeListener {

	private static final long serialVersionUID = 6858870868564931134L;
	private JPanel overviewPanel;

	public ApplicationJInternalFrame() {
		super();
		initUi();
	}

	private void initUi() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			this.addDefinitionPanel();
			this.addToolBar();
			
			pack();
			setSize(1000, 700);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void addDefinitionPanel() {
		this.overviewPanel = new JPanel();
		BorderLayout borderLayout = new BorderLayout();
		this.overviewPanel.setLayout(borderLayout);
		this.overviewPanel.add(new DefinitionJPanel());
		this.getContentPane().add(this.overviewPanel, BorderLayout.CENTER);
	}
	
	private void addToolBar() {
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH);
		toolBar.setEnabled(false);
		toolBar.setBorderPainted(false);
		toolBar.add(JPanelStatus.getInstance(""));
	}

	public void setContentView(JPanel jp) {
		this.overviewPanel.removeAll();
		this.overviewPanel.add(jp);
	}

	@Override
	public void update(Locale newLocale) {
	
	}
}
