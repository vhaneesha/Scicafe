package scicafe.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import scicafe.api.error.RestException;
import scicafe.model.Program;
import scicafe.model.dao.ProgramDao;

@RestController
public class ProgramController {

	@Autowired
	private ProgramDao programDao;
	
	//Get All Programs
	 @RequestMapping(value = "/programs", method = RequestMethod.GET) 
	 public List<Program> getAllPrograms(){
		 return programDao.getAllPrograms();
		 
	 }
	 
	 //Get a Program
	 @RequestMapping(value = "/programs/{id}", method = RequestMethod.GET) 
	 public Program getProgram(@PathVariable Long id) {
	
		 if(programDao.getProgram(id) == null) {
			 throw new RestException(404, "The resource was not found");
		 }
		 else {
			 return programDao.getProgram(id);
		 }
	 }
	 
	 //Create Program
	 @RequestMapping(value = "/programs", method = RequestMethod.POST) 
	 public String createProgram(@RequestBody Program p) {
		 
		 if(p.getDescription() == null || p.getFullname() == null || p.getName() == null) {
			
			 throw new RestException(400, "Missing fields");
		 }
		 else  {
			 programDao.createProgram(p);
			 return "Program Created!";
		 }
		
	 }
	 
	 //Edit Program
	 @RequestMapping(value = "/programs", method = RequestMethod.PUT) 
	 public Program editProgram(@RequestBody Program p) {
		 
		 if(programDao.getProgram(p.getId()) == null) {
			 throw new RestException(404, "The resource was not found");
		 }
		 else
		 if(p.getDescription() == null || p.getFullname() == null || p.getName() == null) {
			 throw new RestException(400, "Missing fields");
		 }
		 else return programDao.editProgram(p);
	 }
	
	 //DeleteProgram
	 @RequestMapping(value = "/programs/{id}", method = RequestMethod.DELETE) 
	 public String deleteProgram(@PathVariable Long id) {
		
		 if(programDao.getProgram(id) == null) {
			 throw new RestException(404, "The resource was not found");
		 }
		 else {
		 Program p = getProgram(id);
		 programDao.deleteProgram(p);
		 	return "Program Deleted";
		 }
		 
	 }
	 
}
