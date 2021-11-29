package org.eu.xmon.customerpanel.routes;

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
import org.eu.xmon.customerpanel.utils.RegexVariables;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

@SparkController
public class LoginController {

    @SparkGet("/account/login")
    public String loginView(final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null ){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                response.redirect("/account/dashboard");
                return null;
            }else{
                final Map<String, Object> model = new HashMap<>();
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "private/account-login.ftl")
                );
            }
        }else {
            final Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "private/account-login.ftl")
            );
        }
    }

    @SparkPost("/account/login")
    public String login(@SparkQueryParam("email") final String name, @SparkQueryParam("password") final String password, final Request req, final Response res){
        if (name == null || password == null || name.isBlank() || password.isBlank() || name.isEmpty() || password.isEmpty()) {
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Username or Password is empty/blank or is nullable!").build());
        }
        if (!RegexVariables.EMAIL_REGEX.matcher(name).matches()){
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Email is not valid").build());
        }
        final User user = DbConnect.getDatabase().sql("SELECT * FROM users WHERE email = ?", name).first(User.class);
        if (user == null){
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("The email or password is incorrect or the account has not been activated").build());
        }
        final BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!result.verified){
            final Action login$failed$atempt = Action.builder()
                    .actionStatus(ActionStatus.FAILED_LOGIN_ATTEMPT.name())
                    .ip(req.ip())
                    .timestamp(LocalTime.now().toString())
                    .useragent(req.userAgent())
                    .user_id(user.id)
                    .build();
            DatabaseUtils.insert(login$failed$atempt);
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("The email or password is incorrect or the account has not been activated").build());
        }
        if (user.getLast_ip().equals("-")){
            final Action register$success$action = Action.builder()
                    .actionStatus(ActionStatus.REGISTRATION_COMPLETED.name())
                    .ip(req.ip())
                    .timestamp(LocalTime.now().toString())
                    .useragent(req.userAgent())
                    .user_id(user.id)
                    .build();
            DatabaseUtils.insert(register$success$action);
        }
        user.setLast_ip(req.ip());
        DatabaseUtils.update(user);
        final Action login$success = Action.builder()
                .actionStatus(ActionStatus.LOGGED_IN.name())
                .ip(req.ip())
                .timestamp(LocalTime.now().toString())
                .useragent(req.userAgent())
                .user_id(user.id)
                .build();
        DatabaseUtils.insert(login$success);
        res.cookie("/", "token", BCrypt.withDefaults().hashToString(4, (user.id + "-" + req.ip()).toCharArray()), 3600,false, true);
        res.cookie("/", "uuid", user.id, 3600,false, true);
        System.out.println("[+-] SignIn User - " + user.toString());
        return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK).message("Login Succesfully").build());
    }
}
