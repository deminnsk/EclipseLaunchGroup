package com.serious.business.launch;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.serious.business.common.Constants;

public class TableViewerLabelProvider implements ITableLabelProvider {
	
	private ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		Map<String, String> config = (Map<String, String>) element;
		String launchMemento = config.get(Constants.MEMENTO_KEY);
		
		ILaunchConfiguration configuration = null;
		try {
			configuration = manager.getLaunchConfiguration(launchMemento);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
