package edu.jsf.dao;

import javax.persistence.EntityTransaction;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class JpaTransaction implements AutoCloseable {
    private Boolean commit = false;
    private EntityTransaction transaction;

    public JpaTransaction(EntityTransaction transaction) {
        this.transaction = transaction;
        transaction.begin();
    }

    @Override
    public void close() {
        if (commit) {
            transaction.commit();
        } else {
            transaction.rollback();
        }
    }

    public void commit() {
        commit = true;
    }
}
