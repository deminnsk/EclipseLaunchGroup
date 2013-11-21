package com.serious.business.configuration.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.serious.business.common.Constants;

public class XMLGroupLaunchConfigurationSerializer implements
		IGroupConfigurationSerializer {

	@Override
	public String serialize(GroupLaunchConfiguration configuration) {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(stream);
		encoder.writeObject(configuration);
		encoder.close();
		
		return stream.toString();
		
	}

	@Override
	public GroupLaunchConfiguration deserialize(
			ILaunchConfiguration configuration) {
		
		String stringConfiguration = null;
		GroupLaunchConfiguration groupLaunchConfiguration = null;
		
		try {
			stringConfiguration = configuration.getAttribute(Constants.GROUP_CONFIGURATION_KEY, new String());
		} catch (CoreException e) {
			stringConfiguration = new String();
			e.printStackTrace();
		}

		if (!stringConfiguration.isEmpty()) {
			
			ByteArrayInputStream stream = new ByteArrayInputStream(stringConfiguration.getBytes());
			XMLDecoder decoder = new XMLDecoder(stream);
			
			groupLaunchConfiguration = (GroupLaunchConfiguration) decoder.readObject();
			decoder.close();
			
			if (groupLaunchConfiguration.getChilds() == null) {
				groupLaunchConfiguration.setChilds(new ArrayList<ChildLaunchConfiguration>());
			}
			
		} else {
			groupLaunchConfiguration = new GroupLaunchConfiguration(configuration);
		}

		return groupLaunchConfiguration;
	}

}
