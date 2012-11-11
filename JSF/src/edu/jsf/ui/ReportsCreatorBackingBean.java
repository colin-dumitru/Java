package edu.jsf.ui;

import com.icesoft.faces.context.ByteArrayResource;
import com.icesoft.faces.context.Resource;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import edu.jsf.dao.JpaService;
import edu.jsf.model.Road;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.util.*;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@ManagedBean
@ViewScoped
public class ReportsCreatorBackingBean {
    @ManagedProperty("#{jpaService}")
    private JpaService jpaService;

    private Collection<Integer> selectedRoadIds = new ArrayList<>();


    @PostConstruct
    public void init() {

    }

    public JpaService getJpaService() {
        return jpaService;
    }

    public void setJpaService(JpaService jpaService) {
        this.jpaService = jpaService;
    }

    public List<SelectItem> getRoads() {
        List<Road> roads = jpaService.getEntityManager().createQuery("select r from Road r", Road.class)
                .getResultList();
        return createSelectItems(roads);
    }

    private List<SelectItem> createSelectItems(List<Road> roads) {
        List<SelectItem> selectItems = new ArrayList<>();
        for (Road road : roads) {
            selectItems.add(new SelectItem(road.getId(), road.toString()));
        }
        return selectItems;
    }

    public List<String> getSelectedRoads() {
        List<Road> roads = jpaService.getEntityManager().createQuery("select r from Road r", Road.class)
                .getResultList();
        return getIds(roads);
    }

    public void setSelectedRoads(List<String> selectedRoads) {
        if (selectedRoads != null) {
            selectedRoadIds.clear();
            for (String selectedRoad : selectedRoads) {
                selectedRoadIds.add(Integer.parseInt(selectedRoad));
            }
        }
    }

    private List<String> getIds(List<Road> roads) {
        List<String> ids = new ArrayList<>();
        for (Road road : roads) {
            ids.add(road.getId().toString());
        }
        return ids;
    }


    public Resource getSignPlacementResource() throws JRException, IOException {
        final List<Object[]> sourceObjects = jpaService.getEntityManager()
                .createQuery("select sp.sign.code, sp.sign.name, sp.kilometer, sp.comment, sp.road.indicative, road.id " +
                        "from SignPlacement sp")
                .getResultList();

        JRDataSource dataSource = new JRDataSource() {
            Integer currentIndex = 0;

            @Override
            public boolean next() throws JRException {
                return sourceObjects.size() - 1 > currentIndex++;
            }

            @Override
            public Object getFieldValue(JRField jrField) throws JRException {
                switch (jrField.getName()) {
                    case "CODE":
                        return sourceObjects.get(currentIndex)[0];
                    case "NAME":
                        return sourceObjects.get(currentIndex)[1];
                    case "KILOMETER":
                        return sourceObjects.get(currentIndex)[2];
                    case "COMMENT":
                        return sourceObjects.get(currentIndex)[3];
                    case "INDICATIVE":
                        return sourceObjects.get(currentIndex)[4];
                    case "ID":
                        return sourceObjects.get(currentIndex)[5];
                }
                return null;
            }
        };

        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/indicatoare.jasper");

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportPath);
        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, createParameters(), dataSource);
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, byteOutputStream);

        return new ByteArrayResource(byteOutputStream.getBytes());
    }

    private Map<String, Object> createParameters() {
        Map<String, Object> params = new HashMap<>();
        params.put("ROADS", selectedRoadIds);
        return params;
    }
}

