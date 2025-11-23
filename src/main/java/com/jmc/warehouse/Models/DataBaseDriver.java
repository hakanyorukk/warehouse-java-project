package com.jmc.warehouse.Models;

import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class DataBaseDriver {

    public DataBaseDriver() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Hibernate connected successfully!");
        } catch (Exception e) {
            System.err.println("Hibernate connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get Owner by username and password
    public OwnerEntity getOwnerData(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM OwnerEntity o WHERE o.username = :username AND o.password = :password";
            Query<OwnerEntity> query = session.createQuery(hql, OwnerEntity.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResultOptional().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Agent by username and password
    public AgentEntity getAgentData(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM AgentEntity o WHERE o.username = :username AND o.password = :password";
            Query<AgentEntity> query = session.createQuery(hql, AgentEntity.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResultOptional().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Admin by username and password
    public AdminEntity getAdminData(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM AdminEntity o WHERE o.username = :username AND o.password = :password";
            Query<AdminEntity> query = session.createQuery(hql, AdminEntity.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResultOptional().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create new Owner
    public boolean createOwner(OwnerEntity owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(owner);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    // Create new Agent
    public boolean createAgent(AgentEntity agent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(agent);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

}
