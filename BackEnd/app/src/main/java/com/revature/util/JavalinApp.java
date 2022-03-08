package com.revature.util;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.routes.AuthRoutes;
import com.revature.routes.ReimbursementRoutes;
import com.revature.routes.Route;
import com.revature.routes.UserRoutes;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import io.javalin.Javalin;

public class JavalinApp {
    private static ReimbursementDao rd = new ReimbursementDaoImpl();
    private static ReimbursementService rs = new ReimbursementService(rd);
    private static ReimbursementController rc = new ReimbursementController(rs);

    private static UserDao ud = new UserDaoImpl();
    private static UserService us = new UserService(ud);
    private static UserController uc = new UserController(us);

    private static AuthService as= new AuthService(ud);
    private  static AuthController ac = new AuthController(as, us);


    private Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.enableCorsForAllOrigins();
            //javalinConfig.addStaticFiles("/", Location.CLASSPATH);
        });

    public void start(int port){
        app.get("/hello", ctx -> ctx.result("Hello get path"));

        Route reimbursement = new ReimbursementRoutes(rc);
        Route user = new UserRoutes(uc);
        Route auth = new AuthRoutes(ac);

        Route.establishRoutes(app, reimbursement, user, auth);

        app.error(403, ctx -> {
            ctx.result("The request you submitted is invalid");
        });

        app.start(port);
    }
}
