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

import java.sql.Timestamp;
import java.util.UUID;

@SparkController
public class RegisterController {

    @SparkPost("/account/register")
    public String register(@SparkQueryParam("email") final String name, @SparkQueryParam("password") final String password, @SparkQueryParam("fullname") final String fullname, Request req, Response res){
        if (name == null || password == null || fullname == null || name.isEmpty() || password.isEmpty() || fullname.isEmpty() || name.isBlank() || password.isBlank() || fullname.isBlank()) {
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Username or Password is empty/blank or is nullable!").build());
        }
        if (DbConnect.getDatabase().sql("SELECT * from users WHERE email = ?", name).first(User.class) != null) {
            return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Email is already exist! An account already exists on this email address").build());
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
                .create_time(new Timestamp(System.currentTimeMillis()).toString())
                .email(name)
                .first_ip(req.ip())
                .last_ip(req.ip())
                .balance(0.0)
                .full_name(fullname)
                .password(BCrypt.withDefaults().hashToString(12, password.toCharArray()))
                .build();
        DbConnect.getDatabase().insert(user);
        res.cookie("/", "token", BCrypt.withDefaults().hashToString(6, (user.id + "-" + req.ip()).toCharArray()), 3600,false, true);
        res.cookie("/", "uuid", user.id, 3600,false, true);
        System.out.println("[+] New User - " + user.toString());
        return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK).data(new Gson().toJsonTree(user)).message("User created successfuly!").build());
    }
}
