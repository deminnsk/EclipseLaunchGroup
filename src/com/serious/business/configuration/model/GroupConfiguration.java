package com.serious.business.configuration.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.core.LaunchConfiguration;

public class GroupConfiguration extends LaunchConfiguration implements ILaunchConfiguration {

	protected GroupConfiguration(String memento) throws CoreException {
		super(memento);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ILaunchConfigurationWorkingCopy getWorkingCopy()
			throws CoreException {
		// TODO Auto-generated method stub
		return super.getWorkingCopy();
	}
	
}
