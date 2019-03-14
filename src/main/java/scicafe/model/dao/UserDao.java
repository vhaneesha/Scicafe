package scicafe.model.dao;
import java.util.List;

import scicafe.model.Event;
import scicafe.model.Program;
import scicafe.model.User;


public interface UserDao {
	
	    List<User> getAllUsers();
	    User getUser(Long id);
	    User AddUser(User user);
}
