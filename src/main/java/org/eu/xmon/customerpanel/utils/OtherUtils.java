package org.eu.xmon.customerpanel.utils;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.format.FormatStyle;
import java.util.UUID;

public class OtherUtils {
    @Getter
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public static boolean isStringUUID(String s){
        try{
            UUID.fromString(s);
            return true;
        }catch (IllegalArgumentException ex){
            return false;
        }
    }

    public static boolean isStringInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
