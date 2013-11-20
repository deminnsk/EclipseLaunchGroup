package com.serious.business.configuration.launch;

import java.beans.XMLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import com.serious.business.common.Constants;

public class GroupLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
	
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
//		XMLEncoder encoder = new XM
		// TODO Auto-generated method stub
//		configuration.launch(mode, monitor);
//		.GroupLaunchConfigurationDelegate
//		DebugPlugin.getDefault().getLaunchManager().isExistingLaunchConfigurationName(name)
//		IJavaLaunchConfigurationConstants.ATTR_ALLOW_TERMINATE;
		
//		Launch
//		hasCycles(configuration, new HashSet<ILaunchConfiguration>());
	}

	
	void hasCycles(ILaunchConfiguration cur, Set<ILaunchConfiguration> graph) {
		
		if (graph.contains(cur)) {
			System.out.println("has cycles");
			return;
		}
		
		Set<ILaunchConfiguration> childsGroupConfigurations = new HashSet<ILaunchConfiguration>(graph);
		childsGroupConfigurations.add(cur);
		
		try {
			List<String> childs = 
					cur.getAttribute(Constants.GROUP_CONFIGURATION_KEY, 
							new ArrayList<String>());
			

			
			for (String childString : childs) {
				ILaunchConfiguration configuration = mgr.getLaunchConfiguration(childString);
				hasCycles(configuration, childsGroupConfigurations);
			}

		} catch (CoreException e) {
		}
		
		if (graph.size() == 0) {
			System.out.println("No cycles");
		} 
			
	}
	
	
	
	
}
