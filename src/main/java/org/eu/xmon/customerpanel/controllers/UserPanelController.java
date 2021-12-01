package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.*;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.Ticket;
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
    @SparkGet("/account/dashboard/logout")
    public String logout(final Request request, final Response response){
        response.removeCookie("/", "token");
        response.removeCookie("/", "uuid");
        response.redirect("/account/login");
        return "";
    }

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
                response.redirect("/account/login");
                return "";
            }
        }else {
            response.redirect("/account/register");
            return "";
        }
    }

    @SparkGet("/account/dashboard/ticket/show/:id")
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
                model.put("avatar", "https://www.gravatar.com/avatar/" + MD5Util.md5Hex(u.getEmail()));
                model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-userpanel-ticket.ftl")
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

    @SparkGet("/account/dashboard/ticket/list/:id")
    public String ticketList(@SparkParam("id") final String id, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ) {
            if (id == null || !OtherUtils.isStringInt(id)){
                return "upsi";
            }
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified) {
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                model.put("user", u);
                model.put("tickets", ParginationUtils.getPage(DbConnect.getDatabase().sql("SELECT * FROM `tickets` WHERE `user_id` = ?", request.cookie("uuid")).results(Ticket.class), Integer.parseInt(id), 10));
                model.put("avatar", "https://www.gravatar.com/avatar/" + MD5Util.md5Hex(u.getEmail()));
                model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-userpanel-ticketlist.ftl")
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

    @SparkGet("/account/dashboard/ticket/new")
    public String ticketCreate(final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ) {
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified) {
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                model.put("user", u);
                model.put("avatar", "https://www.gravatar.com/avatar/" + MD5Util.md5Hex(u.getEmail()));
                model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-userpanel-ticketcreate.ftl")
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
                response.redirect("/account/login");
                return "";
            }
        }else {
            response.redirect("/account/register");
            return "";
        }
    }
}
