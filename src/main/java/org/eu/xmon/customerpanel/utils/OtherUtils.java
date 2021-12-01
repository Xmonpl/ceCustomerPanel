package org.eu.xmon.customerpanel.utils;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.format.FormatStyle;

public class OtherUtils {
    @Getter
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public static boolean isStringInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
