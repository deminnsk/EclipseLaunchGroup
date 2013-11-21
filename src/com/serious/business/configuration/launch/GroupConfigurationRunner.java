package com.serious.business.configuration.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.serious.business.configuration.filters.IConfigurationFilter;
import com.serious.business.configuration.filters.exception.FilterException;
import com.serious.business.configuration.model.ChildLaunchConfiguration;
import com.serious.business.configuration.model.GroupLaunchConfiguration;

public class GroupConfigurationRunner implements IGroupConfigurationRunner {

	private List<IConfigurationFilter> filters;
	
	@Override
	public void addFilter(IConfigurationFilter filter) {
		
		if (filters == null) {
			filters = new ArrayList<IConfigurationFilter>();
		}
		
		filters.add(filter);

	}

	@Override
	public void runConfiguration(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) 
			throws CoreException, FilterException {
		
		GroupLaunchConfiguration configuration2 = GroupLaunchConfiguration.createGroupConfiguration(configuration);
		
		//checking filters 
		for (ChildLaunchConfiguration childLaunchConfiguration : configuration2.getChilds()) {
			for (IConfigurationFilter filter : filters) {
				if (filter.doFilter(childLaunchConfiguration.toLaunchConfiguration())) {
					throw new FilterException(filter.getErrorMessage());
				}
			}
		}
		
		//then launch configurations
		for (ChildLaunchConfiguration childLaunchConfiguration : configuration2.getChilds()) {
			
			if (childLaunchConfiguration.isActivated()) {
				childLaunchConfiguration.toLaunchConfiguration().launch(mode, monitor);
			}
			
		}
	}

}
