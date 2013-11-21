package com.serious.business.configuration.filters;

import org.eclipse.debug.core.ILaunchConfiguration;

public interface IConfigurationFilter {
	public boolean doFilter(ILaunchConfiguration configuration);
	public String getErrorMessage();
}
