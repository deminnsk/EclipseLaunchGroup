package com.serious.business.configuration.tabs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.serious.business.configuration.model.ChildLaunchConfiguration;

public class TableViewerLabelProvider implements ITableLabelProvider {
	
	private ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
	
	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		ChildLaunchConfiguration childLaunchConfiguration = (ChildLaunchConfiguration) element;
		
		ILaunchConfiguration configuration = null;
		try {
			configuration = manager.getLaunchConfiguration(childLaunchConfiguration.getMemento());
		} catch (CoreException e1) {
			e1.printStackTrace();
			return null;
		}
		
		if (columnIndex == 0) {
			
			return configuration.getName();
			
		} else if (columnIndex == 1) {
			try {
				return configuration.getCategory();
			} catch (CoreException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}

}
