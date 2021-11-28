package org.eu.xmon.customerpanel.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.util.List;

public class JsonUtils {
    public static String toJson(List<String> list){
        return new Gson().toJson(list);
    }

    @SneakyThrows
    public static List<String> fromJson(String json){
        return new Gson().fromJson(json, new TypeToken<List<String>>() {}.getType());
    }

}
