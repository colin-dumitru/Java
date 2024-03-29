package edu.jsf.model;

import edu.jsf.dao.JpaService;
import edu.jsf.dao.JpaTransaction;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
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

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().persist(sign);
            transaction.commit();
        }

        assertNotNull("Sign was not saved in DB", sign.getId());
    }

    @Test
    public void testRetrieve() {
        Sign sign = createSign();

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().persist(sign);
            transaction.commit();
        }

        Sign storedSign = jpaService.getEntityManager().find(Sign.class, sign.getId());

        assertThat("Stored sign does not match original", storedSign, is(equalTo(sign)));
    }

    private Sign createSign() {
        Sign sign = new Sign();
        sign.setCode("C11");
        sign.setData("data");
        sign.setImageType("image/x-png");
        sign.setName("name");
        return sign;
    }
}
