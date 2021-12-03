package org.eu.xmon.customerpanel.controllers;

import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkGet;
import com.ftpix.sparknnotation.annotations.SparkPost;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import spark.Request;
import spark.Response;

@SparkController
public class TestController {
    private static final String endpointSecret = "whsec_mKjkx3s6lyZmf7qW62rR97W8qIuWjtjW";

    @SparkPost("/webhook")
    public Object handle(final Request request, final Response response){
        String payload = request.body();
        String sigHeader = request.headers("Stripe-Signature");
        Event event = null;
        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );
        } catch (JsonSyntaxException e) {
            response.status(400);
            return "";
        } catch (SignatureVerificationException e) {
            response.status(400);
            return "";
        }
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        }
        switch (event.getType()) {
            case "payment_intent.succeeded": {
                System.out.println(stripeObject.toString());
                break;
            }
        }
        response.status(200);
        return "";
    }

    @SparkGet("/success")
    public String success(final Request request, final Response response){
        return "Udało się";
    }

    @SparkGet("/cancel")
    public String cancel(){
        return "Nie Udało się";
    }

    @SparkGet("/checkout")
    public String checkout(){
        Stripe.apiKey = "sk_test_51K1W2rJDnoyTCKci9pIFRMMhfpeeTML2ZA5iKmmgI34Hf6ofgcdjZwOW4U8dtkOky52ty23Vl7I1j030AbMBbIgW00BkdAaULT";
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <title>Buy cool new product</title>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "    <script src=\"https://polyfill.io/v3/polyfill.min.js?version=3.52.1&features=fetch\"></script>\n" +
                "    <script src=\"https://js.stripe.com/v3/\"></script>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <section>\n" +
                "      <div class=\"product\">\n" +
                "        <img src=\"https://i.imgur.com/EHyR2nP.png\" alt=\"The cover of Stubborn Attachments\" />\n" +
                "        <div class=\"description\">\n" +
                "          <h3>Stubborn Attachments</h3>\n" +
                "          <h5>$20.00</h5>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <form action=\"/create-checkout-session\" method=\"POST\">\n" +
                "        <button type=\"submit\" id=\"checkout-button\">Checkout</button>\n" +
                "      </form>\n" +
                "    </section>\n" +
                "  </body>\n" +
                "</html>";
    }

    @SneakyThrows
    @SparkPost("/create-checkout-session")
    public String login(final Request request, final Response response){
        String YOUR_DOMAIN = "http://localhost";
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/success")
                        .setCancelUrl(YOUR_DOMAIN + "/cancel")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (e.g. pr_1234) of the product you want to sell
                                        .setPrice("price_1K1XFtJDnoyTCKciMmo6NJeE")
                                        .build())
                        .build();
        Session session = Session.create(params);

        response.redirect(session.getUrl(), 303);
        return "";
    }
}
