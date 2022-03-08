package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.models.User;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest extends TestCase {

    @Test
    public void testCreateUser() {
        UserDao ud = mock(UserDaoImpl.class);
        UserService us = new UserService(ud);

        User test = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );

        assertNotNull(test);

    }

    public void testCreate(){
        UserDao ud = mock(UserDaoImpl.class);
        UserService us = new UserService(ud);

        User test = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );

        when(ud.createUser(any())).thenReturn(true);

        boolean status = us.create(test);

        assertTrue(true);
    }

    @Test
    public void testGetAllUsers(){
        //create a list to get the return object from the mock
        List<User> users = new ArrayList<>();
        UserDao ud = mock(UserDaoImpl.class);
        UserService us = new UserService(ud);
        //Instance of the class we're testing

        User user = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        users.add(user);

        //when it calls the function we're testing it returns the expected return type
        when(ud.getAllUsers()).thenReturn(users);
        List<User> test = us.getAllUsers();
        //makes sure that the test object has something;
        assertNotNull(test);
    }
    @Test
    public void testGetUserById(){

        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);

        User user = new User(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        us.getUserById(1);

        when(userDao.getUserById(anyInt())).thenReturn(user);
        assertNotNull(user);

    }

    @Test
    public void testUpdateUser(){
        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);

        User user = new User(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        us.updateUser(user);

        when(userDao.updateUser(user)).thenReturn(true);

        assertNotNull(user);

    }


}