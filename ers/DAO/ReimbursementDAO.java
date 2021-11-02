package com.revature.ers.DAO;

import com.revature.ers.exception.DatabaseException;
import com.revature.ers.model.Reimbursement;

import com.revature.ers.util.SessionUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDAO {

    private static final Logger logger = LoggerFactory.getLogger(ReimbursementDAO.class);

    private Connection connection;

    public ReimbursementDAO() {
        super();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static List<Reimbursement> getAllReimbursements() {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            String hql = "FROM Reimbursement";
            Query query = session.createQuery(hql);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return getAllReimbursements();
    }

    public List<Reimbursement> getReimbursementsByEmployeeID(int employee_id) throws DatabaseException, SQLException {
        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            logger.info("Employee_id value is: " + employee_id);

            String hql = "FROM Reimbursement WHERE employee_id = ?1";
            Query query = session.createQuery(hql).setParameter(1, employee_id);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return getReimbursementsByEmployeeID(employee_id);
    }


//    public Reimbursement addReimbursement(Reimbursement reimbursement) throws DatabaseException,
//            SQLException {
//
//        Session session = SessionUtility.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        try {
//
//            String hql = "insert into reimbursement (employee_id, amount, description, expense_date) values (6, 520, 'transportation', '2021-06-15');";
//            Query query = session.createQuery(hql);
//            query.setParameter(1, reimbursement.getEmployee_id());
//            query.setParameter(2, reimbursement.getAmount());
//            query.setParameter(3, reimbursement.getDescription());
//            query.setParameter(4, reimbursement.getExpense_date());
//
//
//            System.out.println(reimbursement.getEmployee_id());
//            System.out.println(reimbursement.getAmount());
//            int result = query.executeUpdate();
//
//            tx.commit();
//            return reimbursement;
//
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//
//        return addReimbursement(reimbursement);
//
//    }

    public Reimbursement addReimbursement(Reimbursement reimbursement) throws DatabaseException,
            SQLException {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {

            Reimbursement newReim = new Reimbursement();
            newReim = reimbursement;

            logger.info("new reimbursement value is: " + reimbursement);

            session.save(newReim);

            tx.commit();
            return newReim;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return addReimbursement(reimbursement);
    }


    public Reimbursement approveReimbursementById(int reimbursement_id, Reimbursement reimbursement) throws DatabaseException,
            SQLException {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {

            String hql = "UPDATE Reimbursement r SET r.status = ?1, r.message = ?2 WHERE r.reimbursement_id = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, reimbursement.getStatus());
            query.setParameter(2, reimbursement.getMessage());
            query.setParameter(3, reimbursement_id);
            System.out.println(reimbursement_id);
            System.out.println(reimbursement.getMessage());
            System.out.println(reimbursement.getStatus());
            int result = query.executeUpdate();

            tx.commit();
            return reimbursement;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return approveReimbursementById(reimbursement_id, reimbursement);

    }


//    public static List<Reimbursement> getAllReimbursements() {
//
//        Session session = SessionUtility.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        try {
//            String hql = "FROM Reimbursement";
//            Query query = session.createQuery(hql);
//            List results = query.list();
//
//            tx.commit();
//            return results;
//
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return getAllReimbursements();
//    }

    public static List<Reimbursement> getAvgAmountGroupByEmployeeId() {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            String hql = "select avg(amount) from Reimbursement";
            Query query = session.createQuery(hql);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

    public static List<Reimbursement> getSumAmountOrderByAmountDesc() {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            String hql = "select employee_id , sum(amount) from Reimbursement group by employee_id order by sum(amount) desc";
            Query query = session.createQuery(hql);
            query.setMaxResults(3);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

    public static List<Reimbursement> getSumAmountOrderByAmountAsc() {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            String hql = "select employee_id , sum(amount) from Reimbursement group by employee_id order by sum(amount) asc";
            Query query = session.createQuery(hql);
            query.setMaxResults(3);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

//    public static List<Reimbursement> getAllReimbursements() {
//
//        Session session = SessionUtility.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        try {
//            String hql = "FROM Reimbursement";
//            Query query = session.createQuery(hql);
//            List results = query.list();
//
//            tx.commit();
//            return results;
//
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return getAllReimbursements();
//    }
}







