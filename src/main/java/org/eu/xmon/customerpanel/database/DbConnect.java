package org.eu.xmon.customerpanel.database;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.sqlmakers.MySqlMaker;
import com.google.gson.Gson;
import lombok.Getter;
import org.eu.xmon.customerpanel.object.Action;
import org.eu.xmon.customerpanel.object.Ticket;
import org.eu.xmon.customerpanel.object.User;

import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * @Author Xmon
 */
public class DbConnect {

    @Getter
    private static final Database database = new Database();

    /**
     * @apiNote connect to database
     */
    public void connect(){
        /*
          @TODO Implement download database from dbhub.io
         */

        database.setJdbcUrl("jdbc:sqlite:database.db");
        if (!new File("database.db").exists()) {
            database.createTable(User.class);
            database.createTable(Action.class);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            /*
              @TODO implement upload database to dbhub.io
             */
        }));
    }
}