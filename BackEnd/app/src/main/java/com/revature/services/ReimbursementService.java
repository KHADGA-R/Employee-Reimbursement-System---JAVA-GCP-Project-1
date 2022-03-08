package com.revature.services;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.util.LoggingSingleton;
import java.sql.Timestamp;
import java.util.List;

public class ReimbursementService {

    public ReimbursementService(ReimbursementDao rd){
        this.rd = rd;
    }

    private ReimbursementDao rd;

    public Reimbursement createReimbursement(int id, float amount, Timestamp submitted, Timestamp resolved,
                                             String description, int author, int resolver, int statusId, int typeId){
        Reimbursement reimbursement = new Reimbursement(id, amount, submitted, resolved, description, author, resolver,
                                                        statusId, typeId);

        LoggingSingleton.logger.info(this.getClass().getCanonicalName() + ": createReimbursement: " + reimbursement.toString());

        return reimbursement;
    }

    public boolean create(float amount, String description, int author, int typeId){
        Reimbursement reimbursement = new Reimbursement(amount, description, author, typeId);
        LoggingSingleton.logger.info(this.getClass().getCanonicalName() + ": create: " + reimbursement.toString());
        return rd.create(reimbursement);
    }

    public boolean updateStatus(Reimbursement reimbursement){
        LoggingSingleton.logger.info(this.getClass().getCanonicalName() + ": updateStatus: " + reimbursement.toString());
        return rd.updateStatus(reimbursement);}

    public List<Reimbursement> getAll(){ return rd.getAll();}

    public List<Reimbursement> getAllPending(){ return rd.getAllPending();}

    public List<Reimbursement> getAllResolved(){ return rd.getAllResolved();}

    public List<Reimbursement> getByAuthor(int id){ return rd.getByAuthor(id);}

    public List<Reimbursement> getByAuthorAndPending(int id){ return rd.getByAuthorAndPending(id);}

    public List<Reimbursement> getByAuthorAndResolved(int id){ return rd.getByAuthorAndResolved(id);}

}

