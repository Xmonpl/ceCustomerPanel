package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkGet;
import com.ftpix.sparknnotation.annotations.SparkPost;
import com.ftpix.sparknnotation.annotations.SparkQueryParam;
import com.google.gson.Gson;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.ActionStatus;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.response.StandardResponse;
import org.eu.xmon.customerpanel.response.StatusResponse;
import org.eu.xmon.customerpanel.utils.DatabaseUtils;
import org.eu.xmon.customerpanel.utils.OtherUtils;
import org.eu.xmon.customerpanel.utils.RegexVariables;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SparkController
public class RegisterController {

    @SparkGet("/account/register")
    public String loginView(final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                response.redirect("/account/dashboard");
                return null;
            }else{
                final Map<String, Object> model = new HashMap<>();
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-register.ftl")
                );
            }
        }else {
            final Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "private/account-register.ftl")
            );
        }
    }

    @SparkPost("/account/register")
    public String register(@SparkQueryParam("email") final String name, @SparkQueryParam("password") final String password, @SparkQueryParam("fullname") final String fullname, final Request req, final Response res){
        if (name == null || password == null || fullname == null || name.isEmpty() || password.isEmpty() || fullname.isEmpty() || name.isBlank() || password.isBlank() || fullname.isBlank()) {
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Username or Password is empty/blank or is nullable!").build());
        }
        if (DbConnect.getDatabase().sql("SELECT * from users WHERE email = ?", name).first(User.class) != null) {
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Email is already exist! An account already exists on this email address").build());
        }
        if (!RegexVariables.EMAIL_REGEX.matcher(name).matches()){
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Email is not valid").build());
        }
        if (!RegexVariables.PASSWORD_REGEX.matcher(password).matches()){
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Your password is dangerous! <br />The password must contain the following criteria: <br /><br />* At least 8 characters, at least one uppercase and lowercase letter and a special character!").build());
        }
        UUID uuid = UUID.randomUUID();
        boolean use = true;
        do{
            if (DbConnect.getDatabase().sql("SELECT * from users WHERE id = ?", uuid.toString()).first(User.class) == null){
                use = false;
            }else{
                uuid = UUID.randomUUID();
            }
        }while (use);
        final User user = User.builder()
                .id(uuid.toString())
                .create_time(OtherUtils.getFormatter().format(new Date()))
                .email(name)
                .first_ip(req.ip())
                .last_ip("-")
                .balance(0.0)
                .full_name(fullname)
                .password(BCrypt.withDefaults().hashToString(8, password.toCharArray()))
                .build();
        final Action register$action = Action.builder()
                .actionStatus(ActionStatus.REGISTRATION_STARTED.message)
                        .ip(req.ip())
                        .timestamp(OtherUtils.getFormatter().format(new Date()))
                        .useragent(req.userAgent())
                        .user_id(uuid.toString())
                        .build();
        DatabaseUtils.insert(user);
        DatabaseUtils.insert(register$action);
        res.cookie("/", "token", BCrypt.withDefaults().hashToString(4, (user.id + "-" + req.ip()).toCharArray()), 3600,false, true);
        res.cookie("/", "uuid", user.id, 3600,false, true);
        System.out.println("[+] New User - " + user.toString());
        return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK).message("User created successfuly!").build());
    }
}
