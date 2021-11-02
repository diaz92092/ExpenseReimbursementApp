package com.revature.ers.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.TimeZone;

public class SessionUtility {

    private static SessionFactory sessionFactory;

    public synchronized static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.username", "admin")
                    .setProperty("hibernate.connection.password", "Password")
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }

        return sessionFactory;
    }
}
