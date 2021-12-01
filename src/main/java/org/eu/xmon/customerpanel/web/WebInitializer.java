package org.eu.xmon.customerpanel.web;

import com.ftpix.sparknnotation.Sparknotation;
import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import lombok.SneakyThrows;

import static spark.Spark.*;
/**
 * @Author Xmon
 */
public class WebInitializer {
    public static String DOMAIN = "http://localhost";

    /**
     * @apiNote create Web Server
     */
    @SneakyThrows
    public void initializeWebServer(){
        port(80);
        staticFiles.location("/public");
        exception(Exception.class, (exception, req, res) -> exception.printStackTrace());
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Not Found\"}";
        });
        Sparknotation.init();
    }
}
