package com.emergya.smc.model;

import java.io.Serializable;

/**
 *
 * @author lroman
 */
public class Stop  implements Serializable {
    private String name;
    private double latitude;
    private double longitude;   
    
    private int index;

    public Stop() {
    }
    
    
    

    public Stop(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(int i) {
        this.index = i;
    }
    
}
