package com.serious.business.configuration.launch;

import org.eclipse.debug.core.ILaunchConfiguration;

import com.serious.business.configuration.filters.IConfigurationFilter;

public interface IGroupConfigurationRunner {

	public void addFilter(IConfigurationFilter filter);
	public void runConfiguration(ILaunchConfiguration configuration);
	
}
