package com.revature.ers.service;

import com.revature.ers.DAO.ReimbursementDAO;
import com.revature.ers.exception.DatabaseException;
import com.revature.ers.model.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService() {
        this.reimbursementDAO = new ReimbursementDAO();
    }


    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public List<Reimbursement> getAllReimbursements() throws DatabaseException, SQLException {
        return ReimbursementDAO.getAllReimbursements();
    }

    public List<Reimbursement> getReimbursementByEmployeeID(int author) throws DatabaseException, SQLException {
        return reimbursementDAO.getReimbursementsByEmployeeID(author);
    }

    public Reimbursement addReimbursement(Reimbursement reimbursement) throws DatabaseException, SQLException {
        return reimbursementDAO.addReimbursement(reimbursement);
    }

    public Reimbursement approveReimbursementById(int reimbursement_id, Reimbursement reimbursement) throws DatabaseException,
            SQLException {
        return reimbursementDAO.approveReimbursementById(reimbursement_id, reimbursement);
    }

    public List<Reimbursement> getAvgAmountGroupByEmployeeId() throws DatabaseException, SQLException {
        return ReimbursementDAO.getAvgAmountGroupByEmployeeId();
    }

    public List<Reimbursement> getSumAmountOrderByAmountAsc() throws DatabaseException, SQLException {
        return ReimbursementDAO.getSumAmountOrderByAmountAsc();
    }

    public List<Reimbursement> getSumAmountOrderByAmountDesc() throws DatabaseException, SQLException {
        return ReimbursementDAO.getSumAmountOrderByAmountDesc();
    }
}
