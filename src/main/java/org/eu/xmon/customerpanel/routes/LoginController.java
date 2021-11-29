package org.eu.xmon.customerpanel.routes;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkPost;
import com.ftpix.sparknnotation.annotations.SparkQueryParam;
import com.google.gson.Gson;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.response.StandardResponse;
import org.eu.xmon.customerpanel.response.StatusResponse;
import spark.Request;
import spark.Response;

@SparkController
public class LoginController {

    @SparkPost("/account/login")
    public String login(@SparkQueryParam("email") final String name, @SparkQueryParam("password") final String password, final Request req, final Response res){
        if (name == null || password == null || name.isBlank() || password.isBlank() || name.isEmpty() || password.isEmpty()) {
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Username or Password is empty/blank or is nullable!").build());
        }
        final User user = DbConnect.getDatabase().sql("SELECT * FROM users WHERE email = ?", name).first(User.class);
        if (user == null){
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("The email or password is incorrect or the account has not been activated").build());
        }
        final BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!result.verified){
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("The email or password is incorrect or the account has not been activated").build());
        }
        user.setLast_ip(req.ip());
        DbConnect.getDatabase().update(user);
        res.cookie("/", "token", BCrypt.withDefaults().hashToString(6, (user.id + "-" + req.ip()).toCharArray()), 3600,false, true);
        res.cookie("/", "uuid", user.id, 3600,false, true);
        System.out.println("[+-] SignIn User - " + user.toString());
        return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK).message("Login Succesfully").data(new Gson().toJsonTree(user)).build());
    }
}
