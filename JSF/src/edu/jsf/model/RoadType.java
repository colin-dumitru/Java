package edu.jsf.model;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public enum RoadType {
    EUROPEAN("European"),
    NATIONAL("National"),
    COUNTY("County");

    private String userName;

    private RoadType(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
