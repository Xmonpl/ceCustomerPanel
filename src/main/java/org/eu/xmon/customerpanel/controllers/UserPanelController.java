package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.*;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.Ticket;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.response.StandardResponse;
import org.eu.xmon.customerpanel.response.StatusResponse;
import org.eu.xmon.customerpanel.utils.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import javax.servlet.http.HttpServletResponse;
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
    @SneakyThrows
    @SparkGet("/api/avatar/:id")
    public HttpServletResponse getAvatar(@SparkParam("id") final String uuid, final Request request, final Response response){
        final HttpServletResponse raw = response.raw();
        if (uuid != null && OtherUtils.isStringUUID(uuid)){
            final String email = DbConnect.getDatabase().sql("SELECT `email` FROM `users` WHERE `id` = ?", uuid).first(String.class);
            if (email != null) {
                raw.getOutputStream().write(FileUtils.getByteImage("https://www.gravatar.com/avatar/" + MD5Util.md5Hex(email)));
                raw.getOutputStream().flush();
                raw.getOutputStream().close();
                response.status(200);
                return raw;
            }
        }
        raw.getOutputStream().write(FileUtils.getByteImage("https://www.gravatar.com/avatar/00000000000000000000000000000000"));
        raw.getOutputStream().flush();
        raw.getOutputStream().close();
        response.status(200);
        return raw;
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
                if (u != null) {
                    model.put("user", u);
                    model.put("actions", DbConnect.getDatabase().sql("SELECT * FROM `actions` WHERE `user_id` = ? ORDER BY `id` DESC LIMIT 5", request.cookie("uuid")).results(Action.class));
                    model.put("footer", MvnController.getMvc(MvcEnum.FOOTER));
                    return new VelocityTemplateEngine().render(
                            new ModelAndView(model, "private/account-userpanel.ftl")
                    );
                }else{
                    response.redirect("/account/dashboard/logout");
                    return "";
                }
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
