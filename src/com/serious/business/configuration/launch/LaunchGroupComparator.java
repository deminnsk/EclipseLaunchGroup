package com.serious.business.configuration.launch;

import java.util.Comparator;
import java.util.List;

public class LaunchGroupComparator<Object> implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		
		List<String> first = (List<String>) arg0;
		List<String> second = (List<String>) arg1;
		
		if (first.size() != second.size()) {
			return 1;
		}
		
		for (int i = 0; i < first.size(); i++) {
			
			if (!first.get(i).equals(second.get(i))) {
				return 1;
			}
			
		}
		
		
		return 0;
	}

}
