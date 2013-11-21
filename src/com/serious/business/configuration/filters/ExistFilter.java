package com.serious.business.configuration.filters;

import org.eclipse.debug.core.ILaunchConfiguration;

public class ExistFilter implements IConfigurationFilter {

	ILaunchConfiguration configuration;
	
	@Override
	public boolean doFilter(ILaunchConfiguration configuration) {
		this.configuration = configuration;
		return ! configuration.exists();
	}

	@Override
	public String getErrorMessage() {
		return "Configuration do not exists: " + configuration.getName();
	}

}
