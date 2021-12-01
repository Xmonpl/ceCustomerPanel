package org.eu.xmon.customerpanel.utils;

import spark.Request;

public class DebugUtils {
    public static void debugQueryParams(final Request request){
        request.params().forEach((x, y) ->{
            System.out.println(x + " -> " + y);
        });
        request.queryParams().forEach(y ->{
            System.out.println(y + " -> " + request.queryParams(y));
        });
    }
}
