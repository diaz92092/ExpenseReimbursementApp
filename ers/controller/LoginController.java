package com.revature.ers.controller;

import com.revature.ers.DAO.UsersDAO;
import com.revature.ers.DTO.LoginDTO;
import com.revature.ers.DTO.MessageDTO;
import com.revature.ers.model.Users;
import com.revature.ers.service.LoginService;
import io.javalin.Javalin;

import io.javalin.http.Handler;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class LoginController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginService;

    public LoginController(Session session) {
        this.loginService = new LoginService(session);
    }

    private Handler loginHandler = (ctx) -> {

        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);

        List<Users> users = loginService.login(loginDTO);
        ctx.sessionAttribute("currentlyLoggedInUser", users);
        System.out.println(users);
        ctx.json(users);
    };

    private Handler currentUserHandler = (ctx) -> {

        Users user = ctx.sessionAttribute("currentlyLoggedInUser");

        if (user == null) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage("User is not currently logged in");
            ctx.json(messageDTO);
            ctx.status(400);
        } else {
            ctx.json(user);
        }
    };

    private Handler logoutHandler = (ctx) -> {
        ctx.req.getSession().invalidate();
    };


    @Override
    public void mapEndpoints(Javalin app) {

        app.post("/login", loginHandler);
        app.get("/current_user", currentUserHandler);
        app.post("/logout", logoutHandler);
    }
}
