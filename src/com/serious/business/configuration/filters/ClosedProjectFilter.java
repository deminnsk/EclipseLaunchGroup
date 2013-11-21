package com.serious.business.configuration.filters;

import org.eclipse.debug.core.ILaunchConfiguration;

public class ClosedProjectFilter implements IConfigurationFilter {

	ILaunchConfiguration configuration;
	
	@SuppressWarnings("restriction")
	@Override
	public boolean doFilter(ILaunchConfiguration configuration) {
		this.configuration = configuration;
		
		org.eclipse.debug.internal.ui.launchConfigurations.ClosedProjectFilter filter = 
				new org.eclipse.debug.internal.ui.launchConfigurations.ClosedProjectFilter();
		
		return ! filter.select(null, null, configuration);
	}

	@Override
	public String getErrorMessage() {
		return "Project for configuration closed or configuration do not exist: " + configuration.getName();
	}

}
