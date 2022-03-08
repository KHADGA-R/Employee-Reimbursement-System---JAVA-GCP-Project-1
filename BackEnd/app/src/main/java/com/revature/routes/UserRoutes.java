package com.revature.routes;

import com.revature.controllers.AuthController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class UserRoutes extends Route{
    private UserController uc;

    public UserRoutes(UserController uc){this.uc = uc;}

    @Override
    public void registerLocalRoutes(Javalin app) {
        app.get("/ers_user", uc.getAllUsers);
        app.get("/ers_user/{id}", uc.getUserById);
        app.post("/ers_user", uc.createUser);//failed
        app.put("/ers_user/{id}", uc.updateUser);//failed
    }
}
