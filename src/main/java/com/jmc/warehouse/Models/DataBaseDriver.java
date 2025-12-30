package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.*;
import com.jmc.warehouse.Views.ClimaticConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Internal;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDriver {
    private static final Logger logger = LogManager.getLogger(DataBaseDriver.class);
    public DataBaseDriver() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logger.info("Hibernate connected successfully!");
        } catch (Exception e) {
            logger.error("Hibernate connection failed", e);
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
            logger.error("Failed to get owner data", e);
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
            logger.error("Failed to get agent data with username and password", e);
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
            logger.error("Failed to get admin data", e);
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
            logger.error("Failed to get all agent data");
            return new ArrayList<>();
        }
    }

    // Get Agent by ID
    public AgentEntity getAgentById(Integer agentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(AgentEntity.class, agentId);
        } catch (Exception e) {
            logger.error("Failed to get agent by id={}", agentId, e);
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
            logger.error("Failed to get warehous by agent id={}", agentId, e);
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
            logger.error("Failed to get warehouse by owner id={}", ownerId, e);
            return null;
        }
    }


    // Get warehouse by warehouse id
    public WarehouseEntity getWarehouseById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(WarehouseEntity.class, id);
        } catch (Exception e) {
            logger.error("Failed to get warehouse by id={}", id, e);
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
            logger.error("Failed to get rental warehouse by agent id={}", agentId, e);
            return null;
        }
    }

    // Get RentalWarehouse by ID
    public RentalWarehouseEntity getRentalWarehouseById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(RentalWarehouseEntity.class, id);
        } catch (Exception e) {
            logger.error("Failed to get rental warehouse by id={}", id, e);
            return null;
        }
    }

    public List<AgentNotificationEntity> getAgentNotificationsByAgentId(Integer agentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            FROM AgentNotificationEntity n
            WHERE n.agent.agentId = :agentId
            ORDER BY n.notificationId DESC
        """;

            return session.createQuery(hql, AgentNotificationEntity.class)
                    .setParameter("agentId", agentId)
                    .list();
        } catch (Exception e) {
            logger.error("Failed to load agent notifications", e);
            return List.of();
        }
    }

    public List<OwnerNotificationEntity> getOwnerNotificationsByOwnerId(Integer ownerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            FROM OwnerNotificationEntity n
            WHERE n.owner.ownerId = :ownerId
            ORDER BY n.notificationId DESC
        """;

            return session.createQuery(hql, OwnerNotificationEntity.class)
                    .setParameter("ownerId", ownerId)
                    .list();
        } catch (Exception e) {
            logger.error("Failed to load owner notifications", e);
            return List.of();
        }
    }




//============CREATE==================



    // Create new Owner
    public boolean createOwner(OwnerEntity owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(owner);
            transaction.commit();
            logger.info("Owner created successfully username={}", owner.getUsername());
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to create owner");
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
            logger.info("Agent created successfully: username={}", agent.getUsername());
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to create agent");
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
            logger.info("Warehouse created successfully name={}", warehouse.getName());
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to create warehouse");
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
            logger.info("Rental warehouse created successfully id={}", rentalWarehouse.getWarehouseId());
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to create rental warehouse");
            return false;
        } finally {
            session.close();
        }
    }

    // create agent notification
    public boolean createAgentNotification(AgentNotificationEntity agentNotification) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(agentNotification);
            transaction.commit();
            logger.info("Agent notification created successfully id={}", agentNotification.getNotificationId());
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to create agent notification, {}", e.getMessage());
            return false;
        } finally {
            session.close();
        }
    }

    // create owner notification
    public boolean createOwnerNotification(OwnerNotificationEntity ownerNotification) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(ownerNotification);
            transaction.commit();
            logger.info("Owner notification created successfully id={}", ownerNotification.getNotificationId());
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to create owner notification, {}", e.getMessage());
            return false;
        } finally {
            session.close();
        }
    }

    // ==================== UPDATE OPERATIONS ====================

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
                logger.warn("Rental warehouse not found id={}", id);
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
            logger.info("Rental warehouse successfully updated");
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Failed to updating rental warehouse");
            return false;
        } finally {
            session.close();
        }
    }

    // Update warehouse
    public boolean updateWarehouse(Integer id, String name, String address, String dimensions, Double area, ClimaticConditions climaticConditions, AgentEntity assignedAgent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Load existing entity
            WarehouseEntity warehouse = session.get(WarehouseEntity.class, id);
            if(warehouse == null) {
                logger.warn("Warehouse nod found");
                return false;
            }
            // Update ONLY fields that are NOT NULL
            if(name!= null) {
                warehouse.setName(name);
            }
            if(address != null) {
                warehouse.setAddress(address);
            }
            if(area != null) {
                warehouse.setArea(area);
            }
            if(dimensions != null) {
                warehouse.setDimensions(dimensions);
            }
            if(climaticConditions != null) {
                warehouse.setClimaticConditions(climaticConditions);
            }
            // If there's an agent with an ID, load the managed entity
            if (assignedAgent != null && assignedAgent.getAgentId() != null) {
                AgentEntity managedAgent = session.get(
                        AgentEntity.class,
                        assignedAgent.getAgentId()
                );

                if (managedAgent != null) {
                    warehouse.setAgent(managedAgent);
                }
            }
            // Persist ONLY updated fields

            session.update(warehouse);  // Use merge to update
            transaction.commit();
            logger.info("Warehouse successfully updated");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to updating warehouse");
            return false;
        } finally {
            session.close();
        }
    }

    /*
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

     */

}
