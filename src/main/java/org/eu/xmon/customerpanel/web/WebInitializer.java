package org.eu.xmon.customerpanel.web;

import static spark.Spark.*;
/**
 * @Author Xmon
 */
public class WebInitializer {
    public static String DOMAIN = "http://localhost";

    /**
     * @apiNote create Web Server
     */
    public void initializeWebServer(){
        port(80);
        staticFiles.location("/public");
        exception(Exception.class, (exception, req, res) -> exception.printStackTrace());

    }
}
