package com.satec.geoposition;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.satec.geoposition.DTO.LocationDTO;
import org.apache.commons.httpclient.util.URIUtil;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lucas.alvarez on 08/02/2017.
 */
public  class geoposition {
    private final static org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(geoposition.class);

    static Function<String, LocationDTO> mapString2LocationDTO = new Function<String, LocationDTO>() {

        public LocationDTO apply(String direction) {

            try {

                URL url = new URL(
                        "http://maps.googleapis.com/maps/api/geocode/json?address="
                                + URIUtil.encodeQuery(direction) + "&sensor=false");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));


                // Convert to a JSON object to print data
                JsonParser jp = new JsonParser(); //from gson
                JsonElement root = jp.parse(br); //Convert the input stream to a json element
                slf4jLogger.info(root.toString());
                LocationDTO out=new LocationDTO(
                        root.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("formatted_address").toString(),
                        root.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").toString(),
                        root.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").toString());



                return out;

            } catch (IOException e) {
                slf4jLogger.error(e.toString());
            }

            return null;
        }
    };


    public static LocationDTO getPosition(String direction) {
        List<String> input =new ArrayList<>();
        input.add(direction);
return getPosition(input).get(0);

    }



    public static List<LocationDTO> getPosition(List<String> directions) {
        return directions.stream().map(mapString2LocationDTO).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> input =new ArrayList<>();
        input.add("Fernandez Ladreda 16 oviedo");
        input.add("Bravo murillo 15 madrid");
        input.add("Fernandez Ladreda 16 oviedo");
        input.add("Bravo murillo 15 madrid");
        input.add("Fernandez Ladreda 16 oviedo");
        input.add("Bravo murillo 15 madrid");
        input.add("Bravo murillo 15 madrid");
        input.add("Fernandez Ladreda 16 oviedo");
        input.add("Bravo murillo 15 madrid");
        input.add("Bravo murillo 15 madrid");
        input.add("Bravo murillo 15 madrid");
        input.add("Fernandez Ladreda 16 oviedo");
        input.add("Bravo murillo 15 madrid");
        input.add("Bravo murillo 15 madrid");
        input.add("Bravo murillo 15 madrid");
        input.add("Fernandez Ladreda 16 oviedo");
        input.add("Bravo murillo 15 madrid");


        List<LocationDTO>output=getPosition(input);
        slf4jLogger.info(Arrays.toString(output.toArray()));
        slf4jLogger.info(getPosition("fernandez ladreda oviedo").toString());

        StringBuffer text=new StringBuffer("");

        output.forEach(locationDTO -> text.append(locationDTO.getCSV()).append("\n"));

        slf4jLogger.info(text.toString());

    }


    ;

}
