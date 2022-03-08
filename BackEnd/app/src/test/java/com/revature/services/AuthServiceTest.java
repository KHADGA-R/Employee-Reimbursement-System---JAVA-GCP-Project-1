package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.models.User;
import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest extends TestCase {

    @Test
    public void testLoginUser(){
        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);
        AuthService as = new AuthService(userDao);

        User login = new User(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        us.getUserByUsernameAndPassword(login.getUsername(), login.getPassword());

        when(userDao.getUserByUsernameAndPassword(anyString(), anyString())).thenReturn(login);
        assertNotNull(login);


    }


}
