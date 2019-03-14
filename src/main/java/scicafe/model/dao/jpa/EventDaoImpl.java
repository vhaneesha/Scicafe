package scicafe.model.dao.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scicafe.model.Event;
import scicafe.model.Program;
import scicafe.model.User;
import scicafe.model.dao.EventDao;

@Repository
public class EventDaoImpl implements EventDao {
	@PersistenceContext
	 private EntityManager entityManager;

	@Override
	public List<Event> getAllEvents() 
		{
	        return entityManager.createQuery( "from Event order by id", Event.class ).getResultList();
	    }
		

	@Override
	@Transactional
	public Event createEvent(Event e) {
		
		
		return entityManager.merge(e);
	
	}

	@Override
	@Transactional
	public boolean approverejectEvent(Long id,User user) {
		
		if(user.isEventOrganiser()) {
			Event e = getEvent(id);
			e.setApproved(true);
			e.setApprovedby(user);
			entityManager.merge(e);
		return true;
		}
		else
			return false;
	}

	@Override
	@Transactional
	public Event addAttendee(Long id, User u) {
		
		Event e = getEvent(id);
		//entityManager.detach(e);
		Set<User> users = e.getAttendees();
		users.add(u);
		e.setAttendees(users);
		entityManager.merge(e);
		return e;
		
	}
	@Override
	public Set<User> getAllAttendees(Long id) {
		
		return getEvent(id).getAttendees();
		
	}
	
	public Event getEvent(Long id) {
		return entityManager.find(Event.class, id);
	}


	
	
	 
}
