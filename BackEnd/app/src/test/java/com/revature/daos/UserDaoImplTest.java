package com.revature.daos;

import com.revature.models.User;
import com.revature.services.UserService;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Deprecated
public class UserDaoImplTest extends TestCase {

    @Test
    public void testGetAllUsers(){
        //create a list to get the return object from the mock
        List<User> users = new ArrayList<>();
        //Mock the class we're testing that requires db connection
        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);
        //Instance of the class we're testing

        User user = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        users.add(user);

        List<User> test = us.getAllUsers();
        //when it calls the function we're testing it returns the expected return type
        when(userDao.getAllUsers()).thenReturn(users);
        //makes sure that the test object has something;
        assertNotNull(test);
    }

    @Test
    public void testGetUserById(){
        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);

        User user = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        userDao.getUserById(1);

        when(userDao.getUserById(anyInt())).thenReturn(user);

        assertNotNull(user);

    }
    @Test
    public void testUpdateUser(){
        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);

        User user = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        userDao.updateUser(user);

        when(userDao.updateUser(user)).thenReturn(true);

        assertNotNull(user);

    }
    @Test
    public void testCreateUser(){
        UserDao userDao = mock(UserDaoImpl.class);
        UserService us = new UserService(userDao);

        User user = us.createUser(1,"JohnDoe", "password", "John", "Doe", "JohnDoe@email.com", 1 );
        userDao.createUser(user);

        when(userDao.createUser(user)).thenReturn(true);

        assertNotNull(user);
    }


}

