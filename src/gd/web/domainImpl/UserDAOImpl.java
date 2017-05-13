package gd.web.domainImpl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.entity.UserEntity;

import gd.web.domain.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UserEntity getUserByName(String userName){
		
		//it work? yes
		String queryStr = "from UserEntity where userName =  ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setString(0, userName);
		query.setInteger(1, 1);

		
		
		List<UserEntity> ur = query.list();
		

		//String queryStr = "from  UserEntity WHERE userName =  '"+ userName +"'";
		//List<User> ur = sessionFactory.getCurrentSession().createQuery(queryStr).list();
		
		
		if(ur.size()!=0){
			//System.out.println("\n\n\nhere is the userName2: " + ur.get(0).getUserName());
			return ur.get(0);
		}
		return null;
	}
	
	@Override
	public UserEntity getUserById(int id){
		String queryStr = "from UserEntity where id = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setInteger(0, id);
		query.setInteger(1, 1);
		List<UserEntity> uList = query.list();
		if(uList.size() != 0){
			return uList.get(0);
		}
		return null;
	}
	
	@Override
	public void addUser(UserEntity userEntity){
		sessionFactory.getCurrentSession().save(userEntity);
	}
	
	@Override
	public void deleteUser(UserEntity userEntity){
		if(null!=userEntity){
			sessionFactory.getCurrentSession().delete(userEntity);
		}
	}
	
	@Override
	public void deleteUserById(int id){
		UserEntity ue = getUserById(id);
		if(ue == null){
			return;
		}
		ue.setIsValid(0);
		updateUser(ue);
	}
	
	@Override
	public void updateUser(UserEntity userEntity) {
		sessionFactory.getCurrentSession().update(userEntity);
		
	}

	@Override
	public UserEntity login(UserEntity userEntity) {
		// TODO Auto-generated method stub
		String queryStr = "from UserEntity where userName = ? and password = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setString(0, userEntity.getUserName());
		query.setString(1, userEntity.getPassword());
		query.setInteger(2, 1);
		List<UserEntity> uList = query.list();
		if(uList.size() != 0){
			return uList.get(0);
		}
		return null;
	}

	@Override
	public UserEntity getUserByStaId(int id) {
		// TODO Auto-generated method stub
		String queryStr = "from UserEntity where staId = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setInteger(0, id);
		query.setInteger(1, 1);
		List<UserEntity> uList = query.list();
		if(uList.size() != 0){
			return uList.get(0);
		}
		return null;
	}
}
