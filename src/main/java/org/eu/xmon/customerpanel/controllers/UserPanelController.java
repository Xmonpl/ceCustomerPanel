package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.*;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.utils.MD5Util;
import org.eu.xmon.customerpanel.utils.MvcEnum;
import org.eu.xmon.customerpanel.utils.OtherUtils;
import org.eu.xmon.customerpanel.utils.ParginationUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@SparkController
public class UserPanelController {
    @SparkGet("/account/dashboard/actions/:id")
    public String actinos(@SparkParam("id") final String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ){
            if (id == null || !OtherUtils.isStringInt(id)){
                return "upsi";
            }
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                model.put("user", u);
                model.put("actions", ParginationUtils.getPage(DbConnect.getDatabase().sql("SELECT * FROM `actions` WHERE `user_id` = ? ORDER BY `id` DESC", request.cookie("uuid")).results(Action.class), Integer.parseInt(id), 10));
                model.put("avatar", "https://www.gravatar.com/avatar/" + MD5Util.md5Hex(u.getEmail()));
                model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-userpanel-actions.ftl")
                );
            }else{
                final Map<String, Object> model = new HashMap<>();
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-login.ftl")
                );
            }
        }else {
            final Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "private/account-register.ftl")
            );
        }
    }

    @SparkPost("/api/action/flat")
    public String action(@SparkQueryParam("id") String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null && id != null){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                Action action = DbConnect.getDatabase().sql("SELECT * FROM `actions` WHERE `id` = ?", id).first(Action.class);
                if (action != null){
                    return "<div class=\"modal is-active\">\n" +
                            "        <div class=\"modal-background\"></div>\n" +
                            "        <div class=\"modal-card\">\n" +
                            "            <header class=\"modal-card-head\">\n" +
                            "                <p class=\"modal-card-title\">" + action.id +" - " + action.getActionStatus() + "</p>\n" +
                            "                <button class=\"delete\" aria-label=\"close\" onclick=\"$('#modals').empty()\"></button>\n" +
                            "            </header>\n" +
                            "            <section class=\"modal-card-body\">\n" +
                            "                <p><strong>IP:</strong> " + action.getIp() + "</p>\n" +
                            "                <p><strong>Czas:</strong> " + action.getTimestamp() + "</p>\n" +
                            "                <p><strong>Przeglądarka:</strong> " + action.getUseragent() + "</p>\n" +
                            "            </section>\n" +
                            "        </div>\n" +
                            "    </div>";
                }
            }
        }
        return "";
    }

    @SparkGet("/account/dashboard")
    public String loginView(final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                model.put("user", u);
                model.put("actions", DbConnect.getDatabase().sql("SELECT * FROM `actions` WHERE `user_id` = ? ORDER BY `id` DESC LIMIT 5", request.cookie("uuid")).results(Action.class));
                model.put("avatar", "https://www.gravatar.com/avatar/" + MD5Util.md5Hex(u.getEmail()));
                model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-userpanel.ftl")
                );
            }else{
                final Map<String, Object> model = new HashMap<>();
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-login.ftl")
                );
            }
        }else {
            final Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "private/account-register.ftl")
            );
        }
    }
}
