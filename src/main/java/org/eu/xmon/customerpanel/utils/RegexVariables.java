package org.eu.xmon.customerpanel.utils;

import java.util.regex.Pattern;

public class RegexVariables {
    public static final Pattern EMAIL_REGEX = Pattern.compile("[^@ \t\r\n]+@[^@ \t\r\n]+\\.[^@ \t\r\n]+", Pattern.MULTILINE);
    public static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", Pattern.MULTILINE);
}
