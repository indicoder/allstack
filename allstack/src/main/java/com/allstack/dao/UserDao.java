package com.allstack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.pojo.Question;
import com.allstack.pojo.QuizCollection;
import com.allstack.pojo.User;

public class UserDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public List<User> getAllUsers(){
		Session session = sessionFactory.openSession();	
		List<User> users = session.createQuery("select c from User c").list(); 
		return users;
	}
	
	public User getUserById(int userId){
		Session session = sessionFactory.openSession();
		User user = null;
		List<User> singleUserList = session.createQuery("select c from User c where c.userId=:userId").setParameter("userId", userId).list();
		if(!singleUserList.isEmpty()){
			user = singleUserList.get(0);
		}
		return user;
	}
	
	public User addOrUpdateUser(User user){
		Session session = sessionFactory.openSession();
		if(user == null) return user;
		
		session.beginTransaction();			
		session.saveOrUpdate(user);			
		session.getTransaction().commit();
		
		return user;
	}
}
