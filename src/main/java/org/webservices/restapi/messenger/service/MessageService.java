package org.webservices.restapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webservices.restapi.messenger.database.DatabaseClass;
import org.webservices.restapi.messenger.model.Message;
import org.webservices.restapi.messenger.model.Profile;

public class MessageService {
	
	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L,new Message(1L,"Hello Nina","Swat"));
		messages.put(2L,new Message(2L,"Hello Superman__","Swat"));
		messages.put(3L,new Message(3L,"Hello WonderWoman","Swat"));
		messages.put(4L,new Message(4L,"Hello Dude!!","Swat"));
	}

	public List<Message> getAllMessages(){	
		//Collection<Message> col=messages.values();
		//ArrayList<Message> al=new ArrayList<>(col);
		return  new ArrayList<Message>(messages.values()); //this code is equivalent to above two lines of code.
														//Arraylist has a constructor that takes collection as a parameter
	}
	
	public List<Message> getAllMesssagesForYear(int year){
		
		List<Message> msgList=new ArrayList<Message>();
		ArrayList<Message> originalMessageList=new ArrayList<Message>(messages.values());//(ArrayList<Message>) messages.values();
		
		for(Message message:originalMessageList){
			Date dt=message.getCreated();
			Calendar cal=Calendar.getInstance();
			cal.setTime(dt);
			int yr=cal.get(Calendar.YEAR);
			if(yr==year)
				msgList.add(message);
		}
		
		return msgList;
	}
	
	public List<Message> getAllMessagesPaginated(int start,int size){
		
		ArrayList<Message> originalMessageList=new ArrayList<Message>(messages.values());
		if((start+size)>originalMessageList.size())
			return new ArrayList<Message>();							//return an empty list
		return originalMessageList.subList(start, start+size);
		
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
