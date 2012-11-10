package edu.jsf.model;

import javax.persistence.*;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@Entity
public class SignPlacement {
    private Integer id;
    private Road road;
    private Sign sign;
    private Integer kilometer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    @ManyToOne
    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    @Column
    public Integer getKilometer() {
        return kilometer;
    }

    public void setKilometer(Integer kilometer) {
        this.kilometer = kilometer;
    }
}
