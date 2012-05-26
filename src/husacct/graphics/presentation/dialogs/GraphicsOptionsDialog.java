package husacct.graphics.presentation.dialogs;

import husacct.ServiceProvider;
import husacct.control.IControlService;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

public class GraphicsOptionsDialog extends JDialog {
	private static final long serialVersionUID = 4794939901459687332L;

	protected IControlService controlService;
	protected Logger logger = Logger.getLogger(GraphicsOptionsDialog.class);
	
	private JPanel mainPanel, settingsPanel, actionsPanel, optionsPanel, zoomPanel;
	
	private int menuItemMaxHeight = 45;

	private JButton zoomInButton, zoomOutButton, refreshButton, exportToImageButton;
	private JCheckBox showDependenciesOptionMenu, showViolationsOptionMenu, contextUpdatesOptionMenu;
	private JComboBox layoutStrategyOptions;
	private JSlider zoomSlider;
	
	private int width, height;
	
	public GraphicsOptionsDialog(){
		super();
		width = 500;
		height = 200;
		
		controlService = ServiceProvider.getInstance().getControlService();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		add(mainPanel);
		initGUI();
	}
	
	public void showDialog(){
		setResizable(false);
		setSize(width, height);
		setModal(true);
		setVisible(true);
		ServiceProvider.getInstance().getControlService().centerDialog(this);
	}
	
	public void initGUI() {
		actionsPanel = new JPanel();
			zoomInButton = new JButton();
			actionsPanel.add(zoomInButton);
			
			zoomOutButton = new JButton();
			actionsPanel.add(zoomOutButton);
			
			refreshButton = new JButton();
			actionsPanel.add(refreshButton);
			
			exportToImageButton = new JButton();
			actionsPanel.add(exportToImageButton);
			
			mainPanel.add(actionsPanel);
		
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(3,1));
		
			showDependenciesOptionMenu = new JCheckBox();
			showDependenciesOptionMenu.setSize(40, menuItemMaxHeight);
			optionsPanel.add(showDependenciesOptionMenu);
			
			showViolationsOptionMenu = new JCheckBox();
			showViolationsOptionMenu.setSize(40, menuItemMaxHeight);
			optionsPanel.add(showViolationsOptionMenu);
			
			contextUpdatesOptionMenu = new JCheckBox();
			contextUpdatesOptionMenu.setSize(40, menuItemMaxHeight);
			optionsPanel.add(contextUpdatesOptionMenu);
		
			mainPanel.add(optionsPanel);
		
		settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(1,2));
			settingsPanel.add(new JLabel("Layout strategy"));
			layoutStrategyOptions = new JComboBox();
			settingsPanel.add(layoutStrategyOptions);
			
			mainPanel.add(settingsPanel);
		
		zoomPanel = new JPanel();
		zoomPanel.setLayout(new GridLayout(1,2));
			zoomPanel.add(new JLabel("Zoom"));
			zoomSlider = new JSlider(25, 175, 100);
			zoomSlider.setSize(50, width);
			zoomPanel.add(zoomSlider);
			
			mainPanel.add(zoomPanel);
	}
	
	public void setLocale(HashMap<String, String> menuBarLocale) {
		try {
			zoomInButton.setText(menuBarLocale.get("ZoomIn"));
			zoomOutButton.setText(menuBarLocale.get("ZoomOut"));
			refreshButton.setText(menuBarLocale.get("Refresh"));
			exportToImageButton.setText(menuBarLocale.get("ExportToImage"));
			showDependenciesOptionMenu.setText(menuBarLocale.get("ShowDependencies"));
			showViolationsOptionMenu.setText(menuBarLocale.get("ShowViolations"));
			contextUpdatesOptionMenu.setText(menuBarLocale.get("LineContextUpdates"));
		} catch (NullPointerException e) {
			logger.warn("Locale is not set properly.");
		}
	}
	
	public void setIcons(HashMap<String, String> icons) {
		try{
			ImageIcon icon = new ImageIcon(getClass().getResource(icons.get("zoomIn")));
			zoomInButton.setIcon(icon);
			icon = new ImageIcon(getClass().getResource(icons.get("zoomOut")));
			zoomOutButton.setIcon(icon);
			icon = new ImageIcon(getClass().getResource(icons.get("refresh")));
			refreshButton.setIcon(icon);
			icon = new ImageIcon(getClass().getResource(icons.get("save")));
			exportToImageButton.setIcon(icon);
		} catch (NullPointerException e) {
			logger.warn("Icons are not set properly.");
		}
	}
	
	public void setZoomInAction(ActionListener listener) {
		zoomInButton.addActionListener(listener);
	}

	public void setZoomOutAction(ActionListener listener) {
		zoomOutButton.addActionListener(listener);
	}

	public void setRefreshAction(ActionListener listener) {
		refreshButton.addActionListener(listener);
	}

	public void setExportToImageAction(ActionListener listener) {
		exportToImageButton.addActionListener(listener);
	}

	public void setToggleContextUpdatesAction(ActionListener listener) {
		contextUpdatesOptionMenu.addActionListener(listener);
	}
	
	public void setLayoutStrategyAction(ActionListener listener) {
		layoutStrategyOptions.addActionListener(listener);
	}

	public void setContextUpdatesToggle(boolean setting) {
		contextUpdatesOptionMenu.setSelected(setting);
	}

	public void setToggleDependenciesAction(ActionListener listener) {
		showDependenciesOptionMenu.addActionListener(listener);
	}

	public void setToggleViolationsAction(ActionListener listener) {
		showViolationsOptionMenu.addActionListener(listener);
	}

	public void setZoomChangeListener(ChangeListener listener) {
		zoomSlider.addChangeListener(listener);
	}

	public void setLayoutStrategyItems(String[] layoutStrategyItems) {
		layoutStrategyOptions.removeAllItems();
		for (String item : layoutStrategyItems) {
			layoutStrategyOptions.addItem(item);
		}
	}

	public void setSelectedLayoutStrategyItem(String item) {
		layoutStrategyOptions.setSelectedItem(item);
	}
	
	public String getSelectedLayoutStrategyItem() {
		return (String) layoutStrategyOptions.getSelectedItem();
	}
	
	public void setDependencyToggle(boolean setting) {
		if(showDependenciesOptionMenu.isSelected()!=setting){
			showDependenciesOptionMenu.setSelected(setting);
		}
	}
	
	public void setViolationToggle(boolean setting) {
		if(showViolationsOptionMenu.isSelected()!=setting){
			showViolationsOptionMenu.setSelected(setting);
		}
	}
	
}
