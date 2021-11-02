package com.revature.ers.service;

import com.revature.ers.exception.DatabaseException;
import com.revature.ers.model.Users;
import com.revature.ers.DAO.UsersDAO;
import com.revature.ers.DTO.LoginDTO;
import com.revature.ers.exception.BadParameterException;
import com.revature.ers.exception.LoginException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private UsersDAO usersDAO;

    public LoginService(Session session) {
        this.usersDAO = new UsersDAO(session);
    }


    public LoginService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public List<Users> login(LoginDTO loginDTO) throws BadParameterException, LoginException, SQLException, DatabaseException {

        if ((loginDTO.getEmployee_id() == 0) || loginDTO.getPassword().trim().equals("")) {
            throw new BadParameterException("Cannot have a empty ID or password");
        }


        List<Users> users = usersDAO.getUserByEmployeeIDAndPassword(loginDTO);
        if (users.size() <= 0) {
            throw new LoginException("User unable to login with the ID and password entered.");
        }

        return users;
    }
}
