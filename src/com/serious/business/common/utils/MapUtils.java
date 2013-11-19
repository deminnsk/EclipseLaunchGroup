package com.serious.business.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {

	private static final String DELIMITER = "|";
	private static final String KEY_VALUE_SEPARATOR = ":"; 
	
	public static String stringFromMap(Map<String, String> map){
		
		StringBuilder builder = new StringBuilder();
		
		for (Entry<String, String> entry : map.entrySet()) {
			if (builder.length() != 0) {
				builder.append(DELIMITER);
			}
			builder.append(entry.getKey()).append(KEY_VALUE_SEPARATOR).append(entry.getValue());
		}
		
		return builder.toString();
	}
	
	public static Map<String, String> mapFromString(String string) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		for(final String entry : string.split("\\"+DELIMITER)) {
		    final String[] parts = entry.split(KEY_VALUE_SEPARATOR);
		    assert(parts.length == 2) : "Invalid entry: " + entry;
		    map.put(parts[0], parts[1]);
		}
		
		return map;
		
	}
	
}
