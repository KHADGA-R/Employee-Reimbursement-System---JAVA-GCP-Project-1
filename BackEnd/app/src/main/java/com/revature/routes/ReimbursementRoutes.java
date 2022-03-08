package com.revature.routes;

import com.revature.controllers.ReimbursementController;
import io.javalin.Javalin;

public class ReimbursementRoutes extends Route{
    private ReimbursementController rc;

    public ReimbursementRoutes(ReimbursementController rc) {this.rc = rc;}

    @Override
    public void registerLocalRoutes(Javalin app) {
        app.post("/reimbursement/submit", rc.create);
        app.put("/reimbursement/update", rc.update);
        app.get("/reimbursement/all", rc.getAll);
        app.get("/reimbursement/pending", rc.getAllPending);
        app.get("/reimbursement/resolved", rc.getAllResolved);
        app.get("/reimbursement/{id}", rc.getByAuthor);
        app.get("/reimbursement/pending/{id}", rc.getByAuthorAndPending);
        app.get("/reimbursement/resolved/{id}", rc.getByAuthorAndResolved);

    }
}
