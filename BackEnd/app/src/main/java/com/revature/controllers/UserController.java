package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;

public class UserController {

    private UserService us;
    private ObjectMapper mapper = new ObjectMapper();

    public UserController(UserService us){
        this.us = us;
    }

    public Handler createUser = (context) -> {
        User u = mapper.readValue(context.body(), User.class);
        us.create(u);
    };

    public Handler getAllUsers = (context) -> {
        context.result(mapper.writeValueAsString(us.getAllUsers()));
    };

    public Handler getUserById = (context) -> {
        String idParam = context.pathParam("id");
        int id = Integer.parseInt(idParam);

        context.result(mapper.writeValueAsString(us.getUserById(id)));
    };

    public Handler updateUser = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        User u = mapper.readValue(context.body(), User.class);
        u.setId(id);

        us.updateUser(u);
    };


}
