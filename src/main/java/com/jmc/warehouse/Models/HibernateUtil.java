package com.jmc.warehouse.Models;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            System.out.println("Initializing Hibernate SessionFactory...");

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

            System.out.println("✅ SessionFactory created successfully!");

        } catch (Throwable ex) {
            System.err.println("❌ SessionFactory creation failed!");
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory is not initialized!");
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            System.out.println("Shutting down Hibernate...");
            sessionFactory.close();
            System.out.println("✅ SessionFactory closed");
        }
    }
}