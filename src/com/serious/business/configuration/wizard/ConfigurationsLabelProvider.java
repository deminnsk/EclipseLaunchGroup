package com.serious.business.configuration.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ConfigurationsLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {

		if (element instanceof ILaunchConfigurationType) {
			ILaunchConfigurationType type = (ILaunchConfigurationType)element;
			return type.getName();
		} else if (element instanceof ILaunchConfiguration) {
			ILaunchConfiguration configuration = (ILaunchConfiguration) element;
			return configuration.getName();
		} else {
			return null;
		}
		
	}

	@Override
	public Image getImage(Object element) {

		if (element instanceof ILaunchConfigurationType) {
			
			ILaunchConfigurationType type = (ILaunchConfigurationType) element;
			return DebugUITools.getImage(type.getIdentifier());
		} else if (element instanceof ILaunchConfiguration) {
			
			ILaunchConfiguration configuration = (ILaunchConfiguration) element;
			try {
				return DebugUITools.getImage(configuration.getType()
						.getIdentifier());
			} catch (CoreException e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			return null;
		}
				
	}
	
}
