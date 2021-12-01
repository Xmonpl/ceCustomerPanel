package org.eu.xmon.customerpanel.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ftpix.sparknnotation.annotations.SparkController;
import com.ftpix.sparknnotation.annotations.SparkPost;
import com.ftpix.sparknnotation.annotations.SparkQueryParam;
import com.google.gson.Gson;
import org.eu.xmon.customerpanel.database.DbConnect;
import org.eu.xmon.customerpanel.object.Ticket;
import org.eu.xmon.customerpanel.object.TicketMessage;
import org.eu.xmon.customerpanel.object.TicketPriorityEnum;
import org.eu.xmon.customerpanel.object.User;
import org.eu.xmon.customerpanel.response.StandardResponse;
import org.eu.xmon.customerpanel.response.StatusResponse;
import org.eu.xmon.customerpanel.utils.OtherUtils;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SparkController
public class TicketController {
    @SparkPost("/api/ticket/create")
    public String createTicket(@SparkQueryParam("topic") final String topic, @SparkQueryParam("message") final String message, @SparkQueryParam("priority") final String priority, final Request request, final Response response){
        if (request.cookie("uuid") != null && request.cookie("token") != null){
            final BCrypt.Result result = BCrypt.verifyer().verify((request.cookie("uuid") + "-" + request.ip()).toCharArray(), request.cookie("token"));
            if (result.verified){
                if (topic != null && message != null && priority != null){
                    UUID uuid = UUID.randomUUID();
                    boolean use = true;
                    do{
                        if (DbConnect.getDatabase().sql("SELECT * from `tickets` WHERE id = ?", uuid.toString()).first(Ticket.class) == null){
                            use = false;
                        }else{
                            uuid = UUID.randomUUID();
                        }
                    }while (use);
                    final Ticket ticket = Ticket.builder()
                            .messages(List.of(TicketMessage.builder().created(OtherUtils.getFormatter().format(new Date())).author(request.cookie("uuid")).message(message).build()))
                            .topic(topic)
                            .id(uuid.toString())
                            .priority(TicketPriorityEnum.valueOf(priority))
                            .user_id(request.cookie("uuid"))
                            .closed("0")
                            .build();
                    DbConnect.getDatabase().insert(ticket);
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK).message("Ticket Created").data(new Gson().toJsonTree(ticket)).build());
                }else{
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Bad implementation").build());
                }
            }
        }
        return new Gson().toJson(StandardResponse.builder().status(StatusResponse.ERROR).message("Non logged").build());
    }
}
