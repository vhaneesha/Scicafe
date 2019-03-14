package scicafe.model.dao;

import java.util.List;
import java.util.Set;

import scicafe.model.Event;
import scicafe.model.User;


public interface EventDao {

	 List<Event> getAllEvents();
	 Event getEvent(Long id);
	 Event createEvent(Event e);
	 boolean approverejectEvent(Long id, User user);
	 Event addAttendee(Long id, User u);
	 Set<User> getAllAttendees(Long id);
	 
}
