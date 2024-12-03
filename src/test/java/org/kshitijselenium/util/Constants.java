package org.kshitijselenium.util;

public class Constants { //This is to keep any  variables in entire framework in one class
    //For ex: if we use "browser" in test class directly than there could be a possibility that of mistyping it
    // Hence to avoid confusion in spelling of variable and increasing debugging process these are defined here

    public static final String GRID_ENABLED="selenium.grid.enabled";
    public static final String GRID_URL_FORMAT="selenium.grid.urlFormat";
    public static final String GRID_HUB_HOST="selenium.grid.hubHost";

    public static final String BROWSER="browser";

    public static final String CHROME="chrome";

    public static final String FIREFOX="firefox";
    public static final String DRIVER="driver" ;

    public static final String FLIGHT_RESERVATION_URL="flightReservation.url";

    public static final String VENDOR_PORTAL_URL="vendorPortal.url";


}
