package org.eu.xmon.customerpanel.converters;

import com.google.gson.Gson;
import org.eu.xmon.customerpanel.object.UserBan;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserBanConverter  implements AttributeConverter<UserBan, String> {
    @Override
    public String convertToDatabaseColumn(UserBan userBan) {
        return new Gson().toJson(userBan);
    }

    @Override
    public UserBan convertToEntityAttribute(String s) {
        return new Gson().fromJson(s, UserBan.class);
    }
}
