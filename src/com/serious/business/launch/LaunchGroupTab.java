package com.serious.business.launch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.serious.business.common.Constants;
import com.serious.business.common.Messages;
import com.serious.business.common.Pair;
import com.serious.business.launchwizard.LaunchConfigurationWizard;

public class LaunchGroupTab extends AbstractLaunchConfigurationTab {

	private static final String tabName = "Launch Group";

	private ILaunchConfiguration currentConfiguration;
	
	private CheckboxTableViewer childLaunchersViewer;
	
	private Button upButton;
	private Button downButton;
	private Button editButton;
	private Button addButton;
	private Button removeButton;
	
	private class GroupTabListener extends SelectionAdapter implements ModifyListener {

		@Override
		public void modifyText(ModifyEvent e) {
			updateLaunchConfigurationDialog();
		}
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			Object source = e.getSource();
			
			if (source == childLaunchersViewer.getTable() || source == childLaunchersViewer) {
				updateButtonsState();
			} else if (source == upButton) {
				handleUpButton();
			} else if (source == downButton) {
//				handleParametersEditButtonSelected();
			} else if (source == editButton) {
//				handleParametersRemoveButtonSelected();
			} else if (source == addButton) {
				handleAddButton();
			} else if (source == removeButton) {
//				handleParametersRemoveButtonSelected();
			}
			
		}
		
	}
	
	@Override
	public void createControl(Composite parent) {
		
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),	IJavaDebugHelpContextIds.LAUNCH_CONFIGURATION_DIALOG_APPLET_PARAMETERS_TAB);
		GridLayout topLayout = new GridLayout();
		comp.setLayout(topLayout);		
		GridData gd;
		
		Composite widthHeightNameComp = new Composite(comp, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		widthHeightNameComp.setLayoutData(gd);
		GridLayout widthHeightNameLayout = new GridLayout();
		widthHeightNameLayout.marginHeight = 0;
		widthHeightNameLayout.marginWidth = 0;
		widthHeightNameLayout.numColumns = 4;
		widthHeightNameComp.setLayout(widthHeightNameLayout);
		
		
		Composite tableComp = new Composite(comp, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		tableComp.setLayoutData(gd);
		GridLayout parametersLayout = new GridLayout();
		parametersLayout.numColumns = 2;
		parametersLayout.marginHeight = 0;
		parametersLayout.marginWidth = 0;
		tableComp.setLayout(parametersLayout);

		childLaunchersViewer = CheckboxTableViewer.newCheckList(tableComp, SWT.NONE);
		
		Table configurationsTable = childLaunchersViewer.getTable();
		gd = new GridData(GridData.FILL_BOTH);
		configurationsTable.setLayoutData(gd);		
		TableColumn column1 = new TableColumn(configurationsTable, SWT.NONE);
		column1.setText(Messages.message_child_name);
		TableColumn column2 = new TableColumn(configurationsTable, SWT.NONE);
		column2.setText("text");
		TableLayout tableLayout = new TableLayout();
		configurationsTable.setLayout(tableLayout);
		tableLayout.addColumnData(new ColumnWeightData(100));
		tableLayout.addColumnData(new ColumnWeightData(100));
		configurationsTable.setHeaderVisible(true);
		configurationsTable.setLinesVisible(true);
		
		Composite envButtonComp = new Composite(tableComp, SWT.NONE);
		GridLayout envButtonLayout = new GridLayout();
		envButtonLayout.marginHeight = 0;
		envButtonLayout.marginWidth = 0;
		envButtonComp.setLayout(envButtonLayout);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
		envButtonComp.setLayoutData(gd);
		
		GroupTabListener listener = new GroupTabListener();
		
		upButton = createPushButton(envButtonComp , Messages.message_button_up, null); 
		upButton.addSelectionListener(listener);
		
		downButton = createPushButton(envButtonComp, Messages.message_button_down, null); 
		downButton.addSelectionListener(listener);
				
		editButton = createPushButton(envButtonComp, Messages.message_button_edit, null); 
		editButton.addSelectionListener(listener);
				
		addButton = createPushButton(envButtonComp, Messages.message_button_add, null);
		addButton.addSelectionListener(listener);
		
		removeButton = createPushButton(envButtonComp, Messages.message_button_remove, null);
		removeButton.addSelectionListener(listener);
		
		Dialog.applyDialogFont(parent);
		updateButtonsState();
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		
		this.currentConfiguration = configuration;
		
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return tabName;
	}

	private void updateButtonsState(){
		
	}
	
	private void handleUpButton(){
		
	}
	
	private void handleAddButton(){
		
		LaunchConfigurationWizard wizard = new LaunchConfigurationWizard();
		wizard.addHandler(new ConfigurationSelectedHandler() {
			
			@Override
			public void onResult(ILaunchConfiguration configuration) {
				addChildConfiguration(configuration);
			}
		});
		
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.open();
	}
	
	@SuppressWarnings("unchecked")
	private void addChildConfiguration(ILaunchConfiguration configuration) {
		
		GroupLaunchConfigurationObject object = null;
		
		
		//currentConfiguration.getWorkingCopy().
		try {
			
			ILaunchConfigurationWorkingCopy workingCopy = currentConfiguration.getWorkingCopy();
			
			if (currentConfiguration != null 
					&& workingCopy.hasAttribute(Constants.GROUP_CONFIGURATION_KEY)) {
				
				object = (GroupLaunchConfigurationObject) 
						workingCopy.getAttributes().get(Constants.GROUP_CONFIGURATION_KEY);
				
				
			} else {
				object = new GroupLaunchConfigurationObject();
				//workingCopy.setAttribute(Constants.GROUP_CONFIGURATION_KEY, Constants.GROUP_CONFIGURATION_KEY);
				workingCopy.getAttributes().put(object, Constants.GROUP_CONFIGURATION_KEY);
				//currentConfiguration.getWorkingCopy().doSave();
			}
			
			List<Pair<ILaunchConfiguration, Map<String, Object>>> childs = object.getChilds();
			
			if (childs == null) {
				childs = new ArrayList<Pair<ILaunchConfiguration,Map<String,Object>>>();
			}
			
			Pair<ILaunchConfiguration, Map<String, Object>> pair = 
					new Pair<ILaunchConfiguration, Map<String,Object>>(configuration, new HashMap<String, Object>());
			
			childs.add(pair);
			currentConfiguration = workingCopy.doSave();
			System.out.println("Childs count: " + childs.size());
			updateLaunchConfigurationDialog();
			
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}
	
	public interface ConfigurationSelectedHandler{
		void onResult(ILaunchConfiguration configuration);
	}

}
