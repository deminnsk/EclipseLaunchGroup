package com.serious.business.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.serious.business.common.Constants;

public class ChildConfigurationsContentProvider implements IStructuredContentProvider {

	private ILaunchManager manager;
	
	
	
	public ChildConfigurationsContentProvider(ILaunchManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		
		ILaunchConfiguration configuration = (ILaunchConfiguration) inputElement;
		List<ILaunchConfiguration> configurations = new ArrayList<ILaunchConfiguration>();
		
		List<String> sources = null;
		try {
			sources = configuration.getAttribute(Constants.GROUP_CONFIGURATION_KEY, 
					new ArrayList<String>());
			
			for (String launchConfigurationMemento : sources) {
				configurations.add(manager.getLaunchConfiguration(launchConfigurationMemento));
			}
			
			return configurations.toArray();
			
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return null;
	}

}
