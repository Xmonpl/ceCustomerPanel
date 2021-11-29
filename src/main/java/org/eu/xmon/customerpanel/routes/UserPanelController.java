package org.eu.xmon.customerpanel.routes;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkGet;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.utils.MD5Util;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@SparkController
public class UserPanelController {
    @SparkGet("/account/dashboard")
    public String loginView(final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                final Map<String, Object> model = new HashMap<>();
                final User u = DbConnect.getDatabase().sql("SELECT * FROM `users` WHERE `id` = ?", request.cookie("uuid")).first(User.class);
                model.put("user", u);
                model.put("actions", DbConnect.getDatabase().sql("SELECT * FROM `actions` WHERE `user_id` = ? LIMIT 5", request.cookie("uuid")).results(Action.class));
                model.put("avatar", "https://www.gravatar.com/avatar/" + MD5Util.md5Hex(u.getEmail()));
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
