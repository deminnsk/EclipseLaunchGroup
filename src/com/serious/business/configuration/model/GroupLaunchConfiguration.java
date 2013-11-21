package com.serious.business.configuration.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.debug.core.ILaunchConfiguration;

public class GroupLaunchConfiguration {

	private String name;
	private List<ChildLaunchConfiguration> childs;
	
	@XmlTransient
	private ILaunchConfiguration configuration;
	@XmlTransient
	private static IGroupConfigurationSerializer serializer = new XMLGroupLaunchConfigurationSerializer();
	
	public static GroupLaunchConfiguration createGroupConfiguration(ILaunchConfiguration configuration){
		return serializer.deserialize(configuration);
	}
	
	public GroupLaunchConfiguration() {
		super();
	}

	public GroupLaunchConfiguration(ILaunchConfiguration configuration) {
		super();
		this.configuration = configuration;
		this.name = configuration.getName();
		this.childs = new ArrayList<ChildLaunchConfiguration>();
	}
	
	public void addChild(ChildLaunchConfiguration configuration) {
		this.childs.add(configuration);
	}
	
	public void removeChild(ChildLaunchConfiguration configuration) {
		this.childs.remove(configuration);
	}

	public List<ChildLaunchConfiguration> getChilds() {
		return childs;
	}

	public void setChilds(List<ChildLaunchConfiguration> childs) {
		this.childs = childs;
	}

	@Override
	public String toString(){
		return serializer.serialize(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
