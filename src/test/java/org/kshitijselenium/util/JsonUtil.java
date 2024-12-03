package org.kshitijselenium.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kshitijselenium.tests.vendorportal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {

    private static Logger log =LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper=new ObjectMapper();

    public static VendorPortalTestData getTestData(String path) throws IOException {
        try(InputStream stream=ResourceLoader.getResource(path)){
            /*
            Here we are keeping InputStream to try block as this block closes the file
            automatically once its task is done and it is a good practice to close it.
             */
            return mapper.readValue(stream,VendorPortalTestData.class);
            /*
            Here we are taking stream, converting it to test data instance and returning it
             */
        }catch (Exception e)
        {
            log.error("Unable to read test data {}",path,e);
        }
        return null;

    }


    public static <T> T getTestDataGeneric(String path,Class<T> type) throws IOException {
        try(InputStream stream=ResourceLoader.getResource(path)){
            /*
            Here we are keeping InputStream to try block as this block closes the file
            automatically once its task is done and it is a good practice to close it.
             */
            return mapper.readValue(stream,type);
            /*
            Here we are taking stream, converting it to test data instance and returning it
             */
        }catch (Exception e)
        {
            log.error("Unable to read test data {}",path,e);
        }
        return null;

    }
}
