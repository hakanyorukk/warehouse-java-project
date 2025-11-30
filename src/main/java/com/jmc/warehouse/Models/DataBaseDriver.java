package com.jmc.warehouse.Models;

import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // Get All Agent data
    public List<AgentEntity> getAllAgents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM AgentEntity ORDER BY fullName";
            Query<AgentEntity> query = session.createQuery(hql, AgentEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Get Agent by ID
    public AgentEntity getAgentById(Integer agentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(AgentEntity.class, agentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Warehouses by AgentId
    public List<WarehouseEntity> getWarehousesByAgentId(Integer agentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM WarehouseEntity w WHERE w.agentId.id = :agentId";
            Query<WarehouseEntity> query = session.createQuery(hql, WarehouseEntity.class);
            query.setParameter("agentId", agentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get Warehouse by OwnerId
    public List<WarehouseEntity> getWarehousesByOwnerId(Integer ownerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //String hql = "FROM WarehouseEntity w WHERE w.ownerId.id = :ownerId";
            String hql = "SELECT w FROM WarehouseEntity w " +
                    "LEFT JOIN FETCH w.agentId " +
                    "WHERE w.ownerId.ownerId = :ownerId";

            Query<WarehouseEntity> query = session.createQuery(hql, WarehouseEntity.class);
            query.setParameter("ownerId", ownerId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Get warehouse by warehouse id
    public WarehouseEntity getWarehouseById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(WarehouseEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Get RentalWarehouse by AgentId
    public List<RentalWarehouseEntity> getRentalWarehousesByAgentId(Integer agentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //String hql = "FROM WarehouseEntity w WHERE w.ownerId.id = :ownerId";
            String hql = "SELECT r FROM RentalWarehouseEntity r " +
                    "LEFT JOIN FETCH r.warehouse " +
                    "WHERE r.agent.agentId = :agentId " +
                    "ORDER BY r.startDate DESC";

            Query<RentalWarehouseEntity> query = session.createQuery(hql, RentalWarehouseEntity.class);
            query.setParameter("agentId", agentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get RentalWarehouse by ID
    public RentalWarehouseEntity getRentalWarehouseById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(RentalWarehouseEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateRentalWarehouse(
            Integer id,
            String tenantName,
            BigDecimal monthlyPrice,
            LocalDate startDate,
            LocalDate endDate
    ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            // Load existing entity
            RentalWarehouseEntity rentalWarehouse = session.get(RentalWarehouseEntity.class, id);
            if (rentalWarehouse == null) {
                System.out.println("Rental warehouse not found");
                System.out.println(id);
                return false;
            }
            // Update ONLY fields that are NOT NULL
            if (tenantName != null) {
                rentalWarehouse.setTenantName(tenantName);
            }
            if (monthlyPrice != null) {
                rentalWarehouse.setMonthlyPrice(monthlyPrice);
            }
            if (startDate != null) {
                rentalWarehouse.setStartDate(startDate);
            }
            if (endDate != null) {
                rentalWarehouse.setEndDate(endDate);
            }
            // Persist ONLY updated fields
            session.update(rentalWarehouse);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
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

    // Create new warehouse
    public boolean createWarehouse(WarehouseEntity warehouse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(warehouse);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    // Create new Warehouse Rental Form
    public boolean createRentalWarehouse(RentalWarehouseEntity rentalWarehouse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(rentalWarehouse);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    // ==================== UPDATE OPERATIONS ====================

    // Update warehouse
    public boolean updateWarehouse(WarehouseEntity warehouse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(warehouse);  // Use merge to update
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

    // Update warehouse rental
    public boolean updateRentalWarehouse(RentalWarehouseEntity rental) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(rental);  // Use merge to update
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

    // Update owner
    public boolean updateOwner(OwnerEntity owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(owner);
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

    // Update agent
    public boolean updateAgent(AgentEntity agent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(agent);
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
