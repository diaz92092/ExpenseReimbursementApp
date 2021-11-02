package com.revature.ers;

import com.revature.ers.exception.ExceptionMapper;
import com.revature.ers.util.SessionUtility;
import io.javalin.Javalin;
import com.revature.ers.controller.Controller;
import com.revature.ers.controller.LoginController;

import com.revature.ers.controller.ReimbursementController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static Javalin app;

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Javalin app = Javalin.create((config) -> {
            config.addStaticFiles("static");
            config.enableCorsForAllOrigins();
        });

        app.before((ctx) -> {
            String URI = ctx.req.getRequestURI();
            String httpMethod = ctx.req.getMethod();
            logger.info(httpMethod + " request to endpoint " + URI + " received");
        });

        Session session = SessionUtility.getSessionFactory().openSession();
        mapControllers(app, new LoginController(session), new ExceptionMapper(), new ReimbursementController());

        app.start(9000);
    }

    public static void mapControllers(Javalin app, Controller... controllers) {
        for (Controller c : controllers) {
            c.mapEndpoints(app);
        }
    }
}
