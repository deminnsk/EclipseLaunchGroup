package com.serious.business.configuration.model;

import java.util.UUID;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;

public class ChildLaunchConfiguration {

	private String memento;
	private boolean activated;
	private String identifier;
	
	public ChildLaunchConfiguration(){
		super();
	}
	
	public static ChildLaunchConfiguration createConfiguration(ILaunchConfiguration configuration) throws CoreException {
		return new ChildLaunchConfiguration(configuration.getMemento(), true, 
				UUID.randomUUID().toString());
	}
	
	public ChildLaunchConfiguration(String memento, boolean activated,
			String identifier) {
		super();
		this.memento = memento;
		this.activated = activated;
		this.identifier = identifier;
	}

	public String getMemento() {
		return memento;
	}
	public void setMemento(String memento) {
		this.memento = memento;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public ILaunchConfiguration toLaunchConfiguration() throws CoreException{
		return DebugPlugin.getDefault().getLaunchManager().getLaunchConfiguration(this.memento);
	}
	
}
