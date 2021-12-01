package org.eu.xmon.customerpanel.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eu.xmon.customerpanel.object.TicketMessage;

import javax.persistence.AttributeConverter;
import java.util.List;

public class TicketMessageConverter implements AttributeConverter<List<TicketMessage>, String> {
    @Override
    public String convertToDatabaseColumn(List<TicketMessage> ticketMessage) {
        return new Gson().toJson(ticketMessage);
    }

    @Override
    public List<TicketMessage> convertToEntityAttribute(String s) {
        return new Gson().fromJson(s, new TypeToken<List<TicketMessage>>() {}.getType());
    }
}
