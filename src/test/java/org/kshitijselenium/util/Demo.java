package org.kshitijselenium.util;

import org.apache.commons.io.IOUtils;
import org.kshitijselenium.tests.vendorportal.model.VendorPortalTestData;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Demo {

   /* public static void main(String [] args) throws IOException {
       InputStream stream= ResourceLoader.getResource("dummy.txt");
      String content=  IOUtils.toString(stream, StandardCharsets.UTF_8);
        System.out.println(content);

    }*/


    public static void main(String[] args) throws IOException {
     // VendorPortalTestData testData= JsonUtil.getTestData("test-data/vendor-portal/john.json");
      //  System.out.println(testData.monthlyEarning());


        System.setProperty("browser","firefox");

        Config.initialize();

        System.out.println(Config.get("browser"));
    }

    /*
    Json util is class to read json file and convert it to java object
    Here VendorPortalTestData is the class in which 'record' method to read json is kept

    Note: Read about faster jackson in git hub vid. 89
     */
}
