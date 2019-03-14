package scicafe.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import scicafe.api.error.RestException;
import scicafe.model.Event;
import scicafe.model.User;
import scicafe.model.dao.EventDao;
import scicafe.model.dao.UserDao;

@RestController
public class EventController {
	
		@Autowired
	    private EventDao eventDao;
		@Autowired
		private UserDao userDao;
	 
	    //Create Event
	    @RequestMapping(value = "/events/submitter/{userid}", method = RequestMethod.POST)
	    public Event createEvent(@PathVariable Long userid, @RequestBody Event e) {
			
	    	if(e.getName() == null || e.getLocation() == null || e.getDescription() == null ||  e.getStarttime() == null ) {
	    		
	    		 throw new RestException(400, "Missing Fields");
			
	    	}
	    	else {
	    		User u = getUser(userid);
	    		e.setSubmitter(u);
	    		if(u.isEventOrganiser()) {
	    			e.setApprovedby(u);
	    			e.setApproved(true);
	    		}
	    		return eventDao.createEvent(e); 	
	    	}
		}
	    
	    //Approve or Reject Event
	   @RequestMapping(value = "/events/{id}/approvedby/{userid}", method = RequestMethod.PATCH)
		public String approverejectEvent(@PathVariable Long id, @PathVariable Long userid) {
			
	    	User u = getUser(userid);
			if(eventDao.approverejectEvent(id,u)) {
				
				return "Event Approved!";
			}
			else {
				throw new RestException(403, "Not sufficient permission to approve project");
			}
		}

	    //Add an Attendee to Event
	    @RequestMapping(value = "/events/{id}/addattendee/{userid}", method = RequestMethod.POST)
		public Event addAttendee(@PathVariable Long id, @PathVariable Long userid) {
			
	    	User u = getUser(userid);
	    	if(getEvent(id) == null || u == null) {
	    		throw new RestException(404, "Resource not found");
	    	}
	    	else if(getEvent(id).getAttendees().contains(u)) {
	    		throw new RestException(400, "Attendee Exists!");
	    	}
	    	else {
	    		
	    		return eventDao.addAttendee(id, u);
	    		
	    	}
			
		}
	    
	    //Get all Attendees
	    @RequestMapping(value = "/events/{id}/attendees", method = RequestMethod.GET)
	    public Set<User> getAllAttendees(@PathVariable Long id){
	    	
	    	if(getEvent(id) == null) {
	    		throw new RestException(404, "Resource not found");
	    	}
	    	else return eventDao.getAllAttendees(id);
	    }
	    
	    public User getUser(Long userid) {
	    	
	    	return userDao.getUser(userid);
	    }
	    
	    
	    public Event getEvent(Long id) {
	    	
	    	return eventDao.getEvent(id);
	    }
	    @RequestMapping(value = "/events", method = RequestMethod.GET)
	    public List<Event> getEvents()
	    {
	        return eventDao.getAllEvents();
	    }
}
