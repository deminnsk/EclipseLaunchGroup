package com.serious.business.configuration.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.serious.business.configuration.filters.IConfigurationFilter;
import com.serious.business.configuration.filters.exception.FilterException;

public interface IGroupConfigurationRunner {

	public void addFilter(IConfigurationFilter filter);
	public void runConfiguration(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) 
			throws CoreException, FilterException;
	
}
