package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkPost;
import com.ftpix.sparknnotation.annotations.SparkQueryParam;
import com.google.gson.Gson;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.response.StandardResponse;
import org.eu.xmon.customerpanel.response.StatusResponse;
import spark.Request;
import spark.Response;

@SparkController
public class ActionController {
    @SparkPost("/api/action/flat")
    public String action(@SparkQueryParam("id") String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null && id != null){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                final Action action = DbConnect.getDatabase().sql("SELECT * FROM `actions` WHERE `id` = ?", id).first(Action.class);
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                if (action != null && u != null){
                    if (action.getUser_id().equals(u.id) || u.getRole().equals("ADMIN")) {
                        return "<div class=\"modal is-active\">\n" +
                                "        <div class=\"modal-background\"></div>\n" +
                                "        <div class=\"modal-card\">\n" +
                                "            <header class=\"modal-card-head\">\n" +
                                "                <p class=\"modal-card-title\">" + action.id + " - " + action.getActionStatus() + "</p>\n" +
                                "                <button class=\"delete\" aria-label=\"close\" onclick=\"$('#modals').empty()\"></button>\n" +
                                "            </header>\n" +
                                "            <section class=\"modal-card-body\">\n" +
                                "                <p><strong>IP:</strong> " + action.getIp() + "</p>\n" +
                                "                <p><strong>Czas:</strong> " + action.getTimestamp() + "</p>\n" +
                                "                <p><strong>Przeglądarka:</strong> " + action.getUseragent() + "</p>\n" +
                                "            </section>\n" +
                                "        </div>\n" +
                                "    </div>";
                    }else{
                        return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("unautorized").build());
                    }
                }
            }
        }
        return "";
    }

}
