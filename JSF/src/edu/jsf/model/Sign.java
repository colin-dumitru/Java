package edu.jsf.model;

import javax.persistence.*;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@Entity
public class Sign {

    private Integer id;
    private String code;
    private String name;
    private String imageType;
    private String data;

    public Sign() {
    }

    public Sign(Sign other) {
        this.id = other.id;
        this.code = other.code;
        this.name = other.name;
        this.imageType = other.imageType;
        this.data = other.data;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Column
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, name);
    }

    @Transient
    public String getImageSource() {
        return String.format("data:%s;base64,%s",
                getImageType(),
                getData());
    }
}
