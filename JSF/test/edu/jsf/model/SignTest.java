package edu.jsf.model;

import edu.jsf.dao.JpaService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class SignTest {
    private JpaService jpaService;

    @Before
    public void init() {
        jpaService = new JpaService();
        jpaService.init();
    }

    @Test
    public void testSave() {
        Sign sign = createSign();

        jpaService.getEntityManager().persist(sign);
    }

    @Test
    public void testRetrieve() {
        Sign sign = createSign();

        jpaService.getEntityManager().persist(sign);
        jpaService.getEntityManager().createQuery("select s from Sign s").getResultList();
        Sign storedSign = jpaService.getEntityManager().find(Sign.class, sign.getId());

        assertThat("Stored sign does not match original", storedSign, is(equalTo(sign)));
    }

    private Sign createSign() {
        Sign sign = new Sign();
        return sign;
    }
}
