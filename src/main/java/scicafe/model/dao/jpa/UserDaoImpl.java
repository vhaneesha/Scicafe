package scicafe.model.dao.jpa;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scicafe.model.User;
import scicafe.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class ).getResultList();
    }
    
    @Override
    @Transactional
    public User AddUser(User user) {
    	
    	return entityManager.merge(user);
    }

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, id);
	}
    
}
