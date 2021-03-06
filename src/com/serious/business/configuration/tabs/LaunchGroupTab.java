package com.serious.business.configuration.tabs;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
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
import com.serious.business.configuration.model.ChildLaunchConfiguration;
import com.serious.business.configuration.model.GroupLaunchConfiguration;
import com.serious.business.configuration.wizard.LaunchConfigurationWizard;

public class LaunchGroupTab extends AbstractLaunchConfigurationTab {

	private static final String tabName = "Launch Group";

	private GroupLaunchConfiguration groupLaunchConfiguration;
	private CheckboxTableViewer configurationsTableViewer;
	private int selectionIndex = -1;
	
	private Button upButton;
	private Button downButton;
//	private Button editButton;
	private Button addButton;
	private Button removeButton;
	
	private class GroupTabListener extends SelectionAdapter {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			Object source = e.getSource();
			
			if (source == configurationsTableViewer.getTable() || source == configurationsTableViewer) {
				updateButtonsState();
			} else if (source == upButton) {
				handleUpButton();
			} else if (source == downButton) {
				handleDownButton();
//			} else if (source == editButton) {

			} else if (source == addButton) {
				handleAddButton();
			} else if (source == removeButton) {
				handleRemoveButton();
			}
			
			updateButtonsState();
		}
		
	}
	
	@Override
	public void createControl(Composite parent) {
		
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
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

		configurationsTableViewer = CheckboxTableViewer.newCheckList(tableComp, SWT.NONE);
		
		Table configurationsTable = configurationsTableViewer.getTable();
		gd = new GridData(GridData.FILL_BOTH);
		configurationsTable.setLayoutData(gd);		
		TableColumn column1 = new TableColumn(configurationsTable, SWT.NONE);
		column1.setText(Messages.message_child_name);
		TableColumn column2 = new TableColumn(configurationsTable, SWT.NONE);
		column2.setText("");
		TableLayout tableLayout = new TableLayout();
		configurationsTable.setLayout(tableLayout);
		tableLayout.addColumnData(new ColumnWeightData(100));
		tableLayout.addColumnData(new ColumnWeightData(100));
		configurationsTable.setHeaderVisible(true);
		configurationsTable.setLinesVisible(true);
		
		ChildConfigurationsLabelProvider provider = new ChildConfigurationsLabelProvider();
		
		configurationsTableViewer.setCheckStateProvider(provider);
		configurationsTableViewer.setLabelProvider(provider);
		configurationsTableViewer.setContentProvider(new ChildConfigurationsContentProvider());
		
		configurationsTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionIndex = configurationsTableViewer.getTable().getSelectionIndex();
				updateButtonsState();
			}
		});
		configurationsTableViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				ChildLaunchConfiguration conf = (ChildLaunchConfiguration) event.getElement();
				conf.setActivated(event.getChecked());
				updateLaunchConfigurationDialog();
			}
		});
		
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
				
//		editButton = createPushButton(envButtonComp, Messages.message_button_edit, null); 
//		editButton.addSelectionListener(listener);
				
		addButton = createPushButton(envButtonComp, Messages.message_button_add, null);
		addButton.addSelectionListener(listener);
		
		removeButton = createPushButton(envButtonComp, Messages.message_button_remove, null);
		removeButton.addSelectionListener(listener);
		
		Dialog.applyDialogFont(parent);
		updateButtonsState();
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {		
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		
		this.groupLaunchConfiguration = GroupLaunchConfiguration.createGroupConfiguration(configuration);
		configurationsTableViewer.setInput(this.groupLaunchConfiguration);
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		
		if (groupLaunchConfiguration != null) {
			configuration.setAttribute(Constants.GROUP_CONFIGURATION_KEY, groupLaunchConfiguration.toString());
			
		}
		configuration.setAttribute("eee", (String)null);
		
		configurationsTableViewer.refresh();

	}
	
	@Override
	public String getName() {
		return tabName;
	}

	private void updateButtonsState(){
		
		if (selectionIndex != -1) {
			
			removeButton.setEnabled(true);
			upButton.setEnabled(true);
			downButton.setEnabled(true);
			
			if (selectionIndex == 0) {
				upButton.setEnabled(false);
			} 
			
			if (selectionIndex == this.groupLaunchConfiguration.getChilds().size() - 1) {
				downButton.setEnabled(false);
			}
			
		} else {
			removeButton.setEnabled(false);
			upButton.setEnabled(false);
			downButton.setEnabled(false);
		}
		
	}
	
	private void handleUpButton(){

		if (selectionIndex != -1) {
			Collections.swap(this.groupLaunchConfiguration.getChilds(), selectionIndex, selectionIndex - 1);
			configurationsTableViewer.refresh();
			select(selectionIndex - 1);
		}
		
		updateLaunchConfigurationDialog();
	}
	
	private void handleDownButton(){
		
		if (selectionIndex != -1) {
			Collections.swap(this.groupLaunchConfiguration.getChilds(), selectionIndex, selectionIndex + 1);
			configurationsTableViewer.refresh();
			select(selectionIndex + 1);
		}
		
		updateLaunchConfigurationDialog();
	}
	
	private void handleRemoveButton(){
		
		if (selectionIndex != -1) {
			this.groupLaunchConfiguration.getChilds().remove(selectionIndex);
			configurationsTableViewer.refresh();
			select(selectionIndex);
		
		}
		
		updateLaunchConfigurationDialog();
		
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
	
	private void select(int index) {
		
		int indexForSelect = index;
		
		if (index > this.groupLaunchConfiguration.getChilds().size() - 1) {
			indexForSelect = this.groupLaunchConfiguration.getChilds().size() - 1;
		} else if (index < 0) {
			indexForSelect = 0;
		}
	
		selectionIndex = indexForSelect;
		configurationsTableViewer.getTable().select(indexForSelect);
	}
	
	private void addChildConfiguration(ILaunchConfiguration configuration) {

		try {
			
			ChildLaunchConfiguration childLaunchConfiguration = 
					ChildLaunchConfiguration.createConfiguration(configuration);
			
			this.groupLaunchConfiguration.addChild(childLaunchConfiguration);
			setDirty(true);
			updateLaunchConfigurationDialog();
			
			
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}
	
	public interface ConfigurationSelectedHandler{
		void onResult(ILaunchConfiguration configuration);
	}

}
