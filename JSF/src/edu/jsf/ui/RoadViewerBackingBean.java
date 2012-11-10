package edu.jsf.ui;

import edu.jsf.dao.JpaService;
import edu.jsf.dao.JpaTransaction;
import edu.jsf.model.Road;
import edu.jsf.model.Sign;
import edu.jsf.model.SignPlacement;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@ManagedBean
@ViewScoped
public class RoadViewerBackingBean {
    @ManagedProperty("#{jpaService}")
    private JpaService jpaService;

    private Road selectedRoad;
    private Sign selectedSign;
    private Integer atKilometer;

    @PostConstruct
    public void init() {

    }

    public JpaService getJpaService() {
        return jpaService;
    }

    public void setJpaService(JpaService jpaService) {
        this.jpaService = jpaService;
    }


    public Collection<SelectItem> getRoads() {
        List<Road> roadList = jpaService.getEntityManager().createQuery("select r from Road r", Road.class)
                .getResultList();
        return createRoadSelectItems(roadList);
    }

    private Collection<SelectItem> createRoadSelectItems(List<Road> roadList) {
        Collection<SelectItem> selectItems = new ArrayList<>();
        for (Road road : roadList) {
            selectItems.add(new SelectItem(road.getId(), road.toString()));
        }
        return selectItems;
    }

    public String getSelectedRoad() {
        if (selectedRoad != null) {
            return selectedRoad.getId().toString();
        }
        return null;
    }

    public void setSelectedRoad(String id) {
        if (id != null) {
            selectedRoad = jpaService.getEntityManager().createQuery("select r from Road r where r.id=:id", Road.class)
                    .setParameter("id", Integer.parseInt(id))
                    .getSingleResult();
        }
    }

    public Collection<SignPlacement> getSignPlacements() {
        if (selectedRoad != null) {
            return jpaService.getEntityManager().createQuery("select sp from SignPlacement sp " +
                    "where sp.road.id=:roadId", SignPlacement.class)
                    .setParameter("roadId", selectedRoad.getId())
                    .getResultList();
        }
        return Collections.emptyList();
    }

    public String getRoadIndicative() {
        if (selectedRoad != null) {
            return selectedRoad.getIndicative();
        }
        return null;
    }

    public String getRoadAdministration() {
        if (selectedRoad != null) {
            return selectedRoad.getAdministration();
        }
        return null;
    }

    public String getRoadLength() {
        if (selectedRoad != null) {
            return selectedRoad.getLength().toString();
        }
        return null;
    }

    public String getRoadType() {
        if (selectedRoad != null) {
            return selectedRoad.getRoadType().getUserName();
        }
        return null;
    }

    public String getSelectedSign() {
        if (selectedSign != null) {
            return selectedSign.getId().toString();
        }
        return null;
    }

    public void setSelectedSign(String signId) {
        selectedSign = jpaService.getEntityManager().createQuery("select s from Sign s where s.id=:id", Sign.class)
                .setParameter("id", Integer.parseInt(signId))
                .getSingleResult();
    }

    public Collection<SelectItem> getSigns() {
        List<Sign> signs = jpaService.getEntityManager().createQuery("select s from Sign s", Sign.class)
                .getResultList();
        return createSignSelectItems(signs);
    }

    private Collection<SelectItem> createSignSelectItems(List<Sign> signs) {
        Collection<SelectItem> selectItems = new ArrayList<>();
        for (Sign sign : signs) {
            selectItems.add(new SelectItem(sign.getId(), sign.toString()));
        }
        return selectItems;
    }

    public String getKilometer() {
        return "0";
    }

    public void setKilometer(String kilometer) {
        if (kilometer != null) {
            this.atKilometer = Integer.parseInt(kilometer);
        }
    }

    public void validateKilometer(FacesContext context, UIComponent validate, Object value) {
        if (selectedRoad == null) {
            return;
        }

        try {
            Integer userValue = Integer.parseInt(value.toString());
            if (userValue < 0 || userValue > selectedRoad.getLength()) {
                addValidationError(context, validate, "Illegal length specified");
            } else {
                atKilometer = userValue;
            }
        } catch (NumberFormatException ex) {
            addValidationError(context, validate, "Input must be a number");
        }
    }

    private void addValidationError(FacesContext context, UIComponent validate, String message) {
        ((UIInput) validate).setValid(false);
        context.addMessage(validate.getClientId(context), new FacesMessage(message));
    }

    public String addSign() {
        SignPlacement signPlacement = new SignPlacement();
        signPlacement.setKilometer(atKilometer);
        signPlacement.setRoad(selectedRoad);
        signPlacement.setSign(selectedSign);

        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().persist(signPlacement);
            transaction.commit();
        }

        return "success";
    }

    public String removeSign(String placementId) {
        if (placementId != null) {
            try (JpaTransaction transaction = jpaService.beginTransaction()) {
                jpaService.getEntityManager().createQuery("delete from SignPlacement sp where sp.id=:id")
                        .setParameter("id", Integer.parseInt(placementId))
                        .executeUpdate();
                transaction.commit();
            }
        }
        return "success";
    }
}
