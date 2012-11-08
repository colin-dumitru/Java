package edu.jsf.dao;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@ManagedBean
@ApplicationScoped
public class JpaService {
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = Persistence.createEntityManagerFactory("persistence")
                .createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public JpaTransaction beginTransaction() {
        return new JpaTransaction(entityManager.getTransaction());
    }


}
