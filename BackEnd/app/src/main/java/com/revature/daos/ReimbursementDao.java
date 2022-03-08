package com.revature.daos;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    boolean create(Reimbursement reimbursement);
    boolean updateStatus(Reimbursement reimbursement);
    List<Reimbursement> getAll();
    List<Reimbursement> getAllPending();
    List<Reimbursement> getAllResolved();
    List<Reimbursement> getByAuthor(int sid);
    List<Reimbursement> getByAuthorAndPending(int sid);
    List<Reimbursement> getByAuthorAndResolved(int sid);
}
