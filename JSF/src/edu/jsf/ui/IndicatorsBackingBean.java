package edu.jsf.ui;

import edu.jsf.dao.JpaService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@ManagedBean
@ViewScoped
public class IndicatorsBackingBean {
    @ManagedProperty("#{jpaService}")
    private JpaService jpaService;

    @PostConstruct
    public void init() {

    }

    public String getTitle() {
        return "Title";
    }

    public JpaService getJpaService() {
        return jpaService;
    }

    public void setJpaService(JpaService jpaService) {
        this.jpaService = jpaService;
    }
}
