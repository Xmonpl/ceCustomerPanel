package org.eu.xmon.customerpanel;

import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.web.WebInitializer;

/**
 * @Author Xmon
 */
public class Bootstrap {
    public static void main(String[] args) {
        final DbConnect dbConnect = new DbConnect();
        dbConnect.connect();

        final WebInitializer webInitializer = new WebInitializer();
        webInitializer.initializeWebServer();
    }
}
