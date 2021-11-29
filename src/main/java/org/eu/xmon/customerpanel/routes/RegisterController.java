package org.eu.xmon.customerpanel.routes;

import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkPost;
import com.ftpix.sparknnotation.annotations.SparkQueryParam;

@SparkController
public class RegisterController {
    @SparkPost("/account/register")
    public String register(@SparkQueryParam("name") final String name, @SparkQueryParam("password") final String password){
        if (name == null || password == null) {
            return "puste";
        }
        return name + " " + password;
    }
}