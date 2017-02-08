package com.satec.geoposition.DTO;

import org.slf4j.LoggerFactory;

/**
 * Created by lucas.alvarez on 08/02/2017.
 */
public class LocationDTO {

    private String direction;
    private String lat;
    private String lng;

    public LocationDTO(String direction, String lat, String lng) {
        this.direction = direction;
        this.lat = lat;
        this.lng = lng;
    }
    public String getCSV(String delimiter){
      return direction+delimiter+lat+delimiter+lng;
    };

    @Override
    public String toString() {
        return "locationDTO{" +
                "direction='" + direction + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String directio) {
        this.direction = directio;
    }



}
