package org.eu.xmon.customerpanel.database;

import com.dieselpoint.norm.Database;
import lombok.Getter;

import java.io.File;

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
            //Tabels
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            /*
              @TODO implement upload database to dbhub.io
             */
        }));
    }
}