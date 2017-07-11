package org.webservices.restapi.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.constraints.Size;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.webservices.restapi.messenger.model.Message;
import org.webservices.restapi.messenger.resources.beans.MessageFilterBean;
import org.webservices.restapi.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService=new MessageService();
	
	//Accessing parameters using query Param
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int yr,@QueryParam("start") int st,@QueryParam("size") int sz){
		if(yr>0)
			return messageService.getAllMesssagesForYear(yr);
		if(st>=0 && sz>0)
			return messageService.getAllMessagesPaginated(st, sz);
		return messageService.getAllMessages();
	}*/
	
	//Accessing parameters using Bean Param
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean){
		if(messageFilterBean.getYr()>0)
			return messageService.getAllMesssagesForYear(messageFilterBean.getYr());
		if(messageFilterBean.getSt()>=0 && messageFilterBean.getSz()>0)
			return messageService.getAllMessagesPaginated(messageFilterBean.getSt(),messageFilterBean.getSz());
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long msgid){
		return messageService.getMessage(msgid);
	}
	
	/*@POST 		--Here using Message Object only to form the json..Not using Response object
	public Message addMessage(Message message){
		return messageService.addMessage(message);
	}*/
	
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo){
		Message newMessage= messageService.addMessage(message);
		String newId=Long.toString(newMessage.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
						.entity(newMessage)
						.build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long msgid,Message message){
		message.setId(msgid);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	
	
	/*@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){						//See when return type is string use media type as Text_PLAIN
		return "Post Working Dude!!!" ;
	}*/
	
}
