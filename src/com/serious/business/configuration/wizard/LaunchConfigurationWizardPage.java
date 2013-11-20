package com.serious.business.configuration.wizard;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class LaunchConfigurationWizardPage extends WizardPage {

	private ILaunchConfiguration launchConfiguration;
	private TreeViewer treeViewer;
	private ILaunchManager manager;
	
	
	protected LaunchConfigurationWizardPage(String pageName) {
		
		super(pageName);
		manager = DebugPlugin.getDefault().getLaunchManager();
		setTitle("Add launch configuration");
	    setDescription("Select existing launch configuration");
	    
	}

	@Override
	public void createControl(Composite arg0) {

	    treeViewer = new TreeViewer(arg0, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	    treeViewer.setContentProvider(new ConfigurationsContentProvider(manager));
	    treeViewer.setLabelProvider(new ConfigurationsLabelProvider());
	    treeViewer.setAutoExpandLevel(0);
	    treeViewer.setInput(manager);
	    
		Tree tree = (Tree) treeViewer.getControl();

		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				
				if (item.getData() instanceof ILaunchConfiguration){
					LaunchConfigurationWizardPage.this.launchConfiguration = (ILaunchConfiguration)item.getData();
					setPageComplete(true);
				}
			}
		});

	    setControl(arg0);
	    setPageComplete(false);
	}

	public ILaunchConfiguration getLaunchConfiguration(){
		return launchConfiguration;
	}
	
}
