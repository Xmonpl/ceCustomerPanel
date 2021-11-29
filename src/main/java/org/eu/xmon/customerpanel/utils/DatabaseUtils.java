package org.eu.xmon.customerpanel.utils;

import org.eu.xmon.customerpanel.database.DbConnect;

import java.util.concurrent.Executors;

public class DatabaseUtils {
    public static void insert(Object zlass){
        Executors.newCachedThreadPool().submit(() ->{
            DbConnect.getDatabase().insert(zlass);
        });
    }

    public static void update(Object zlass){
        Executors.newCachedThreadPool().submit(() ->{
            DbConnect.getDatabase().update(zlass);
        });
    }

}
