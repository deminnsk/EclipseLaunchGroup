package com.serious.business.configuration.tabs;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.serious.business.configuration.model.GroupLaunchConfiguration;

public class ChildConfigurationsContentProvider implements IStructuredContentProvider {

	public ChildConfigurationsContentProvider(ILaunchManager manager) {
		super();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		GroupLaunchConfiguration configuration = (GroupLaunchConfiguration) inputElement;
		return configuration.getChilds().toArray();
	}

}
