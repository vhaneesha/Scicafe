package scicafe.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scicafe.model.Program;
import scicafe.model.dao.ProgramDao;

@Repository
public class ProgramDaoImpl implements ProgramDao{
		
	@PersistenceContext
	 private EntityManager entityManager;
	 
	 
	@Override
	 public List<Program> getAllPrograms(){
		 return entityManager.createQuery( "from Program order by id", Program.class ).getResultList();
		 
	 }
	 
	@Override
	 public Program getProgram(Long id) {
		 return entityManager.find(Program.class,id);
	 }
	 
	@Override
	@Transactional
	 public void createProgram(Program p) {
		
		  entityManager.merge(p);
	 }
	 
	 @Override
	 @Transactional
	 public Program editProgram(Program p) {
		 
		 entityManager.detach(getProgram(p.getId()));
		 return entityManager.merge(p);
	 }
	 
	 @Override
	 @Transactional
	 public void deleteProgram(Program p) {
		 entityManager.remove(p);
	 }
	 
	 
}
