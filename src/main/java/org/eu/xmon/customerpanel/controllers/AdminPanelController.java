package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkGet;
import com.ftpix.sparknnotation.annotations.SparkParam;
import com.ftpix.sparknnotation.annotations.SparkPost;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.Ticket;
import org.eu.xmon.customerpanel.object.User;
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
public class AdminPanelController {
    @SparkGet("/account/adminpanel/ticket/:id")
    public String ticketGet(@SparkParam("id") final String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ) {
            if (id == null || !OtherUtils.isStringUUID(id)){
                return "upsi";
            }
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified) {
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                model.put("user", u);
                model.put("ticket", DbConnect.getDatabase().sql("SELECT * FROM `tickets` WHERE `id` = ?", id).first(Ticket.class));
                model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/admin-panel-ticket.ftl")
                );
            }else{
                response.redirect("/account/login");
                return "";
            }
        }else{
            response.redirect("/account/register");
            return "";
        }
    }


    @SparkGet("/account/adminpanel/tickets/:id")
    public String tickets(@SparkParam("id") final String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null) {
            if (id == null || !OtherUtils.isStringInt(id)){
                return "upsi";
            }
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified) {
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                if (u != null) {
                    if (u.getRole().equals("ADMIN")){
                        model.put("user", u);
                        model.put("tickets", ParginationUtils.getPage(DbConnect.getDatabase().sql("SELECT * FROM `tickets` ORDER BY cast(closed as int)asc, priority='HIGH' DESC, priority='NORMAL' DESC, priority='LOW' DESC;").results(Ticket.class), Integer.parseInt(id), 10));
                        model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                        return new VelocityTemplateEngine().render(
                                new ModelAndView(model, "private/admin-panel-tickets.ftl")
                        );
                    }else{
                        response.redirect("/account/dashboard");
                        return "";
                    }
                }else{
                    response.redirect("/account/dashboard/logout");
                    return "";
                }
            }else{
                response.redirect("/account/login");
                return "";
            }
        }else{
            response.redirect("/account/register");
            return "";
        }
    }

    @SparkGet("/account/adminpanel/users/:id")
    public String users(@SparkParam("id") final String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null) {
            if (id == null || !OtherUtils.isStringInt(id)){
                return "upsi";
            }
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified) {
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                if (u != null) {
                    if (u.getRole().equals("ADMIN")){
                        model.put("user", u);
                        model.put("users", ParginationUtils.getPage(DbConnect.getDatabase().sql("SELECT * FROM `users`;").results(User.class), Integer.parseInt(id), 10));
                        model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                        return new VelocityTemplateEngine().render(
                                new ModelAndView(model, "private/admin-panel-users.ftl")
                        );
                    }else{
                        response.redirect("/account/dashboard");
                        return "";
                    }
                }else{
                    response.redirect("/account/dashboard/logout");
                    return "";
                }
            }else{
                response.redirect("/account/login");
                return "";
            }
        }else{
            response.redirect("/account/register");
            return "";
        }
    }
}
