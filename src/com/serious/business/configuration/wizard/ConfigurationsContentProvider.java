package com.serious.business.configuration.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ConfigurationsContentProvider implements ITreeContentProvider {

	private ILaunchManager manager;
	
	public ConfigurationsContentProvider(ILaunchManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}

	@Override
	public Object[] getChildren(Object arg0) {
		
		Object[] results = null;
		
		try {
			results = manager.getLaunchConfigurations((ILaunchConfigurationType)arg0);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}

	@Override
	public Object[] getElements(Object arg0) {
		return manager.getLaunchConfigurationTypes();
	}

	@Override
	public Object getParent(Object arg0) {
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		
		if (arg0 instanceof ILaunchConfigurationType) {
			
			Object[] results = null;
			try {
				results = manager.getLaunchConfigurations((ILaunchConfigurationType)arg0);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return  results != null && results.length > 0;
			
		} else {
			return false;
		}
		
	}

}
