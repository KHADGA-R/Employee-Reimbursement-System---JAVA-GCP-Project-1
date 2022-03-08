package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.util.LoggingSingleton;

public class AuthService {

    private UserDao userDao;

    public AuthService (UserDao userDao){
        this.userDao = userDao;
    }
    public boolean loginUser(String username, String password){
        User login = userDao.getUserByUsernameAndPassword(username, password);

        if(login == null || !login.getPassword().equals(password)) {
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName() + "A user has failed to log in" +username.toString());
            return false;
        }
        LoggingSingleton.logger.info(this.getClass().getCanonicalName() + "A user has succcesfully logged in" +username.toString());
        return true;
    }
}
