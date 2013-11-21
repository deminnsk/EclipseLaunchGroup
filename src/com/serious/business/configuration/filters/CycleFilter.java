package com.serious.business.configuration.filters;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.serious.business.configuration.model.ChildLaunchConfiguration;
import com.serious.business.configuration.model.GroupLaunchConfiguration;

public class CycleFilter implements IConfigurationFilter {

	private boolean hasCycle = false;
	private String errorMessage;
	
	@Override
	public boolean doFilter(ILaunchConfiguration configuration) {
		
		hasCycle = false;
		checkCycles(configuration, new HashSet<ILaunchConfiguration>());
		return hasCycle;
		
	}

	void checkCycles(ILaunchConfiguration cur, Set<ILaunchConfiguration> graph) {
		
		if (graph.contains(cur)) {
			hasCycle = true;
			errorMessage = "Configuration has cycles: " + cur.getName();
			return;
		}
		
		Set<ILaunchConfiguration> childsGroupConfigurations = new HashSet<ILaunchConfiguration>(graph);
		childsGroupConfigurations.add(cur);
		
		try {
			
			GroupLaunchConfiguration groupLaunchConfiguration = GroupLaunchConfiguration.createGroupConfiguration(cur);
			
			for (ChildLaunchConfiguration child : groupLaunchConfiguration.getChilds()) {
				checkCycles(child.toLaunchConfiguration(), childsGroupConfigurations);
			}

		} catch (CoreException e) {
			// do nothing
		}
			
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
