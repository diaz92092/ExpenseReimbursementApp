package com.revature.ers.DAO;

import com.revature.ers.DTO.LoginDTO;
import com.revature.ers.model.Employee;
import com.revature.ers.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

import com.revature.ers.util.SessionUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsersDAO {
    private Session session;
    private static final Logger logger = LoggerFactory.getLogger(UsersDAO.class);

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public UsersDAO(Session session) {
        this.setSessionFactory(session);
//        super();
    }

    public void setSessionFactory(Session session) {
        this.session = session;
    }

    public List<Users> getUserByEmployeeIDAndPassword(LoginDTO loginDTO) {
        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        logger.info("employeeID:" + Integer.toString(loginDTO.getEmployee_id()));
        logger.info("password:" + loginDTO.getPassword());
        String hql = "FROM Users WHERE username=?1 AND password=?2";
        Query query = session.createQuery(hql)
                .setParameter(1, loginDTO.getEmployee_id())
                .setParameter(2, loginDTO.getPassword());
        List results = query.list();
        tx.commit();
        return results;
    }

    //TODO: Get User Role Function
    //example getUserByEmployeeIDAndPassword
}
