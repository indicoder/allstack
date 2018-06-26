package com.allstack.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allstack.dao.CourseDao;
import com.allstack.dao.UserDao;
import com.allstack.pojo.Course;
import com.allstack.pojo.Question;
import com.allstack.pojo.User;

public class UserService {
	@Autowired
	UserDao userDao;
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public User getUserById(int userId){
		return userDao.getUserById(userId);
	}
	
	public User addOrUpdateUser(User user){
		return userDao.addOrUpdateUser(user);
	}
}
