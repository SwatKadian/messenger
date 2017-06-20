package org.webservices.restapi.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.webservices.restapi.messenger.model.Message;
import org.webservices.restapi.messenger.model.Profile;

public class DatabaseClass {

	private static Map<Long,Message> messages=new HashMap<>();
	private static Map<Long,Profile> profiles=new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
	
	
}
