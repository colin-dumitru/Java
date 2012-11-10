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
public class SignPlacementTest {
    private JpaService jpaService;

    @Before
    public void init() {
        jpaService = new JpaService();
        jpaService.init();
    }

    @Test
    public void testSave() {
        SignPlacement signPlacement = null;

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            Sign sign = save(createSign());
            Road road = save(createRoad());
            signPlacement = createSignPlacement(sign, road);

            jpaService.getEntityManager().persist(signPlacement);
            transaction.commit();
        }

        assertNotNull("Sign placement was not saved in DB", signPlacement.getId());
    }

    @Test
    public void testRetrieve() {
        SignPlacement signPlacement = null;

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            Sign sign = save(createSign());
            Road road = save(createRoad());
            signPlacement = createSignPlacement(sign, road);

            jpaService.getEntityManager().persist(signPlacement);
            transaction.commit();
        }

        SignPlacement storedSignPlacement = jpaService.getEntityManager().find(SignPlacement.class, signPlacement.getId());

        assertThat("Stored sign does not match original", storedSignPlacement, is(equalTo(signPlacement)));
    }

    private SignPlacement createSignPlacement(Sign sign, Road road) {
        SignPlacement placement = new SignPlacement();
        placement.setKilometer(10);
        placement.setRoad(road);
        placement.setSign(sign);
        placement.setComment("Temp");
        return placement;
    }

    private Sign createSign() {
        Sign sign = new Sign();
        sign.setCode("C11");
        sign.setData("data");
        sign.setImageType("image/x-png");
        sign.setName("name");
        return sign;
    }

    private Road createRoad() {
        Road road = new Road();
        road.setAdministration("administration");
        road.setLength(10);
        road.setRoadType(RoadType.COUNTY);
        road.setIndicative("E80");
        return road;
    }

    public <T> T save(T object) {
        jpaService.getEntityManager().persist(object);
        return object;
    }
}
