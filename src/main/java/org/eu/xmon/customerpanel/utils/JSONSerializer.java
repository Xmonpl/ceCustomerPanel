package org.eu.xmon.customerpanel.utils;

import com.dieselpoint.norm.serialize.DbSerializable;
import com.google.gson.Gson;

public class JSONSerializer implements DbSerializable {
    @Override
    public String serialize(Object o) {
        return new Gson().toJson(o);
    }

    @Override
    public Object deserialize(String s, Class<?> aClass) {
        return new Gson().fromJson(s, aClass);
    }
}
