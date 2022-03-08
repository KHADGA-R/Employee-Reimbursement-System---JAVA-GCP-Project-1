package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {

    private ReimbursementService rs;
    private ObjectMapper mapper = new ObjectMapper();

    public ReimbursementController(ReimbursementService rs){ this.rs = rs;}

    public Handler create = (context) -> {
        Reimbursement r = mapper.readValue(context.body(), Reimbursement.class);
        rs.create(r.getAmount(), r.getDescription(), r.getAuthor(), r.getTypeId());
    };

    public Handler update = (context) -> {
        Reimbursement r = mapper.readValue(context.body(), Reimbursement.class);
        rs.updateStatus(r);
    };

    public Handler getAll = (context) -> {
        context.result(mapper.writeValueAsString(rs.getAll()));
    };

    public Handler getAllPending = (context) -> {
        context.result(mapper.writeValueAsString(rs.getAllPending()));
    };

    public Handler getAllResolved = (context) ->{
        context.result(mapper.writeValueAsString(rs.getAllResolved()));
    };

    public Handler getByAuthor = (context) -> {
        Integer id = Integer.parseInt(context.pathParam("id"));
        context.result(mapper.writeValueAsString(rs.getByAuthor(id)));
    };

    public Handler getByAuthorAndPending = (context) -> {
        Integer id = Integer.parseInt(context.pathParam("id"));
        context.result(mapper.writeValueAsString(rs.getByAuthorAndPending(id)));
    };

    public Handler getByAuthorAndResolved = (context) -> {
        Integer id = Integer.parseInt(context.pathParam("id"));
        context.result(mapper.writeValueAsString(rs.getByAuthorAndResolved(id)));
    };




}
