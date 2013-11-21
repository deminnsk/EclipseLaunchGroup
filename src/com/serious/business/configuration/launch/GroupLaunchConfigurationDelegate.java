package com.serious.business.configuration.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.serious.business.configuration.filters.ClosedProjectFilter;
import com.serious.business.configuration.filters.CycleFilter;
import com.serious.business.configuration.filters.ExistFilter;
import com.serious.business.configuration.filters.exception.FilterException;

public class GroupLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		IGroupConfigurationRunner runner = new GroupConfigurationRunner();
		
		runner.addFilter(new CycleFilter());
		runner.addFilter(new ClosedProjectFilter());
		runner.addFilter(new ExistFilter());
		
		try {
			runner.runConfiguration(configuration, mode, monitor);
		} catch (FilterException e) {
//			e.printStackTrace();
			showError("Error", e.getLocalizedMessage());			
		}

	}

	public void showError(final String title, final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openError(null, title, message);
			}
		});
	}
	
}
