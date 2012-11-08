package edu.jsf.model;

import javax.persistence.*;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@Entity
public class Road {

    private Integer id;
    private String indicative;
    private RoadType roadType;
    private Integer length;
    private String administration;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    @Column
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Column
    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    @Column
    public String getIndicative() {
        return indicative;
    }

    public void setIndicative(String indicative) {
        this.indicative = indicative;
    }
}
