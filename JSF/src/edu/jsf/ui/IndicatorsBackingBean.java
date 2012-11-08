package edu.jsf.ui;

import edu.jsf.dao.JpaService;
import edu.jsf.model.Sign;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Collection;

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
    private String selectedSignName;

    @PostConstruct
    public void init() {

    }

    public JpaService getJpaService() {
        return jpaService;
    }

    public void setJpaService(JpaService jpaService) {
        this.jpaService = jpaService;
    }

    public Sign getSelectedSign() {
        return selectedSign;
    }

    public void setSelectedSign(Sign selectedSign) {
        this.selectedSign = selectedSign;
        updateSelectedSignFields();
    }

    private void updateSelectedSignFields() {
        if (selectedSign == null) {
            selectedSignName = null;
        } else {
            selectedSignName = selectedSign.getName();
        }
    }

    public Collection<Sign> getDefinedSigns() {
        return jpaService.getEntityManager()
                .createQuery("select s from Sign s", Sign.class)
                .getResultList();
    }

    public void setSelectedSignName(String selectedSignName) {
        this.selectedSignName = selectedSignName;
    }

    public String getSelectedSignName() {
        return selectedSignName;
    }
}
