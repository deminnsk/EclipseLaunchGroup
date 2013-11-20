package com.serious.business.configuration.tabs;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

public class LaunchGroupConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
	
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new LaunchGroupTab(),
				new CommonTab()
		};
		setTabs(tabs);
		
	}

}
