package com.serious.business.launchwizard;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.wizard.Wizard;

import com.serious.business.launch.LaunchGroupTab.ConfigurationSelectedHandler;

public class LaunchConfigurationWizard extends Wizard {

	private ConfigurationSelectedHandler handler;
	
	private LaunchConfigurationWizardPage launchConfigurationWizardPage;

	public void addHandler(ConfigurationSelectedHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void addPages() {
		launchConfigurationWizardPage = new LaunchConfigurationWizardPage("select");
		addPage(launchConfigurationWizardPage);
	}
	
	@Override
	public boolean performFinish() {
		
		ILaunchConfiguration configuration = launchConfigurationWizardPage.getLaunchConfiguration();
		System.out.println("Selected configuration: " + configuration.getName());
		
		if (handler != null) {
			handler.onResult(configuration);
		}
		
		return true;
	}

}
