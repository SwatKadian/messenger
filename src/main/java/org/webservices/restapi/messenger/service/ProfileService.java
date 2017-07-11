package org.webservices.restapi.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.webservices.restapi.messenger.database.DatabaseClass;
import org.webservices.restapi.messenger.model.Profile;

public class ProfileService {

	private Map<String,Profile> profiles=DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("nina_dob",new Profile(1l, "nina_dob", "Nina", "Dobrev"));
		profiles.put("chris_bale",new Profile(2l, "chris_bale", "Christian", "Bale"));
	}
	
	public List<Profile> getallProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty())
			return null;
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile deleteProfile(String profileName){
		return profiles.remove(profileName);
	}
}
