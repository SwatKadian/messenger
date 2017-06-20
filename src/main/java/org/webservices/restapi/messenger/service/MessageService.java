package org.webservices.restapi.messenger.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webservices.restapi.messenger.database.DatabaseClass;
import org.webservices.restapi.messenger.model.Message;
import org.webservices.restapi.messenger.model.Profile;

public class MessageService {
	
	private Map<Long,Message> messages=DatabaseClass.getMessages();
	private static Map<Long,Profile> profiles=DatabaseClass.getProfiles();
	
	public MessageService(){
		messages.put(1L,new Message(1L,"Hello Nina","Swat"));
		messages.put(2L,new Message(2L,"Hello Superman","Swat"));
	}

	public List<Message> getAllMessages(){	
		//Collection<Message> col=messages.values();
		//ArrayList<Message> al=new ArrayList<>(col);
		return  new ArrayList<Message>(messages.values()); //this code is equivalent to above two lines of code.
														//Arraylist has a constructor that takes collection as a parameter
	}
	
	public Message getMessage(Long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		int sz=messages.size()+1;
		message.setId(sz);
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId()<=0)
			return null;
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
