package com.serious.business.launch;

import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.ILaunchConfiguration;

import com.serious.business.common.Pair;

public class GroupLaunchConfigurationObject {

	private List<Pair<ILaunchConfiguration, Map<String, Object>>> childs;
	private boolean activated;
	
	public GroupLaunchConfigurationObject() {
		super();
	}
	
	public List<Pair<ILaunchConfiguration, Map<String, Object>>> getChilds() {
		return childs;
	}
	public void setChilds(List<Pair<ILaunchConfiguration, Map<String, Object>>> childs) {
		this.childs = childs;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	
}
