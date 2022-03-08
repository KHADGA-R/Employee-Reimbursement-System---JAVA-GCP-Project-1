package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.util.LoggingSingleton;

import java.util.List;

public class UserService {


    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public User createUser(int id, String username, String password, String firstName, String lastName, String email, int roleId){
        User user = new User(id, username, password, firstName, lastName, email, roleId);

        LoggingSingleton.logger.info("New User task created :" + user.toString());
        return user;
    }

    public boolean create(User u) {
        LoggingSingleton.logger.info(this.getClass().getCanonicalName() + ": createUser: " + u.toString());
        return userDao.createUser(u);
    }

    public List<User> getAllUsers(){ return userDao.getAllUsers();}

    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    public boolean updateUser(User u){
        LoggingSingleton.logger.info(this.getClass().getCanonicalName() + ": updated user: " + u.toString());
        return userDao.updateUser(u);
    }

    public User getUserByUsernameAndPassword(String username, String password) {return userDao.getUserByUsernameAndPassword(username, password);}


}
