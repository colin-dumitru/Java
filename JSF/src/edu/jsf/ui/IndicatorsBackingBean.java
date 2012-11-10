package edu.jsf.ui;

import edu.jsf.dao.JpaService;
import edu.jsf.dao.JpaTransaction;
import edu.jsf.model.Sign;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@ManagedBean
@ViewScoped
public class IndicatorsBackingBean {
    @ManagedProperty("#{jpaService}")
    private JpaService jpaService;

    private Sign selectedSign;
    private Sign editedSign;

    @PostConstruct
    public void init() {

    }

    public JpaService getJpaService() {
        return jpaService;
    }

    public void setJpaService(JpaService jpaService) {
        this.jpaService = jpaService;
    }

    public String getSelectedSign() {
        if (selectedSign != null) {
            return selectedSign.getId().toString();
        }
        return null;
    }

    public void setSelectedSign(String signId) {
        if (signId != null) {
            selectedSign = jpaService.getEntityManager().find(Sign.class, Integer.parseInt(signId));
            editedSign = new Sign(this.selectedSign);
        } else {
            selectedSign = null;
            editedSign = null;
        }
    }

    public Collection<SelectItem> getDefinedSigns() {
        List<Sign> signs = jpaService.getEntityManager()
                .createQuery("select s from Sign s", Sign.class)
                .getResultList();
        return buildSelectItemsFromSigns(signs);
    }

    private Collection<SelectItem> buildSelectItemsFromSigns(List<Sign> signs) {
        Collection<SelectItem> selectItems = new ArrayList<>();
        for (Sign sign : signs) {
            selectItems.add(new SelectItem(sign.getId(), sign.toString()));
        }
        return selectItems;
    }

    public void setSelectedSignName(String selectedSignName) {
        if (editedSign != null) {
            editedSign.setName(selectedSignName);
        }
    }

    public String getSelectedSignName() {
        if (selectedSign != null) {
            return selectedSign.getName();
        }
        return null;
    }

    public String getSelectedSignCode() {
        if (selectedSign != null) {
            return selectedSign.getCode();
        }
        return null;
    }

    public void setSelectedSignCode(String code) {
        if (editedSign != null) {
            editedSign.setCode(code);
        }
    }

    public String getImageSource() {
        if (selectedSign != null) {
            return selectedSign.getImageSource();
        }
        return null;
    }

    public String getImageData() {
        if (selectedSign != null) {
            return selectedSign.getData();
        }
        return null;
    }

    public void setImageData(String data) {
        if (editedSign != null) {
            editedSign.setData(data);
        }
    }

    public String saveNewSign() {
        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            editedSign.setId(null);
            jpaService.getEntityManager().persist(editedSign);
            transaction.commit();
        }
        return "success";
    }

    public String updateSign() {
        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().merge(editedSign);
            transaction.commit();
        }
        return "success";
    }

    public String deleteSign() {
        try (JpaTransaction transaction = jpaService.beginTransaction()) {
            jpaService.getEntityManager().createQuery("delete from Sign s where s.id=:id")
                    .setParameter("id", selectedSign.getId())
                    .executeUpdate();
            transaction.commit();
        }
        return "success";
    }
}
