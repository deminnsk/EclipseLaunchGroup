package com.serious.business.configuration.model;

import org.eclipse.debug.core.ILaunchConfiguration;

public interface IGroupConfigurationSerializer {
	
	public String serialize(GroupLaunchConfiguration configuration);
	public GroupLaunchConfiguration deserialize (ILaunchConfiguration configuration);
	
}
