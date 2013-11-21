package com.serious.business.configuration.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import com.serious.business.configuration.filters.CycleFilter;
import com.serious.business.configuration.filters.exception.FilterException;

public class GroupLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
	
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		IGroupConfigurationRunner runner = new GroupConfigurationRunner();
		
//		GroupLaunchConfiguration groupLaunchConfiguration = GroupLaunchConfiguration.createGroupConfiguration(configuration);
		
		runner.addFilter(new CycleFilter());
		
		try {
			runner.runConfiguration(configuration, mode, monitor);
		} catch (FilterException e) {
			e.printStackTrace();
		}

	}

	
	
	
}
