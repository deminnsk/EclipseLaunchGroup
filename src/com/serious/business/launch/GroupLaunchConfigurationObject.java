package com.serious.business.launch;

import java.util.List;

import org.eclipse.debug.core.ILaunchConfiguration;

public class GroupLaunchConfigurationObject {

	private List<ILaunchConfiguration> childs;
	private boolean activated;
	
	public GroupLaunchConfigurationObject() {
		super();
	}
	
	public List<ILaunchConfiguration> getChilds() {
		return childs;
	}
	public void setChilds(List<ILaunchConfiguration> childs) {
		this.childs = childs;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	
}
