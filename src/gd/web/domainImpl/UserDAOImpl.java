package gd.web.domainImpl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.entity.User;

import gd.web.domain.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserByName(String userName){
		
		//it work?
		String queryStr = "from User where userName =  ?";
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setString(0, userName);
		

		
		
		List<User> ur = query.list();
		

		//String queryStr = "from  User WHERE userName =  '"+ userName +"'";
		//List<User> ur = sessionFactory.getCurrentSession().createQuery(queryStr).list();
		
		
		if(ur.size()!=0){
			//System.out.println("\n\n\nhere is the userName2: " + ur.get(0).getUserName());
			return ur.get(0);
		}
		return null;
	}
	@Override
	public User getUserById(int id){
		String queryStr = "from User where id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setInteger(0, id);
		List<User> uList = query.list();
		if(uList.size() != 0){
			return uList.get(0);
		}
		return null;
	}
	@Override
	public void addUser(User user){
		sessionFactory.getCurrentSession().save(user);
	}
	@Override
	public void deleteUser(User user){
		if(null!=user){
			sessionFactory.getCurrentSession().delete(user);
		}
	}
	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		
	}
}
