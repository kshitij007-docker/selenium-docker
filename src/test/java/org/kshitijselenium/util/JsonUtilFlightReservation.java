package org.kshitijselenium.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kshitijselenium.tests.flightreservation.model.FlightReservationTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtilFlightReservation {

    private static Logger log= LoggerFactory.getLogger(JsonUtilFlightReservation.class);

    private static ObjectMapper mapper=new ObjectMapper();
    public static FlightReservationTestData getTestData(String path)
    {
        try(InputStream stream=ResourceLoader.getResource(path))
        {
            return mapper.readValue(stream,FlightReservationTestData.class);
        }
        catch(Exception e)
        {
            log.error("Unable to read test data {} ",path,e);
        }
        return null;
    }


}
