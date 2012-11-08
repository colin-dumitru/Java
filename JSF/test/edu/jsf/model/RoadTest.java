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
public class RoadTest {
    private JpaService jpaService;

    @Before
    public void init() {
        jpaService = new JpaService();
        jpaService.init();
    }

    @Test
    public void testSave() {
        Road road = createRoad();

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().persist(road);
            transaction.commit();
        }

        assertNotNull("Road was not saved in DB", road.getId());
    }

    @Test
    public void testRetrieve() {
        Road road = createRoad();

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().persist(road);
            transaction.commit();
        }

        Road storedRoad = jpaService.getEntityManager().find(Road.class, road.getId());

        assertThat("Stored road does not match original", storedRoad, is(equalTo(road)));
    }

    private Road createRoad() {
        Road road = new Road();
        road.setAdministration("administration");
        road.setLength(10);
        road.setRoadType(RoadType.COUNTY);
        road.setIndicative("E80");
        return road;
    }
}
