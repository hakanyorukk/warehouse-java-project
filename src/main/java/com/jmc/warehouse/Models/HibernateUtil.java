package com.jmc.warehouse.Models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    static {
        try {
            logger.info("Initializing Hibernate SessionFactory...");

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
            logger.info("SessionFactory created successfully");


        } catch (Throwable ex) {
            logger.error("SessionFactory creation failed={}", ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            logger.error("SessionFactory is not initialized");
            throw new IllegalStateException("SessionFactory is not initialized!");
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            logger.info("Shutting down Hibernate...");
            sessionFactory.close();
            logger.info("SessionFactory closed");
        }
    }
}