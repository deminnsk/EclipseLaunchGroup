package com.serious.business.launch;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	private static final String BUNDLE_NAME = "com.serious.business.messages";

	static {
		reloadMessages();
	}

	public static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String message_child_name;
	
	public static String message_button_up;
	public static String message_button_down;
	public static String message_button_edit;
	public static String message_button_add;
	public static String message_button_remove;
	
	
	
}
