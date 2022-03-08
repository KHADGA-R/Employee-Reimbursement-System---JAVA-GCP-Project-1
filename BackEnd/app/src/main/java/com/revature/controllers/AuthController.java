package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

public class AuthController {

    private AuthService as;
    private UserService us;
    private ObjectMapper mapper = new ObjectMapper();

    public AuthController(AuthService as, UserService us){
        this.as = as;
        this.us = us;
    }
    public Handler login = (context) -> {
        //interpret the request
        String username = context.formParam("username");
        String password = context.formParam("password");
        //fulfill the request
        User user = us.getUserByUsernameAndPassword(username, password);

        //preparing response
        if(user == null) {
            throw new UnauthorizedResponse("Incorrect Username and Password.");
        }else {
            context.status(200);
            context.result("Login Successful.");
        }


        //let set the session to know that the user is logged in
        context.req.getSession().setAttribute("id", user.getId());
        context.req.getSession().setAttribute("LoggedIn", user.getUsername());
        context.req.getSession().setAttribute("type", user.getRoleId());

        context.header("id", Integer.toString(user.getId()));
        context.header("LoggedIn", user.getUsername());
        context.header("type", Integer.toString(user.getRoleId()));
        context.result(mapper.writeValueAsString(user));

    };

    public Handler verify = (context) -> {
        context.header("Access-Control-Expose-Headers", "*");

        System.out.println(context.req.getSession().getAttribute("id"));

        if(context.req.getSession().getAttribute("id") == null) {
            context.status(400);
            context.result("User not logged in.");
        }else {
            context.header("uid", ""+context.req.getSession().getAttribute("id"));
            context.result("User was verified as logged in.");
        }
    };

    public Handler logout = context -> {
        context.req.getSession().invalidate();
        context.status(200);
        context.result("User logged out");
    };
}
