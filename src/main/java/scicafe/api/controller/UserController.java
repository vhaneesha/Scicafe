package scicafe.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import scicafe.api.error.RestException;
import scicafe.model.User;
import scicafe.model.dao.UserDao;

@RestController
public class UserController {
	
	 @Autowired
	 private UserDao userDao;

	    //User Registration
	    @RequestMapping(value="/users", method = RequestMethod.POST)
	    public User addUser(@RequestBody User user) {
	    	
	    	if(user.getFname() == null || user.getLname() == null || user.getOrganisation() == null || user.getUsername() == null
	    		|| user.getPassword() == null ||user.getPosition() == null || user.getEmail() == null) {
	    		
	    		throw new RestException(400,"Missing fields");
	    	}
	    	else return userDao.AddUser(user);
	    }
	    
	    
	  //Get all Users
	    @RequestMapping(value = "/users", method = RequestMethod.GET)
	    public List<User> getAllUsers()
	    {
	        return userDao.getAllUsers();
	    }
}

